package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.ActivityLog;
import jpabasic.reserve.jpa.EMFOracle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainSequence {
    private static Logger logger = LoggerFactory.getLogger(MainSequence.class);

    public static void main(String[] args) {
        EMFOracle.init();
        EntityManager em = EMFOracle.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // 생성 시점에 식별자 지정하지 않음
            ActivityLog log = new ActivityLog("U01", "VISIT");
            logger.info("persist 실행 전");
            // persist() 시점에 시퀀스로 식별자 구함
            em.persist(log);
            logger.info("persist 실행 함");
            logger.info("생성한 식별자: {}", log.getId());
            logger.info("커밋하기 전");
            // 커밋 시점에 INSERT쿼리 실행
            tx.commit();
            logger.info("커밋함");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        EMFOracle.close();
    }
}
