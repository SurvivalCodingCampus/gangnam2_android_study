package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail.legacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.misterjerry.gangnam2kiandroidstudy.databinding.FragmentSavedRecipeDetailBinding

class SavedRecipeDetailFragment() : Fragment() {

    private var _binding: FragmentSavedRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private var recipeName: String? = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeName = it.getString(ARG_RECIPE_NAME)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRecipeName.text = recipeName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val ARG_RECIPE_NAME = "recipe_name"
        fun newInstance(recipeName: String): SavedRecipeDetailFragment {
            val fragment = SavedRecipeDetailFragment()
            val args = Bundle()
            args.putString(ARG_RECIPE_NAME, recipeName)
            fragment.arguments = args
            return fragment
        }
    }
}