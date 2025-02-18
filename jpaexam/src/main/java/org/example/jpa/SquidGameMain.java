package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SquidGameMain {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
//            // Dalgona
//            Dalgona dg = new Dalgona();
//            dg.setSurvivor(300); // 300명 생존
//            dg.setShape("Umbrella"); // 삼각형
//
//            // Gonggi
//            Gonggi gg = new Gonggi();
//            gg.setSurvivor(150);
//            gg.setPassMinNumber(4); // 공깃돌 4개 이상 잡으면 통과
//
//            em.persist(dg);
//            em.persist(gg);

            // 데이터 조회
            SquidGame sg = em.find(SquidGame.class, 1L);
            if(sg instanceof Dalgona){
                Dalgona dalgona = (Dalgona)sg;
                log.info("[달고나 게임] 생존자 : " + dalgona.getSurvivor() + ", 달고나 모양 : " + dalgona.getShape());
            }else if(sg instanceof Gonggi){
                Gonggi gonggi = (Gonggi)sg;
                log.info("[공기 놀이] 생존자 : " + gonggi.getSurvivor() + ", 통과 개수 : " + gonggi.getPassMinNumber());
            }
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
}
