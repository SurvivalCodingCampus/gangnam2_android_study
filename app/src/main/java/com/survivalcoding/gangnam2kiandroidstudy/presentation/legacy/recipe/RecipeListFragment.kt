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

@AndroidEntryPoint
class RecipeListFragment : Fragment(), OnRecipeClickListener {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedRecipesViewModel by viewModels()

    private lateinit var adapter: RecipeAdapter
    private var recyclerViewState: Parcelable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeState()
        observeEvent()

        binding.ivBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = RecipeAdapter(this)

        binding.rvRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecipes.adapter = adapter
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    adapter.submitList(state.recipes)
                    adapter.submitBookmarkedIds(state.bookmarkedIds)

                    // 스크롤 상태 복원
                    recyclerViewState?.let {
                        binding.rvRecipes.layoutManager?.onRestoreInstanceState(it)
                        recyclerViewState = null
                    }
                }
            }
        }
    }

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

    override fun onRecipeClick(recipeId: Int) {
        viewModel.onAction(
            SavedRecipesAction.RecipeClicked(recipeId)
        )
    }

    override fun onBookmarkClick(recipeId: Int) {
        viewModel.onAction(
            SavedRecipesAction.BookmarkClicked(recipeId)
        )
    }

    private fun navigateToDetail(recipeId: Int) {
        val fragment = RecipeDetailFragment.newInstance(recipeId)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

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
