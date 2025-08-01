package com.lmao.demo.repo;

import com.lmao.demo.enity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class StudentRepo {
    private EntityManagerFactory enity = Persistence.createEntityManagerFactory("default");
    private EntityManager em = enity.createEntityManager();

    public List<Student> getStudent() {
        return em.createQuery("SELECT s From Student s", Student.class).getResultList();
    }

    public void addStudent(Student student) {

        em.getTransaction().begin();

        em.persist(student);

        em.getTransaction().commit();
    }

    public void deleteStudent(Long id) {

        em.getTransaction().begin();

        em.remove(em.find(Student.class, id));

        em.getTransaction().commit();
    }

    public Student getStudentById(Long id) {
        return em.find(Student.class, id);
    }

    public void updateStudent(Student student) {

        em.getTransaction().begin();

        em.merge(student);

        em.getTransaction().commit();
    }
}
