# 1. 과제 1번
@search_recipe_screen_view_model.kt에서 검색 + 결과 표시 할 수 있는 통합테스트를 할 거야.
@SearchRecipeViewModelTest.kt 에서 만들어 주면 되고 안 적혀 있는 부분 채워주고,


테스트 시나리오 
1. 검색창에 키워드를 입력
2. 잠시대기
3. Fake Repository에서 결과를 받아 리스트에 화면표시

테스트 요구사항  
- 텍스트입력 -> 버튼 클릭 -> 리스트 표시가 되는지
- 뷰모델 레포지토리 UI 연결 검증
- 실제서버가 아닌 FakeRepository dev환경에서 하는거 빠져 있으면 넣어서 해줘.

+ @SearchRecipeViewModelTest.kt 중복 있으면 제거 해줘


# 3. 과제 3번
@HomeScreen.kt에서 즐겨찾기 토글 + 상태유지 테스트를 할거야.
테스트 시나리오 
1. 리스트에서 북마크를 클릭
2. ViewModel의 상태변경
3. 화면 재구성 후에도 상태 유지

테스트 요구 사항
1. 버튼 클릭 후 아이콘 상태 변경
2. 화면 회전 or recomposition 상황에서도 상태 유지할 수 있게
만들어줘 
+ @HomeScreenTest.kt 에서 에러 있는 데 그거 고쳐줘

# 4. 과제 4번
@HomeScreen.kt 오류 시나리오 통합테스트를 할 거야. 테스트 시나리오는 
1. Fake Repository에서 에러 반환 
2. 에러 상태 UI 표시(문구 or Empty View)
테스트 요구사항 으로는 
1. 성공/ 실패 케이스 각각 테스트 
2. 에러 시 다른 UI가 표시되는지야
