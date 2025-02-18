package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SportsMain {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
//            // Football
//            Football fb = new Football();
//            fb.setCountry("England");
//            fb.setUniformColor("BlackStripe");
//
//            // Basketball
//            Basketball bb = new Basketball();
//            bb.setCountry("United States of America");
//            bb.setPlayerNumber(5);
//
//            em.persist(fb);
//            em.persist(bb);

            // 데이터 조회
            Sports sports = em.find(Sports.class, 1L);
            if(sports instanceof Football){
                Football football = (Football)sports;
                log.info("ID : " + football.getId() + "::: 유니폼 색깔 : " + football.getUniformColor());
            }else if(sports instanceof Basketball){
                Basketball basketball = (Basketball)sports;
                log.info("ID : " + basketball.getId() + "::: 인원 수 : " + basketball.getPlayerNumber());
            }

            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
}
