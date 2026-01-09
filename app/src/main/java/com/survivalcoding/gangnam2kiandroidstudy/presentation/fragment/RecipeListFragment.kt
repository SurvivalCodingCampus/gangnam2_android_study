package com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeListBinding
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe.SavedRecipesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.survivalcoding.gangnam2kiandroidstudy.presentation.adapter.SavedRecipeAdapter
import kotlinx.coroutines.launch


class RecipeListFragment(
    private val listener: OnRecipeSelectedListener
) : Fragment() {

    interface OnRecipeSelectedListener {
        fun onRecipeSelected(recipeId: Int)
    }

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedRecipesViewModel by viewModel()
    private lateinit var recipeAdapter: SavedRecipeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeState()

    }


    // recyclerView
    private fun setupRecyclerView() {
        recipeAdapter = SavedRecipeAdapter(
            object : SavedRecipeAdapter.OnRecipeClickListener {
                override fun onRecipeClick(recipeId: Int) {
                    Toast.makeText(requireContext(), "ID: $recipeId", Toast.LENGTH_SHORT)
                        .show()

                    listener.onRecipeSelected(recipeId)
                }
            }
        )

        binding.recyclerView.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    // 데이터 받기
    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->

                    // 레시피 리스트 업데이트
                    if (!state.isLoading) {
                        recipeAdapter.submitList(state.recipes)
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Fragment View 생명주기 끝나면 반드시 해제
        _binding = null
    }
}