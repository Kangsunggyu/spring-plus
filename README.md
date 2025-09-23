# SPRING PLUS

0. 
- application.yml이 포함되어 있지 않았습니다.
- DB 비밀번호, JWT Secret Key 같은 민감한 정보는 하드코딩하지 않고 .env 파일에 보관했습니다.

1. 코드 개선 퀴즈 - @Transactional의 이해
- todo 컨트롤러의 saveTodo를 postman에서 실행하면 오류가 발생한다.
- 원인은 service 코드에 모든 트랜젝션에 readOnly = true를 붙였다.
- readOnly = true는 조회 성능을 최적화를 위해 사용한다. CRUD 중 READ의 최적화를 위해 사용하기에 CUD에서는 오류가 발생한다.

2. 코드 추가 퀴즈 - JWT의 이해
- user 필드에 nickname을 추가한다.
- User 엔티티 nickname 필드 추가 
- 회원가입 시 nickname을 입력해야 하기에 SignupRequest 코드 변경
- user 정보를 get할 시 nickname을 보여야 하기에 UserResponse 코드 변경
- nickname을 추가했기에, jwt 등을 코드 변경

3. 코드 개선 퀴즈 -  JPA의 이해
- todoService, todoRepository, todoContoller 모두 변경

4. 테스트 코드 퀴즈 - 컨트롤러 테스트의 이해
- 예외가 발생해야 하기에 ok를 badRequest로 변환 

5. 코드 개선 퀴즈 - AOP의 이해
- UserController.getUser(..) 메서드가 끝난 후(@After) 실행되도록 되어 있다. @After 이기에
- 이것을 @Before로 바꾼다. 

6. JPA Cascade
- TodoEntity에 **cascade = CascadeType.PERSIST)** 추가

7. N+1
- n+1 문제를 해결하기 위해 CommentRepository에 join fetch 사용

9. Spring Security
- 세큐리티 사용해보기
- AUAR, FilterConfig, JwtFilter, WebConfig 코드를 주석으로 만든다.
- JwtAuthenticationToken, JwtAuthenticationFilter, SecurityConfig 클래스 추가 

10. transaction 
- log 패키지를 추가해 log가 정상작동하도록 했다.
- 이 부분은 사실 제작은 했지만, 이해가 잘 되지 않아 더 공부해야 한다.