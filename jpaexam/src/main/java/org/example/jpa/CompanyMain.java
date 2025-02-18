package org.example.jpa;

import jakarta.persistence.EntityManager;

public class CompanyMain {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Address address1 = new Address();
            address1.setCity("Fukuoka");
            address1.setCountry("Japan");
            address1.setStreet("Daimyo");
            address1.setZipCode("11111");

            Company company1 = new Company();
            company1.setName("복호두");
            company1.setAddress(address1);

            em.persist(company1);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
}
