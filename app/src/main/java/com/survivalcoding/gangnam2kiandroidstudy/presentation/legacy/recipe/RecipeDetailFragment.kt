package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 레시피의 상세 정보를 보여주는 화면
 * 리스트에서 선택된 레시피의 ID를 전달받아 해당 내용을 표시
 */
@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    // ViewBinding: Fragment의 생명주기에 맞춰 메모리 누수를 방지하기 위한 처리
    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, // 이 프래그먼트가 들어갈 부모 뷰
        savedInstanceState: Bundle?
    ): View {
        // XML 레이아웃을 실제 뷰 객체로 인플레이트함
        // inflate? XML 레이아웃 파일(설계도)을 실제 메모리에 있는 뷰 객체(실체)로 만드는 과정
        // false: 지금 당장 틀에 끼워 넣지는 말고(attach), 뷰만 만들기 (나중에 프레임워크가 알아서 끼울 것)
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bundle에서 전달받은 레시피 ID를 꺼내와서 화면에 표시 (추후 데이터 로드 로직으로 확장 가능)
        val recipeId = arguments?.getInt(ARG_RECIPE_ID) ?: 0
        binding.tvRecipeId.text = "Recipe ID: $recipeId"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰가 파괴될 때 바인딩 객체를 해제하여 메모리 누수 방지
        _binding = null
    }

    companion object {
        private const val ARG_RECIPE_ID = "recipe_id"

        /**
         * Fragment 인스턴스를 생성할 때 필요한 인자(recipeId)를 안전하게 전달하기 위한 정적 팩토리 메서드
         */
        fun newInstance(recipeId: Int): RecipeDetailFragment {
            return RecipeDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_RECIPE_ID, recipeId)
                }
            }
        }
    }
}