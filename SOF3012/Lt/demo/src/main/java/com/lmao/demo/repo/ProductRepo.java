package com.lmao.demo.repo;

import com.lmao.demo.enity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProductRepo {
    private EntityManagerFactory enity = Persistence.createEntityManagerFactory("default");
    private EntityManager em = enity.createEntityManager();

    public List<Product> getProduct() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}
