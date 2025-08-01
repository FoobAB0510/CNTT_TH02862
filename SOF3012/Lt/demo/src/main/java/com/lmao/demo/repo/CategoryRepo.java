package com.lmao.demo.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CategoryRepo {
    private EntityManagerFactory enity = Persistence.createEntityManagerFactory("default");
    private EntityManager em = enity.createEntityManager();


}
