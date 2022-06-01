## 설정
1. 배포시 war 파일 생성 (이름 : ROOT.war)
2. 배포시 내장 tomcat 은 사용하지 않도록 설정

## 의존성 Gradle  (initProject.gradle 참조)
1. initDependencies.gradle
2. initFile.gradle 

## 주의 사항
[ JPA - Hibernate ]
1. EntityManager 클래스는 @Autowired 가아니라 @PersistenceContext 를 사용한다. 
   (모든 @Autowired 사용하여 DI 하는것 보다는 @RequiredArgsConstructor 를 사용하여 DI 가 되도록 하는것이 좋다.)
2. QueryDSL 을 이용시 JPAQueryFactory 클래스를 이용한다. 
3. 복잡한 쿼리는 MYBATIS 를 이용하는것이 시간적 낭비가 준다.

[ Mybatis ] 
1. enum 사용시 TypeHandler 를 이용하여 매핑을 반드시 시켜줘야함. 
2. application.yml(application.properties) 에서 dataSource 의 "url" 키를 "jdbc-url" 로 사용해야한다.
   그렇지 않으면 "url request" 에러가 발생한다.