/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.dao;

import com.conexia.prueba.restaurantes.dao.exceptions.NonexistentEntityException;
import com.conexia.prueba.restaurantes.dao.exceptions.RollbackFailureException;
import com.conexia.prueba.restaurantes.model.Camarero;
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
public class CamareroDAO implements Serializable {

    private EntityManagerFactory emf = null;
    public CamareroDAO() {
       this.emf = Persistence.createEntityManagerFactory("restaurantesDB");;
    }
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Camarero camarero) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(camarero);
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

    public void edit(Camarero camarero) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            
            em = getEntityManager();
            em.getTransaction().begin();
            camarero = em.merge(camarero);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = camarero.getId();
                if (findCamarero(id) == null) {
                    throw new NonexistentEntityException("The camarero with id " + id + " no longer exists.");
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
            Camarero camarero;
            try {
                camarero = em.getReference(Camarero.class, id);
                camarero.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The camarero with id " + id + " no longer exists.", enfe);
            }
            em.remove(camarero);
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

    public List<Camarero> findCamareroEntities() {
        return findCamareroEntities(true, -1, -1);
    }

    public List<Camarero> findCamareroEntities(int maxResults, int firstResult) {
        return findCamareroEntities(false, maxResults, firstResult);
    }

    private List<Camarero> findCamareroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Camarero.class));
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

    public Camarero findCamarero(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Camarero.class, id);
        } finally {
            em.close();
        }
    }

    public int getCamareroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Camarero> rt = cq.from(Camarero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
