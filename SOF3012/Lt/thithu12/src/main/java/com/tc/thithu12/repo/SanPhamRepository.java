package com.tc.thithu12.repo;
// pls import HibernateConfig

import com.tc.thithu12.model.SanPham;
import com.tc.thithu12.util.HibernateConfig;
import org.hibernate.Session;

import java.util.List;

public class SanPhamRepository {

    private Session session;

    public SanPhamRepository() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<SanPham> getAll() {
        return session.createQuery("FROM SanPham").list();
    }

    public SanPham getOne(Integer id) {
        return session.find(SanPham.class, id);
    }

    public void update(SanPham enityUpdate) {
        try {
            session.getTransaction().begin();
            session.merge(enityUpdate);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(SanPham enityDelete) {
        try {
            session.getTransaction().begin();
            session.delete(enityDelete);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void add(SanPham enityAdd) {
        try {
            session.getTransaction().begin();
            session.save(enityAdd);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
