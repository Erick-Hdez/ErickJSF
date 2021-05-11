/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import controles.exceptions.NonexistentEntityException;
import entidades.SAccesos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SAplicaciones;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class SAccesosJpaController implements Serializable {

    public SAccesosJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SAccesos SAccesos) {
        if (SAccesos.getSAplicacionesCollection() == null) {
            SAccesos.setSAplicacionesCollection(new ArrayList<SAplicaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<SAplicaciones> attachedSAplicacionesCollection = new ArrayList<SAplicaciones>();
            for (SAplicaciones SAplicacionesCollectionSAplicacionesToAttach : SAccesos.getSAplicacionesCollection()) {
                SAplicacionesCollectionSAplicacionesToAttach = em.getReference(SAplicacionesCollectionSAplicacionesToAttach.getClass(), SAplicacionesCollectionSAplicacionesToAttach.getIdAplicacion());
                attachedSAplicacionesCollection.add(SAplicacionesCollectionSAplicacionesToAttach);
            }
            SAccesos.setSAplicacionesCollection(attachedSAplicacionesCollection);
            em.persist(SAccesos);
            for (SAplicaciones SAplicacionesCollectionSAplicaciones : SAccesos.getSAplicacionesCollection()) {
                SAccesos oldIdAccesoOfSAplicacionesCollectionSAplicaciones = SAplicacionesCollectionSAplicaciones.getIdAcceso();
                SAplicacionesCollectionSAplicaciones.setIdAcceso(SAccesos);
                SAplicacionesCollectionSAplicaciones = em.merge(SAplicacionesCollectionSAplicaciones);
                if (oldIdAccesoOfSAplicacionesCollectionSAplicaciones != null) {
                    oldIdAccesoOfSAplicacionesCollectionSAplicaciones.getSAplicacionesCollection().remove(SAplicacionesCollectionSAplicaciones);
                    oldIdAccesoOfSAplicacionesCollectionSAplicaciones = em.merge(oldIdAccesoOfSAplicacionesCollectionSAplicaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SAccesos SAccesos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAccesos persistentSAccesos = em.find(SAccesos.class, SAccesos.getIdAcceso());
            Collection<SAplicaciones> SAplicacionesCollectionOld = persistentSAccesos.getSAplicacionesCollection();
            Collection<SAplicaciones> SAplicacionesCollectionNew = SAccesos.getSAplicacionesCollection();
            Collection<SAplicaciones> attachedSAplicacionesCollectionNew = new ArrayList<SAplicaciones>();
            for (SAplicaciones SAplicacionesCollectionNewSAplicacionesToAttach : SAplicacionesCollectionNew) {
                SAplicacionesCollectionNewSAplicacionesToAttach = em.getReference(SAplicacionesCollectionNewSAplicacionesToAttach.getClass(), SAplicacionesCollectionNewSAplicacionesToAttach.getIdAplicacion());
                attachedSAplicacionesCollectionNew.add(SAplicacionesCollectionNewSAplicacionesToAttach);
            }
            SAplicacionesCollectionNew = attachedSAplicacionesCollectionNew;
            SAccesos.setSAplicacionesCollection(SAplicacionesCollectionNew);
            SAccesos = em.merge(SAccesos);
            for (SAplicaciones SAplicacionesCollectionOldSAplicaciones : SAplicacionesCollectionOld) {
                if (!SAplicacionesCollectionNew.contains(SAplicacionesCollectionOldSAplicaciones)) {
                    SAplicacionesCollectionOldSAplicaciones.setIdAcceso(null);
                    SAplicacionesCollectionOldSAplicaciones = em.merge(SAplicacionesCollectionOldSAplicaciones);
                }
            }
            for (SAplicaciones SAplicacionesCollectionNewSAplicaciones : SAplicacionesCollectionNew) {
                if (!SAplicacionesCollectionOld.contains(SAplicacionesCollectionNewSAplicaciones)) {
                    SAccesos oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones = SAplicacionesCollectionNewSAplicaciones.getIdAcceso();
                    SAplicacionesCollectionNewSAplicaciones.setIdAcceso(SAccesos);
                    SAplicacionesCollectionNewSAplicaciones = em.merge(SAplicacionesCollectionNewSAplicaciones);
                    if (oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones != null && !oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones.equals(SAccesos)) {
                        oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones.getSAplicacionesCollection().remove(SAplicacionesCollectionNewSAplicaciones);
                        oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones = em.merge(oldIdAccesoOfSAplicacionesCollectionNewSAplicaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SAccesos.getIdAcceso();
                if (findSAccesos(id) == null) {
                    throw new NonexistentEntityException("The sAccesos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAccesos SAccesos;
            try {
                SAccesos = em.getReference(SAccesos.class, id);
                SAccesos.getIdAcceso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SAccesos with id " + id + " no longer exists.", enfe);
            }
            Collection<SAplicaciones> SAplicacionesCollection = SAccesos.getSAplicacionesCollection();
            for (SAplicaciones SAplicacionesCollectionSAplicaciones : SAplicacionesCollection) {
                SAplicacionesCollectionSAplicaciones.setIdAcceso(null);
                SAplicacionesCollectionSAplicaciones = em.merge(SAplicacionesCollectionSAplicaciones);
            }
            em.remove(SAccesos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SAccesos> findSAccesosEntities() {
        return findSAccesosEntities(true, -1, -1);
    }

    public List<SAccesos> findSAccesosEntities(int maxResults, int firstResult) {
        return findSAccesosEntities(false, maxResults, firstResult);
    }

    private List<SAccesos> findSAccesosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SAccesos.class));
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

    public SAccesos findSAccesos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SAccesos.class, id);
        } finally {
            em.close();
        }
    }

    public int getSAccesosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SAccesos> rt = cq.from(SAccesos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
