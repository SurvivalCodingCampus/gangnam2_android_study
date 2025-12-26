1. 기본 요구사항

```markdown
HomeScreen 오류 시나리오 통합 테스트

시나리오
Fake Repository에서 에러 반환
에러 상태 UI 표시 (문구 or Empty View)

테스트 요구사항
성공 / 실패 케이스 각각 테스트
에러 시 다른 UI가 표시되는지

@app/src/main/java/com/survivalcoding/gangnam2
kiandroidstudy/presentation/screen/home/HomeScreen.kt

```

2. import 에러 수정 요청 로그 메세지 제공

```markdown
Unresolved reference 'HiltTestActivity'.
```

3. 성공 UI 는 보이지 않는지 검증하는 코드 직접 추가 >  성공 / 실패 각각 테스트