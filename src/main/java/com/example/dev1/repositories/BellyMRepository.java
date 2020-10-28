package com.example.dev1.repositories;

import com.example.dev1.domain.BellyMesurement;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BellyMRepository {

    public void addBellyM(BellyMesurement bellyMesurement){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Domain creations
            System.out.println(bellyMesurement.toString());
            tx.begin();
            em.persist(bellyMesurement);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    public List<BellyMesurement> getAllBellyMs() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<BellyMesurement> bellyMesurements = new ArrayList<>();

        try {
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx =em.getTransaction();
            TypedQuery<BellyMesurement> query = em.createNamedQuery("getAllBellyMs", BellyMesurement.class);

            tx.begin();
            bellyMesurements = query.getResultList();
            tx.commit();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
            return bellyMesurements;
        }
    }

    public void deleteById(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get employee by ID
            TypedQuery<BellyMesurement> queryEmp = em.createNamedQuery("getBellyMById", BellyMesurement.class);
            queryEmp.setParameter("srchid",id);
            tx.begin();
            BellyMesurement bellyMesurement = queryEmp.getSingleResult();
            tx.commit();
            // Remove employee
            tx.begin();
            em.remove(bellyMesurement);
            tx.commit();
        } catch (Exception exception){
            System.out.println(exception.getMessage().toString());
            System.out.println("Delete is not executed");
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    public BellyMesurement findById(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        BellyMesurement bellyMesurement = new BellyMesurement();

        try {
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get employee by ID
            TypedQuery<BellyMesurement> queryEmp = em.createNamedQuery("getBellyMById", BellyMesurement.class);
            queryEmp.setParameter("srchid",id);
            tx.begin();
            bellyMesurement = queryEmp.getSingleResult();
            tx.commit();
        } catch (Exception exception){
            System.out.println(exception.getMessage().toString());
            System.out.println("Delete is not executed");
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
            return bellyMesurement;
        }
    }

    public void update(BellyMesurement newBellyM) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Entering update Hib");
            System.out.println("New belly mesurement: " + newBellyM.toString());
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get employee by ID
            TypedQuery<BellyMesurement> queryEmp = em.createNamedQuery("getBellyMById", BellyMesurement.class);
            queryEmp.setParameter("srchid",newBellyM.getMesureId());
            tx.begin();
            BellyMesurement oldBellyM = queryEmp.getSingleResult();
            tx.commit();
            System.out.println("Old belly mesurement: " + oldBellyM.toString());
            // Update employee in persistence area
            tx.begin();
            oldBellyM.setCircumRef(newBellyM.getCircumRef());
            oldBellyM.setMesureDate(newBellyM.getMesureDate());
            tx.commit();
            System.out.println("Update executed");
        } catch (Exception exception){
            System.out.println(exception.getMessage().toString());
            System.out.println("Update is not executed");
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

}
