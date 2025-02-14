package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorMain {
    // 데이터 조회
    private static void find(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
//            Author author = em.find(Author.class, 2L);
//            String findAuthor = author.getName();
//            log.info("[SELECT] [ID=2] Author : {}", findAuthor);
//
//            for(Book book : author.getBooks()){
//                log.info("Book [{}]'s Author : [{}]", book.getTitle(), findAuthor);
//            }
            em.find(Book.class, 1L);
        }finally{
            em.close();
        }
    }

    // 데이터 생성
    private static void create(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            // 1. Author 생성
            Author author = new Author("김소월");

            // 2. Books 생성
            author.getBooks().add(new Book("진달래꽃", author));
            author.getBooks().add(new Book("가는 길", author));

            em.persist(author);
            em.getTransaction().commit();

            log.info("[CREATE] Author : {}", author.getName());
            for(Book book : author.getBooks()){
                log.info("[CREATE] Book [{}]'s Author : [{}]", book.getTitle(), author.getName());
            }
        }finally{
            em.close();
        }
    }

    // 데이터 수정
    private static void update(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Author author = em.find(Author.class, 2L);
            String beforeAuthor = author.getName();
            author.setName("Mark Zuckerberg");

            em.persist(author);
            em.getTransaction().commit();

            log.info("[UPDATE] Author : {} -> {}", beforeAuthor, author.getName());
        }finally{
            em.close();
        }
    }

    // 데이터 삭제
    private static void delete(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Author author = em.find(Author.class, 4L);
            String deletedAuthor = author.getName();

            em.remove(author);
            em.getTransaction().commit();

            log.info("[DELETE] Author : {} -> [삭제완료]", deletedAuthor);
        }finally{
            em.close();
        }
    }

    public static void main(String[] args) {
        find();
//        create();
//        update();
//        delete();
    }
}
