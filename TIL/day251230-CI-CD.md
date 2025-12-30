# CI / CD

1: 현재 빌드(devDebug) 결과물만 정확히 업로드하기
```yaml
      # 1. 'dev' 환경의 디버그 APK만 빌드합니다.
      - name: Build dev-debug APK
        run: ./gradlew assembleDevDebug

      # 2. 빌드된 'dev-debug' APK 하나만 업로드합니다.
      - name: Upload dev-debug APK
        uses: actions/upload-artifact@v4 # v3 대신 최신 v4 사용 권장
        with:
          name: dev-debug-apk # 이름 명확화
          path: app/build/outputs/apk/dev/debug/app-dev-debug.apk # 경로 명확화

```

2: 모든 Flavor의 Debug APK를 빌드하고 업로드하기
```yaml
      # 1. 모든 flavor의 debug APK를 빌드합니다. (dev, qa, prod)
      - name: Build all debug APKs
        run: ./gradlew assembleDebug

      # 2. 생성된 모든 debug APK들을 업로드합니다.
      - name: Upload All Debug APKs
        uses: actions/upload-artifact@v4
        with:
          name: all-debug-apks # 원래 이름 유지
          path: app/build/outputs/apk/**/debug/*.apk # 이 경로는 여기서 유효함

```

