package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes.legacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.misterjerry.gangnam2kiandroidstudy.R
import com.misterjerry.gangnam2kiandroidstudy.databinding.FragmentRecipeListBinding
import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail.legacy.SavedRecipeDetailFragment
import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes.SavedRecipesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipeListFragment : Fragment(), RecipeListAdapter.OnItemClickListener {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedRecipesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding != null) { //binding을 최초 1회만 시행하여 이전 스크롤 상태를 기억하게 함
            return binding.root
        }
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)

        val adapter = RecipeListAdapter(viewModel.state.value.savedRecipesList)
        adapter.setOnItemClickListener(this@RecipeListFragment)
        binding.rcv.adapter = adapter
        binding.rcv.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.rcv
        val adapter = binding.rcv.adapter as? RecipeListAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                adapter?.updateList(it.savedRecipesList)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onItemClicked(pos: Int) {
        Toast.makeText(
            requireContext(),
            "RecipeItem 클릭됨. id:${viewModel.state.value.savedRecipesList[pos].id}",
            Toast.LENGTH_SHORT
        ).show()

        val fragment =
            SavedRecipeDetailFragment.newInstance(viewModel.state.value.savedRecipesList[pos].name)
        parentFragmentManager.commit {
            replace(R.id.fcv, fragment)
            addToBackStack(null)
        }
    }

    override fun deleteItem(recipeId: Int) {
        viewModel.delete(recipeId)
    }
}