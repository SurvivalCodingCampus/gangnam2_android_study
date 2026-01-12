package com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.saved_recipe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeListBinding
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe.SavedRecipesState
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe.SavedRecipesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {
    private val viewModel: SavedRecipesViewModel by viewModel()

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RecipeListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        observeState()
    }

    private fun setupRecyclerView(view: View) {
        _binding = FragmentRecipeListBinding.bind(view)

        adapter = RecipeListAdapter { recipe ->
            viewModel.saveNewRecipe(recipe.id)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@RecipeListFragment.adapter
        }
    }

    // onViewCreated에서 사용하는 private 메서드
    // SavedRecipes 화면의 State를 수집하고 렌더링을 위해 인자로 전달
    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                render(state)
            }
        }
    }

    // SavedRecipes 화면의 State를 인자로 받아서
    // submitList() 메서드를 통해 RecyclerView에 전달
    private fun render(state: SavedRecipesState) {
        when (state) {
            is SavedRecipesState -> {
                adapter.submitList(state.savedRecipes)
            }
        }
    }

    // RecyclerView가 파괴되면 ViewBinding한 내용을 삭제
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}