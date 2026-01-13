package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.adapter

import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.ItemRecipeBinding
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.OnRecipeClickListener

/**
 * 주어진 데이터를 View에 바인딩
 * 데이터 판단이나 로직 없이 렌더링과 이벤트 전달만 담당
 */
class RecipeViewHolder(
    private val binding: ItemRecipeBinding,
    private val listener: OnRecipeClickListener
) : RecyclerView.ViewHolder(binding.root) {
    // RecyclerView는 목록을 스크롤 할 때, 화면 밖으로 나간 뷰(껍데기)를 버리지 않고 재사용(Recycle)
    //  재사용된 뷰는 이전 데이터가 남아있거나 비어 있는데
    //  여기에 새로운 데이터를 다시 채워 넣는 과정이 필수적
    //  이것을 "다시 바인딩한다(Re-binding)" 라고 함


    /**
     * 레시피 데이터를 뷰에 바인딩하고 클릭 리스너를 설정
     *
     * 뷰에 바인딩? 데이터(Data)를 화면 요소(View)에 연결해서 채워 넣는 작업
     *
     * @param recipe 표시할 레시피 정보 객체
     * @param isBookmarked 해당 레시피의 즐겨찾기 상태
     */
    fun bind(recipe: Recipe, isBookmarked: Boolean) = with(binding) {
        tvTitle.text = recipe.title
        tvChef.text = recipe.chefName
        tvTime.text = recipe.time
        tvRating.text = "${recipe.rating}"

        // 이미지 로딩: ViewHolder는 “이 URL을 이 ImageView에 보여줘라”는 지시만 수행
        binding.ivThumbnail.load(recipe.imageUrls) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
        }

        // 아이템 전체 클릭 시 레시피 상세 화면 등으로 이동하는 리스너 호출
        root.setOnClickListener {
            listener.onRecipeClick(recipe.id)
        }

        // 즐겨찾기 버튼의 선택 상태(UI)를 설정하고 클릭 시 북마크 상태 전환 리스너 호출
        btnBookmark.isSelected = isBookmarked
        btnBookmark.setOnClickListener {
            listener.onBookmarkClick(recipe.id)
        }
    }
}