package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IngredientItem(
                modifier = Modifier.padding(top = 50.dp),
                imageURL = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
                ingredientName = "Tomato",
                ingredientWeight = "200g"
            )
        }
    }
}