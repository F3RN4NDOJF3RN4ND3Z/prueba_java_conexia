/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.dao;

import com.conexia.prueba.restaurantes.dao.exceptions.NonexistentEntityException;
import com.conexia.prueba.restaurantes.dao.exceptions.RollbackFailureException;
import com.conexia.prueba.restaurantes.model.Cocinero;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Fernandojavier
 */
public class CocineroDAO implements Serializable {

    private EntityManagerFactory emf = null;
    public CocineroDAO() {
       this.emf = Persistence.createEntityManagerFactory("restaurantesDB");;
    }
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cocinero cocinero) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cocinero);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cocinero cocinero) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
           
            em = getEntityManager();
            em.getTransaction().begin();
            cocinero = em.merge(cocinero);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cocinero.getId();
                if (findCocinero(id) == null) {
                    throw new NonexistentEntityException("The cocinero with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            
            em = getEntityManager();
            em.getTransaction().begin();
            Cocinero cocinero;
            try {
                cocinero = em.getReference(Cocinero.class, id);
                cocinero.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cocinero with id " + id + " no longer exists.", enfe);
            }
            em.remove(cocinero);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cocinero> findCocineroEntities() {
        return findCocineroEntities(true, -1, -1);
    }

    public List<Cocinero> findCocineroEntities(int maxResults, int firstResult) {
        return findCocineroEntities(false, maxResults, firstResult);
    }

    private List<Cocinero> findCocineroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cocinero.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cocinero findCocinero(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cocinero.class, id);
        } finally {
            em.close();
        }
    }

    public int getCocineroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cocinero> rt = cq.from(Cocinero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
