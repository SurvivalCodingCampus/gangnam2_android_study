package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes.legacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.misterjerry.gangnam2kiandroidstudy.databinding.FragmentRecipeListBinding
import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes.SavedRecipesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipeListFragment : Fragment() {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedRecipesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.rcv

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                val adapter = RecipeListAdapter(it.savedRecipesList)
                recyclerView.adapter = adapter
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}