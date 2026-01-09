# 📋 레시피 앱 컨텐트 프로바이더 구현 로드맵 (Clean Architecture)
## 1. Data Layer (데이터 계층)

가장 먼저 기반이 되는 데이터베이스와 데이터 접근 로직을 정의합니다.

- Room Entity 정의: 레시피 정보를 담을 Table 스키마 설계 (id, 제목, 재료, 설명 등).
- Room Database & DAO: * 실제 DB 인스턴스 생성.
  - Cursor를 반환하는 Query 메서드 작성 (ContentProvider는 기본적으로 Cursor를 통해 데이터를 전달하기 때문).
- Repository Implementation: DAO를 호출하여 데이터를 가져오는 로직 구현.

## 2. Domain Layer (도메인 계층)

비즈니스 로직의 핵심이며, 안드로이드 프레임워크와 의존성을 분리합니다.

- Model 정의: 앱 내부에서 사용하는 순수한 Recipe Data Class.
- Repository Interface: Data Layer와 연결을 위한 인터페이스 정의.
- Use Case 작성: * GetRecipesUseCase: 전체 레시피 목록 조회 로직.
  - GetRecipeDetailUseCase: 특정 ID의 레시피 조회 로직.

## 3. Framework Layer: Content Provider (프레임워크 계층)

외부 앱과의 접점이 되는 ContentProvider를 구현합니다.

- Authority & UriMatcher 설정: * com.example.recipe.provider와 같은 고유 권한 정의.
  - 목록 조회용 및 단일 항목 조회용 URI 패턴 매칭.
- ContentProvider 클래스 구현:
  - query(): Use Case 또는 Repository를 통해 Room DB의 데이터를 Cursor 형태로 반환.
  - insert(), update(), delete(): 필요 시 데이터 조작 로직 추가.
- AndroidManifest.xml 등록: android:exported="true" 설정 및 권한(permission) 정의.

## 4. 구현 내용 정리

구현한 내용들을 별도의 앱에서 참고할 수 있게 자세하게 내용을 정리해서 다음의 경로 파일에 작성: "D:\workspace\android_lecture\gangnam2_android_study\docs\Recipe-Reader.md"