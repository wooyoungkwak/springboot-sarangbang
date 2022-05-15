[ JPA - Hibernate 주의점 ]
1. EntityManager 클래스는 @Autowired 가아니라 @PersistenceContext 를 사용한다.
2. QueryDSL 을 이용시 JPAQeuryFactory 클래스를 이용한다. 
3. 복잡한 쿼리는 MYBATIS 를 이용하는것이 시간적 낭비가 준다.

[ MYBATIS 주의점 ] 
1. enum 사용시 TypeHandler 를 이용하여 매핑을 반드시 시켜줘야함. 
2. application.yml(application.properties) 에서 dataSource 의 "url" 키를 "jdbc-url" 로 사용해야한다.
   그렇지 않으면 "url reqeust" 에러가 발생한다. 
