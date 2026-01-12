package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.saved_recipe.RecipeListFragment

// AppCompatActivity: XML View 시스템 기반
// Toolbar, Fragment, Material 테마 사용 가능
// 안드로이드 스튜디오 기본 템플릿
class SavedRecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saved)

        // 처음 실행될 때만 Fragment 추가
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    RecipeListFragment()
                )
                .commit()
        }
    }
}