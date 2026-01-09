package com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentDetailBinding
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeListBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bundle 데이터 받아오기
        val recipeId = arguments?.getInt("id")
            ?: throw IllegalStateException("recipeId is null")

        // 받아온 ID UI에 표시
        binding.detailIdTv.text = recipeId.toString()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}