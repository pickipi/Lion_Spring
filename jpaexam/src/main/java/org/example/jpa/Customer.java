package org.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter@Setter
public class Customer {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private ContactInfo contactInfo;
}

@Embeddable
@Getter@Setter
class ContactInfo{
    private String email;
    private String phoneNumber;
    private String address;
    private String country;
}

@Slf4j
class CustomerMain{
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
//            ContactInfo contactInfo1 = new ContactInfo();
//            contactInfo1.setEmail("dominic@premier.com");
//            contactInfo1.setPhoneNumber("413-1111");
//            contactInfo1.setAddress("London");
//            contactInfo1.setCountry("England");
//
//            Customer customer1 = new Customer();
//            customer1.setName("Solanke");
//            customer1.setContactInfo(contactInfo1);
//
//            em.persist(customer1);
//            em.getTransaction().commit();

            // 데이터 조회
            List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
            for(Customer c : customers){
                log.info("Customer : " + c.getName());
                log.info("Email : " + c.getContactInfo().getEmail());
                log.info("PhoneNumber : " + c.getContactInfo().getPhoneNumber());
                log.info("Address : " + c.getContactInfo().getAddress());
                log.info("Country : " + c.getContactInfo().getCountry());
            }
        }finally{
            em.close();
        }
    }
}
