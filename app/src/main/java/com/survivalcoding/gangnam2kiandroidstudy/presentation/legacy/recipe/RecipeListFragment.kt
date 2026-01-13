package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeListBinding
import com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.adapter.RecipeAdapter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.SavedRecipesAction
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.SavedRecipesEvent
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.SavedRecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * 저장된 레시피 목록을 보여주는 화면입니다.
 * ViewModel을 통해 상태(State)와 이벤트(Event)를 관찰하고,
 * 사용자 액션(Action)을 ViewModel에 전달하는 MVI와 유사한 구조로 설계되었습니다.
 */
@AndroidEntryPoint
class RecipeListFragment : Fragment(), OnRecipeClickListener {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    // Hilt를 이용한 ViewModel 주입
    private val viewModel: SavedRecipesViewModel by viewModels()

    private lateinit var adapter: RecipeAdapter

    // 화면 회전이나 이동 시 스크롤 위치를 유지하기 위한 변수
    private var recyclerViewState: Parcelable? = null

    /**
     * Fragment의 UI 레이아웃을 생성합
     * XML 설계도를 실제 View 객체로 만드는 인플레이션(Inflation)이 진행
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * onCreateView에서 생성된 뷰가 완전히 만들어진 후 호출
     * 리사이클러뷰 설정, 데이터 관찰(Observe) 등 초기화 작업을 수행하기 가장 좋은 시점 (Compose의 `LaunchedEffect`와 비슷한 역할)
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeState()
        observeEvent()

        binding.ivBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    /**
     * RecyclerView 초기 설정 (LayoutManager, Adapter 연결)
     */
    private fun setupRecyclerView() {
        adapter = RecipeAdapter(this)

        binding.rvRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecipes.adapter = adapter
    }

    /**
     * ViewModel의 UI 상태(State)를 관찰하여 화면을 갱신합니다.
     * repeatOnLifecycle을 사용하여 Fragment가 STARTED 상태일 때만 수집하도록 안전하게 처리합니다.
     */
    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    // 데이터 리스트 갱신
                    adapter.submitList(state.recipes)
                    // 북마크 상태(ID 목록) 갱신
                    adapter.submitBookmarkedIds(state.bookmarkedIds)

                    // 저장된 스크롤 상태가 있다면 복원
                    recyclerViewState?.let {
                        binding.rvRecipes.layoutManager?.onRestoreInstanceState(it)
                        recyclerViewState = null
                    }
                }
            }
        }
    }

    /**
     * ViewModel에서 발생하는 일회성 이벤트(Event)를 처리합니다.
     * 화면 이동이나 토스트 메시지 출력 등을 담당합니다.
     */
    private fun observeEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        is SavedRecipesEvent.NavigateToRecipeDetail -> {
                            Toast.makeText(
                                requireContext(),
                                "Recipe ID: ${event.recipeId}",
                                Toast.LENGTH_SHORT
                            ).show()

                            navigateToDetail(event.recipeId)
                        }

                        is SavedRecipesEvent.ShowSnackBar -> {
                            Toast.makeText(
                                requireContext(),
                                event.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    /**
     * OnRecipeClickListener 구현: 레시피 클릭 시 ViewModel에 액션 전달
     */
    override fun onRecipeClick(recipeId: Int) {
        viewModel.onAction(
            SavedRecipesAction.RecipeClicked(recipeId)
        )
    }

    /**
     * OnRecipeClickListener 구현: 북마크 클릭 시 ViewModel에 액션 전달
     */
    override fun onBookmarkClick(recipeId: Int) {
        viewModel.onAction(
            SavedRecipesAction.BookmarkClicked(recipeId)
        )
    }

    /**
     * 상세 화면으로 프래그먼트 교체 및 백스택 추가
     */
    private fun navigateToDetail(recipeId: Int) {
        val fragment = RecipeDetailFragment.newInstance(recipeId)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // 뒤로가기 버튼 지원
            .commit()
    }

    /**
     * Fragment가 일시 정지될 때 RecyclerView의 현재 스크롤 상태를 저장
     */
    override fun onPause() {
        super.onPause()
        recyclerViewState =
            binding.rvRecipes.layoutManager?.onSaveInstanceState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
