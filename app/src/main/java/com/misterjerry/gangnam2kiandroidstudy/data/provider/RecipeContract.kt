package com.misterjerry.gangnam2kiandroidstudy.data.provider

import android.content.ContentResolver
import android.net.Uri

/**
 * RecipeContract
 * 외부 앱과 공유할 데이터의 주소(URI)와 컬럼 명세를 정의하는 계약 클래스입니다.
 */
object RecipeContract {

    // 1. Authority: 안드로이드 시스템에서 이 Provider를 식별하는 고유 이름
    const val AUTHORITY = "com.misterjerry.gangnam2kiandroidstudy.provider"

    // 2. Base Content URI: 모든 요청의 기본이 되는 URI
    private val BASE_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY")

    // 3. Path: 접근하고자 하는 테이블의 경로
    const val PATH_SAVED_RECIPES = "saved_recipes"

    /**
     * SavedRecipeEntry: '저장된 레시피' 테이블에 대한 세부 명세
     */
    object SavedRecipeEntry {

        // 외부 앱이 쿼리 시 사용할 최종 URI (content://com.misterjerry.../saved_recipes)
        val CONTENT_URI: Uri = BASE_CONTENT_URI.buildUpon()
            .appendPath(PATH_SAVED_RECIPES)
            .build()

        // MIME Type: 다중 항목 (Directory)
        const val CONTENT_TYPE = "${ContentResolver.CURSOR_DIR_BASE_TYPE}/vnd.$AUTHORITY.$PATH_SAVED_RECIPES"
        
        // MIME Type: 단일 항목 (Item)
        const val CONTENT_ITEM_TYPE = "${ContentResolver.CURSOR_ITEM_BASE_TYPE}/vnd.$AUTHORITY.$PATH_SAVED_RECIPES"

        // 컬럼 이름: Room Entity (@ColumnInfo)와 이름을 맞춥니다.
        const val COLUMN_ID = "id"
    }
}
