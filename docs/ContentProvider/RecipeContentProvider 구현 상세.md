# RecipeContentProvider 구현 상세

프로젝트의 북마크된 레시피 데이터를 외부 앱과 공유하기 위해 구현된 `RecipeContentProvider`의 설계 및 설정 내용입니다.

## 1. 컨텐츠 URI 패턴 설계
외부 앱이 데이터에 접근하기 위한 주소를 정의합니다.

- **Authority**: `com.survivalcoding.gangnam2kiandroidstudy.provider` (프로젝트 고유 식별자)
- **Path**: `bookmarked_recipes` (북마크된 레시피 목록 테이블 접근)
- **Full URI**: `content://com.survivalcoding.gangnam2kiandroidstudy.provider/bookmarked_recipes`

`RecipeContentProvider.kt`의 `UriMatcher`를 통해 위 패턴을 매칭하도록 설계되었습니다.
```kotlin
private const val AUTHORITY = "com.survivalcoding.gangnam2kiandroidstudy.provider"
private const val BOOKMARKED_RECIPES = 1
private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    addURI(AUTHORITY, "bookmarked_recipes", BOOKMARKED_RECIPES)
}
```

## 2. MIME 타입 설계
조회된 데이터의 형식을 시스템에 알려주기 위해 MIME 타입을 정의합니다. 목록(`dir`) 형태를 반환하므로 다음과 같이 설계했습니다.

- **MIME Type**: `vnd.android.cursor.dir/vnd.com.survivalcoding.gangnam2kiandroidstudy.provider.bookmarked_recipes`

`RecipeContentProvider.kt`의 `getType` 메서드에서 반환됩니다.
```kotlin
override fun getType(uri: Uri): String? {
    return when (uriMatcher.match(uri)) {
        BOOKMARKED_RECIPES -> "vnd.android.cursor.dir/vnd.$AUTHORITY.bookmarked_recipes"
        else -> null
    }
}
```

## 3. AndroidManifest 설정
시스템이 `ContentProvider`를 인식하고 외부 앱이 접근할 수 있도록 매니페스트에 등록했습니다.

- **File**: `app/src/main/AndroidManifest.xml`
- **Settings**:
    - `android:name`: 프로바이더 클래스 경로
    - `android:authorities`: 위에서 설계한 Authority 값
    - `android:exported="true"`: **중요!** 다른 앱에서 이 프로바이더에 접근할 수 있도록 허용

```xml
<provider
    android:name=".data.local.RecipeContentProvider"
    android:authorities="com.survivalcoding.gangnam2kiandroidstudy.provider"
    android:exported="true" />
```

## 제공 데이터 컬럼 명세
외부 앱이 `Cursor`를 통해 읽어갈 수 있는 컬럼 목록입니다. (Room `RecipeEntity` 기반)

| Column Name | Type | Description |
| :--- | :--- | :--- |
| `id` | Long | 레시피 고유 식별자 (PK) |
| `name` | String | 레시피 명칭 |
| `category` | String | 레시피 카테고리 |
| `image` | String | 음식 이미지 URL |
| `chef` | String | 제작 셰프 명 |
| `time` | String | 조리 소요 시간 |
| `rating` | Double | 레시피 평점 |
| `ingredientsJson` | String | 재료 목록 (JSON 형식 문자열) |
