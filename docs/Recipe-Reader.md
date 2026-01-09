# 📖 Recipe App Content Provider 연동 가이드

본 가이드는 `gangnam2kiandroidstudy` 앱에서 제공하는 레시피 데이터를 외부 앱에서 가져오는 방법을 설명합니다.

## 1. 개요
Clean Architecture 기반으로 구현된 이 앱은 Room Database를 기반으로 레시피 정보를 저장하며, `ContentProvider`를 통해 외부로 데이터를 노출합니다.

## 2. 권한 설정
외부 앱에서 레시피 데이터를 읽기 위해서는 다음 권한을 `AndroidManifest.xml`에 추가해야 합니다.

```xml
<uses-permission android:name="com.survivalcoding.gangnam2kiandroidstudy.READ_RECIPE" />
```

## 3. URI 정보
- **Authority:** `com.survivalcoding.gangnam2kiandroidstudy.provider`
- **전체 레시피 목록 조회:** `content://com.survivalcoding.gangnam2kiandroidstudy.provider/recipes`
- **특정 레시피 상세 조회:** `content://com.survivalcoding.gangnam2kiandroidstudy.provider/recipes/{id}`

## 4. 데이터 스키마 (Cursor Column)
조회된 Cursor에서 다음 컬럼들을 사용할 수 있습니다.

| 컬럼명 | 타입 | 설명 |
|---|---|---|
| `id` | Integer | 레시피 고유 ID |
| `category` | String | 카테고리 (예: Indian, Asian 등) |
| `name` | String | 레시피 제목 |
| `image` | String | 이미지 URL |
| `chef` | String | 요리사 이름 |
| `time` | String | 소요 시간 |
| `rating` | Double | 평점 |

## 5. 구현 상세 (Internal)

### Data Layer
- **RecipeEntity**: Room을 이용한 `recipes` 테이블 정의.
- **RecipeDao**: `getAllCursor()`, `getByIdCursor(id)`를 통해 Cursor 객체 직접 반환.
- **AppDataBase**: 기존 `User` 테이블에 `Recipe` 테이블 추가 및 버전 업그레이드 (v2).

### Domain Layer
- **RecipeRepository**: `getRecipesCursor()`, `getRecipeCursorById()` 인터페이스 추가.
- **GetRecipesUseCase**: 전체 레시피 조회 로직 담당.

### Framework Layer
- **RecipeProvider**: `UriMatcher`를 통해 요청을 분기하고 Repository를 통해 데이터를 Cursor 형태로 반환.
- **Sync Logic**: DB가 비어있을 경우 Asset의 JSON 데이터를 읽어와 DB에 채워주는 로직이 Repository에 포함되어 있음.
