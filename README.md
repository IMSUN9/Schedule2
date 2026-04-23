# Schedule2

## API 명세

### 1. 일정 생성
- Method: POST
- URL: /schedules
- Request Body:
  - title
  - contents
- Response Body:
  - id
  - userId
  - username
  - title
  - contents
  - createdAt
  - updatedAt
- 인증 방식: Cookie / Session
- 설명:
  - 로그인한 사용자의 세션 정보를 기준으로 일정을 생성한다.

### 2. 일정 전체 조회
- Method: GET
- URL: /schedules
- Request Body: 없음
- Response Body:
  - schedules[]
    - id
    - userId
    - username
    - title
    - contents
    - createdAt
    - updatedAt
- 설명:
  - 저장된 일정 전체를 조회한다.

### 3. 일정 단건 조회
- Method: GET
- URL: /schedules/{scheduleId}
- Request Body: 없음
- Response Body:
  - id
  - userId
  - username
  - title
  - contents
  - createdAt
  - updatedAt
- 설명:
  - scheduleId에 해당하는 일정 1개를 조회한다.
  
### 4. 일정 수정
- Method: PUT
- URL: /schedules/{scheduleId}
- Request Body:
  - title
  - contents
- Response Body:
  - id
  - userId
  - username
  - title
  - contents
  - createdAt
  - updatedAt
- 인증 방식: Cookie / Session
- 설명:
  - 로그인한 사용자의 세션 정보를 기준으로 본인 일정을 수정한다.

### 5. 일정 삭제
- Method: DELETE
- URL: /schedules/{scheduleId}
- Request Body: 없음
- Response Body: 없음
- 인증 방식: Cookie / Session
- 설명:
  - 로그인한 사용자의 세션 정보를 기준으로 본인 일정을 삭제한다.

### 6. 유저 생성
- Method: POST
- URL: /users
- Request Body:
  - username
  - email
  - password
- Response Body:
  - id
  - username
  - email
  - createdAt
  - updatedAt
- 설명:
  - 유저 정보를 생성한다.

### 7. 유저 전체 조회
- Method: GET
- URL: /users
- Request Body: 없음
- Response Body:
  - users[]
    - id
    - username
    - email
    - createdAt
    - updatedAt
- 설명:
  - 저장된 유저 전체를 조회한다.

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
- 설명:
  - userId에 해당하는 유저 1명을 조회한다.

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
- 설명:
  - userId에 해당하는 유저 정보를 수정한다.
  
### 10. 유저 삭제
- Method: DELETE
- URL: /users/{userId}
- Request Body: 없음
- Response Body: 없음
- 설명:
  - userId에 해당하는 유저를 삭제한다.

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
- 설명:
  - 비밀번호는 8장 이상이어야 하며, 회원가입 시 유저를 생성한다.

### 12. 로그인
- Method: POST
- URL: /login
- RequestBody:
  - email
  - password
- ResponseBody: 
  - message
- 인증 방식: Cookie / Session
- 설명:
  - 이메일과 비밀번호를 확인한 뒤, 로그인 성공 시 세션에 loginUserId를 저장한다.

## ERD

### User
- id
- username
- email
- password
- createdAt
- updatedAt

### Schedule
- id
- user_id
- title
- contents
- createdAt
- updatedAt

### 관계
- User1 : N schedule
- Schedule은 User를 참조하며, user_id(FK)로 연결된다.

