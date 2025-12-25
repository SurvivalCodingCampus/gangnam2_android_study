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



