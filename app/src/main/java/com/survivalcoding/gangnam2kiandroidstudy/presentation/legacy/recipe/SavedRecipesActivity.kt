package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.gangnam2kiandroidstudy.R
import dagger.hilt.android.AndroidEntryPoint

// activity_saved_recipes.xml 연결
@AndroidEntryPoint
class SavedRecipesActivity : AppCompatActivity() {
    // 화면이 처음 만들어 질 때 딱 한번 실행
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. 코드를 실행하기 전에, 안드로이드가 기본적으로 해야 할 화면 세팅부터 먼저 해줌
        super.onCreate(savedInstanceState)
        // 2. 액티비티의 레이아웃을 표시하는데 사용할 xml 파일을 지정
        setContentView(R.layout.activity_saved_recipes)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipeListFragment())
                .commit()
        }
    }
}