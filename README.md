# KBO 커뮤니티 (가칭)

## 1. 실행
### 로컬
- `./gradlew bootrun`

## 2. 컨벤션
### 커밋
- feat: 기능 추가, 개발
- test: 테스트 코드 작성
- doc: 문서 작성
- chore: 그 외

### 프로젝트 구조 (레이어 구조)
일반적인 3 layer 구조에서 서비스 레이어의 비대화를 방지하기 위해 프레젠테이션 레이어와 서비스 레이어 사이에 유즈케이스 레이어를 추가하였습니다.
이 때 프레젠테이션 레이어와 유즈케이스 레이어 사이에는 반드시 별개의 Dto를 구현하도록 하여 서로 간의 의존성을 끊어냅니다.
- 프레젠테이션 레이어 (Controller)
- 유즈케이스 레이어 (Usecase)
- 서비스 레이어 (Service)
- 레포지토리 레이어 (Repository)

## 3. API 정보
### OpenAPI (Swagger)
- 경로
  - `/api/swagger-ui/index.html`