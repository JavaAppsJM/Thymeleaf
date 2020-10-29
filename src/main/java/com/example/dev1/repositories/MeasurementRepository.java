package com.example.dev1.repositories;

import com.example.dev1.domain.BellyMesurement;
import com.example.dev1.domain.Measurement;
import com.example.dev1.domain.MeasurementType;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MeasurementRepository {

    public <E extends Measurement> void addMeasurement(E measurement){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            System.out.println("Entering add");
            System.out.println("New belly measurement: " + measurement.toString());
            // Define
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Domain creations
            System.out.println(measurement.toString());
            tx.begin();
            em.persist(measurement);
            tx.commit();
            System.out.println("add finished");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    public List<Measurement> getAllMeasurements(MeasurementType measureType) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<Measurement> measurements = new ArrayList<>();

        try {
            System.out.println("Entering getAll");
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx =em.getTransaction();

            TypedQuery<Measurement> queryBel = em.createNamedQuery("getAllBellyMs", Measurement.class);
            TypedQuery<Measurement> queryBlood = em.createNamedQuery("getAllBloodMs", Measurement.class);

            tx.begin();
            if (measureType == MeasurementType.BELLY){
                measurements = queryBel.getResultList();
            }else{
                measurements = queryBlood.getResultList();
            }
            tx.commit();

            System.out.println("getAll finished");
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
            return measurements;
        }
    }

    public void deleteById(int id, MeasurementType measurementType) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Entering delete");
            System.out.println("id: " + id);
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            Measurement measurement;
            // Get employee by ID
            TypedQuery<Measurement> queryBel = em.createNamedQuery("getBellyMById", Measurement.class);
            TypedQuery<Measurement> queryBlood = em.createNamedQuery("getBloodMById", Measurement.class);

            tx.begin();
            if (measurementType == MeasurementType.BELLY){
                queryBel.setParameter("srchid",id);
                measurement = queryBel.getSingleResult();
            }else{
                queryBlood.setParameter("srchid",id);
                measurement = queryBlood.getSingleResult();
            }
            tx.commit();

            System.out.println("id found" + measurement.toString());
            // Remove employee
            tx.begin();
            em.remove(measurement);
            tx.commit();
            System.out.println("delete finished");
        } catch (Exception exception){
            System.out.println(exception.getMessage().toString());
            System.out.println("Delete is not executed");
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    public Measurement findById(int id, MeasurementType measurementType) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Measurement measurement;

        try {
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get employee by ID
            TypedQuery<Measurement> queryBel = em.createNamedQuery("getBellyMById", Measurement.class);
            TypedQuery<Measurement> queryBlood = em.createNamedQuery("getBloodMById", Measurement.class);

            tx.begin();
            if (measurementType == MeasurementType.BELLY){
                queryBel.setParameter("srchid",id);
                measurement = queryBel.getSingleResult();
            }else{
                queryBlood.setParameter("srchid",id);
                measurement = queryBlood.getSingleResult();
            }
            tx.commit();

            System.out.println("id found" + measurement.toString());
        } catch (Exception exception){
            System.out.println(exception.getMessage().toString());
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
            return measurement;
        }
    }

    public void update(BellyMesurement newBellyM) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Entering update");
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
