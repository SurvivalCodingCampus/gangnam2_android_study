package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.detail.RecipeDetailFragment
import com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.saved_recipe.RecipeListFragment

// AppCompatActivity: XML View 시스템 기반
// Toolbar, Fragment, Material 테마 사용 가능
// 안드로이드 스튜디오 기본 템플릿
class SavedRecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saved)

        // 앱 최초 진입 시 RecipeListFragment 표시
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    RecipeListFragment(),
                )
                .commit()
        }
    }

    // RecipeListFragment에서 호출하는 메서드
    fun showRecipeDetail(bundle: Bundle) {
        // 3) DetailFragment 생성
        val fragment = RecipeDetailFragment().apply {
            // 전달받은 Bundle을 Fragment arguments에 설정
            arguments = bundle
        }

        // 4) Fragment 교체
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null) // 뒤로가기 버튼을 누르면 이전 Fragment로 돌아감
            .commit()
    }
}