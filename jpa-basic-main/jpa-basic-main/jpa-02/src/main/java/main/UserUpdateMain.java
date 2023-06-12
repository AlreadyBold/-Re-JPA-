package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabasic.reserve.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserUpdateMain {
    private static Logger logger = LoggerFactory.getLogger(UserUpdateMain.class);

    public static void main(String[] args) {

        // 필요한 자원생성 / persistence.xml 파일에 정의한 영속단위 기준으로 초기화됨
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");

        // EntityManager 생성
        EntityManager entityManager = emf.createEntityManager();
        // EntityTransaction 구하기
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // 트랜잭션 시작
            transaction.begin();
            User user = entityManager.find(User.class, "user@user.com");
            if (user == null) {
                System.out.println("User 없음");
            } else {
                String newName = "이름" + (System.currentTimeMillis() % 100);
                user.changeName(newName);
                logger.info("User.changeName 호출함");
            }
            // 트랜잭션 커밋
            transaction.commit();
            logger.info("EntityTransaction.commit 호출함");
        } catch (Exception ex) {
            logger.error("에러 발생: " + ex.getMessage(), ex);
            // 에러발생할 경우 롤백 하는 예외처리
            transaction.rollback();
        } finally {
            // EntityManager 닫기
            entityManager.close();
        }
        // 팩토리 닫음 , 사용한 자원 반환
        emf.close();
    }
}
