/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import controles.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SAccesos;
import entidades.SAplicaciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class SAplicacionesJpaController implements Serializable {

    public SAplicacionesJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SAplicaciones SAplicaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAccesos idAcceso = SAplicaciones.getIdAcceso();
            if (idAcceso != null) {
                idAcceso = em.getReference(idAcceso.getClass(), idAcceso.getIdAcceso());
                SAplicaciones.setIdAcceso(idAcceso);
            }
            em.persist(SAplicaciones);
            if (idAcceso != null) {
                idAcceso.getSAplicacionesCollection().add(SAplicaciones);
                idAcceso = em.merge(idAcceso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SAplicaciones SAplicaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAplicaciones persistentSAplicaciones = em.find(SAplicaciones.class, SAplicaciones.getIdAplicacion());
            SAccesos idAccesoOld = persistentSAplicaciones.getIdAcceso();
            SAccesos idAccesoNew = SAplicaciones.getIdAcceso();
            if (idAccesoNew != null) {
                idAccesoNew = em.getReference(idAccesoNew.getClass(), idAccesoNew.getIdAcceso());
                SAplicaciones.setIdAcceso(idAccesoNew);
            }
            SAplicaciones = em.merge(SAplicaciones);
            if (idAccesoOld != null && !idAccesoOld.equals(idAccesoNew)) {
                idAccesoOld.getSAplicacionesCollection().remove(SAplicaciones);
                idAccesoOld = em.merge(idAccesoOld);
            }
            if (idAccesoNew != null && !idAccesoNew.equals(idAccesoOld)) {
                idAccesoNew.getSAplicacionesCollection().add(SAplicaciones);
                idAccesoNew = em.merge(idAccesoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SAplicaciones.getIdAplicacion();
                if (findSAplicaciones(id) == null) {
                    throw new NonexistentEntityException("The sAplicaciones with id " + id + " no longer exists.");
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
            SAplicaciones SAplicaciones;
            try {
                SAplicaciones = em.getReference(SAplicaciones.class, id);
                SAplicaciones.getIdAplicacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SAplicaciones with id " + id + " no longer exists.", enfe);
            }
            SAccesos idAcceso = SAplicaciones.getIdAcceso();
            if (idAcceso != null) {
                idAcceso.getSAplicacionesCollection().remove(SAplicaciones);
                idAcceso = em.merge(idAcceso);
            }
            em.remove(SAplicaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SAplicaciones> findSAplicacionesEntities() {
        return findSAplicacionesEntities(true, -1, -1);
    }

    public List<SAplicaciones> findSAplicacionesEntities(int maxResults, int firstResult) {
        return findSAplicacionesEntities(false, maxResults, firstResult);
    }

    private List<SAplicaciones> findSAplicacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SAplicaciones.class));
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

    public SAplicaciones findSAplicaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SAplicaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getSAplicacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SAplicaciones> rt = cq.from(SAplicaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
