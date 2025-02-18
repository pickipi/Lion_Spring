package org.example.jpa;

import jakarta.persistence.EntityManager;

public class VehicleMain {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try{
            // Car
            Car car = new Car();
            car.setManufacturer("Samsung");
            car.setSeatCount(5);

            // Bus
            Bus bus = new Bus();
            bus.setManufacturer("고속버스");
            bus.setBusColor("Green");

            // Truck
            Truck truck = new Truck();
            truck.setManufacturer("Bongo");
            truck.setPayloadCapacity(500);

            // Taxi
            Taxi taxi = new Taxi();
            taxi.setManufacturer("Kakao");
            taxi.setPassengerGender("Male");

            em.persist(car);
            em.persist(bus);
            em.persist(truck);
            em.persist(taxi);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
}
