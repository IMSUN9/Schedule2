# Schedule2

## API 명세

### 1. 일정 생성
- Method: POST
- URL: /schedules
- Request Body:
  - username
  - title
  - contents
- Response Body:
  - id
  - username
  - title
  - contents
  - createdAt
  - updatedAt

### 2. 전체 일정 조회
- Method: GET
- URL: /schedules
- Request Body: 없음
- Response Body:
  - schedules[]

### 3. 일정 단건 조회
- Method: GET
- URL: /schedules/{scheduleId}
- Request Body: 없음
- Response Body:
  - id
  - username
  - title
  - contents
  - createdAt
  - updatedAt

### 4. 일정 수정
- Method: PUT
- URL: /schedules/{scheduleId}
- Request Body:
  - username
  - title
  - contents
- Response Body:
  - id
  - username
  - title
  - contents
  - createdAt
  - updatedAt

### 5. 일정 삭제
- Method: DELETE
- URL: /schedules/{scheduleId}
- Request Body: 없음
- Response Body: 없음

### 6. 유저 생성
- Method: POST
- URL: /users
- Request Body:
  - username
  - email
- Response Body:
  - id
  - username
  - email
  - createdAt
  - updatedAt

### 7. 유저 전체 조회
- Method: GET
- URL: /users
- Request Body: 없음
- Response Body:
  - users[]

### 8. 유저 단건 조회
- Method: GET
- URL: /users/{userId}
- Request Body: 없음
- Response Body:
  - id
  - username
  - email
  - createdAt
  - updatedAt

### 9. 유저 수정
- Method: PUT
- URL: /users/{userId}
- Request Body:
  - username
  - email
- Response Body:
  - id
  - username
  - email
  - createdAt
  - updatedAt

### 10. 유저 삭제
- Method: DELETE
- URL: /users/{userId}
- Request Body: 없음
- Response Body: 없음

### 11. 회원가입
- Method: POST
- URL: /users/signup
- RequestBody:
  - username
  - email
  - password
- Response Body:
  - id
  - username
  - email
  - createdAt
  - updatedAt

### 12. 로그인
- Method: POST
- URL: /login
- RequestBody:
  - email
  - password
- ResponseBody: 
  - message
- 인증 방식: Cookie / Session

## ERD

### User
- 회원가입 단계에서 password 필드가 추가됨

- id
- username
- email
- createdAt
- updatedAt

### Schedule
- id
- username
- title
- contents
- createdAt
- updatedAt

### 관계
- User1 : N schedule
- 추후 연관관계 적용 시 Schedule은 username 대신 userId를 가짐

