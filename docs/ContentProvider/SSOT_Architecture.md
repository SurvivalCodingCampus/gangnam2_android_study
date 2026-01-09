# Single Source of Truth (SSOT) 아키텍처 설계

본 프로젝트는 데이터의 신뢰성과 성능을 극대화하기 위해 **Room DB를 유일한 로컬 진실 공급원(SSOT)**으로 사용하는 로컬-퍼스트(Local-First) 아키텍처를 채택하고 있습니다.

## 1. 개요
모든 데이터(레시피 정보, 북마크 상태)는 일차적으로 Room DB에 저장되며, UI 및 외부 인터페이스(`ContentProvider`)는 오직 Room DB의 데이터만을 바라봅니다. 원격 데이터(GitHub, Firebase)는 이 로컬 DB를 최신화하기 위한 '입력원'으로만 작동합니다.

## 2. 데이터 흐름 (Data Flow)

### GitHub RAW JSON (레시피 캐싱)
- **과정**: `RecipeRepository`가 Remote API를 호출하여 전체 레시피 목록을 받아옵니다.
- **처리**: 받아온 데이터를 즉시 Room의 `recipes` 테이블에 **Upsert**(기존 정보 갱신, 신규 추가) 합니다.
- **결과**: 네트워크 오프라인 상태에서도 이미 캐싱된 레시피 정보를 사용자에게 즉시 제공할 수 있습니다.

### Firebase Firestore (북마크 실시간 동기화)
- **과정**: `BookmarkRepository`에서 Firestore의 `addSnapshotListener`를 상시 가동합니다.
- **처리**: 클라우드 측에서 북마크 변경(추가/삭제)이 감지되면 즉시 로컬 Room의 `bookmarks` 테이블을 실시간으로 업데이트합니다.
- **결과**: 사용자의 모든 기기에서 동일한 북마크 상태를 실시간으로 공유하며, 로컬 쿼리 속도로 데이터를 조회할 수 있습니다.

## 3. 구현 상세

### Room Entity 구성
- **`RecipeEntity`**: 레시피의 전체 메타데이터를 저장합니다. 상세 필드(재료 등)는 JSON Converter를 통해 문자열로 압축 저장됩니다.
- **`BookmarkEntity`**: 오직 북마크된 레시피의 ID와 타임스탬프만을 관리하여 가벼운 필터링을 지원합니다.

### Repository 로직 (SSOT 전략)
```kotlin
// SSOT 예시: 원격 데이터를 가져와 로컬 DB를 갱신하고, 항상 로컬 DB를 반환
override suspend fun findRecipes(): Result<List<Recipe>, String> {
    val remoteData = dataSource.getRecipes() // Remote Fetch
    if (remoteData != null) {
        recipeDao.insertRecipes(remoteData.toEntities()) // Local Update
    }
    return Result.Success(recipeDao.getAllRecipes()) // Local Return
}
```

## 4. 아키텍처의 장점
1. **오프라인 지원**: 네트워크가 없는 상황에서도 앱의 모든 기능을 제약 없이 사용할 수 있습니다.
2. **UI 응답성**: 모든 조회가 인메모리 방식에 가까운 로컬 DB 기반이므로 UI 지연(Jank)이 없습니다.
3. **데이터 공유의 용이성**: `ContentProvider`가 동기적으로 데이터를 제공해야 할 때, 이미 로컬에 준비된 데이터를 반환하므로 성능과 정합성이 보장됩니다.
