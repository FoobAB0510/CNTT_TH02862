package com.lmao.demo;

import com.lmao.demo.enity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory enity = Persistence.createEntityManagerFactory("default");

        EntityManager em = enity.createEntityManager();

        Student stu = new Student(10004L, "Stfu 3", "Email3", "Phone4");

        em.getTransaction().begin();
        em.persist(stu);
        em.getTransaction().commit();

//        em.createNativeQuery("SELECT * FROM student", Student.class).getResultList().forEach(System.out::println); // Natvie
//        em.createQuery("SELECT s From Student s", Student.class).getResultList().forEach(System.out::println); // JPSQL
        System.out.println("Done...");
    }
}
