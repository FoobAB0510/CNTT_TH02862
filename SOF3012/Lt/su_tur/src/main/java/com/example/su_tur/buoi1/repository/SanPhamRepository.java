package com.example.su_tur.buoi1.repository;

import com.example.su_tur.buoi1.model.SanPham;
import com.example.su_tur.buoi1.util.HibernateConfig;
import org.hibernate.Session;

import java.util.List;

public class SanPhamRepository {

    private Session session = null;

    public SanPhamRepository() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<SanPham> getAll() {
        return session.createQuery("from SanPham").list(); // JPQL - huon den class
    }
}
