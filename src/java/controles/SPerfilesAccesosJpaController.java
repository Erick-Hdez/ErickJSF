/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import controles.exceptions.NonexistentEntityException;
import controles.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SAccesos;
import entidades.SAccesos_;
import entidades.SPerfiles;
import entidades.SPerfilesAccesos;
import entidades.SPerfilesAccesosPK;
import entidades.SPerfilesAccesos_;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.JoinType;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Erick Corral
 */
public class SPerfilesAccesosJpaController implements Serializable {

    public SPerfilesAccesosJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SPerfilesAccesos SPerfilesAccesos) throws PreexistingEntityException, Exception {
        if (SPerfilesAccesos.getSPerfilesAccesosPK() == null) {
            SPerfilesAccesos.setSPerfilesAccesosPK(new SPerfilesAccesosPK());
        }
        SPerfilesAccesos.getSPerfilesAccesosPK().setIdPerfil(SPerfilesAccesos.getSPerfiles().getIdPerfil());
        SPerfilesAccesos.getSPerfilesAccesosPK().setIdAcceso(SPerfilesAccesos.getSAccesos().getIdAcceso());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAccesos SAccesos = SPerfilesAccesos.getSAccesos();
            if (SAccesos != null) {
                SAccesos = em.getReference(SAccesos.getClass(), SAccesos.getIdAcceso());
                SPerfilesAccesos.setSAccesos(SAccesos);
            }
            SPerfiles SPerfiles = SPerfilesAccesos.getSPerfiles();
            if (SPerfiles != null) {
                SPerfiles = em.getReference(SPerfiles.getClass(), SPerfiles.getIdPerfil());
                SPerfilesAccesos.setSPerfiles(SPerfiles);
            }
            em.persist(SPerfilesAccesos);
            if (SAccesos != null) {
                SAccesos.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                SAccesos = em.merge(SAccesos);
            }
            if (SPerfiles != null) {
                SPerfiles.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                SPerfiles = em.merge(SPerfiles);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSPerfilesAccesos(SPerfilesAccesos.getSPerfilesAccesosPK()) != null) {
                throw new PreexistingEntityException("SPerfilesAccesos " + SPerfilesAccesos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SPerfilesAccesos SPerfilesAccesos) throws NonexistentEntityException, Exception {
        SPerfilesAccesos.getSPerfilesAccesosPK().setIdPerfil(SPerfilesAccesos.getSPerfiles().getIdPerfil());
        SPerfilesAccesos.getSPerfilesAccesosPK().setIdAcceso(SPerfilesAccesos.getSAccesos().getIdAcceso());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfilesAccesos persistentSPerfilesAccesos = em.find(SPerfilesAccesos.class, SPerfilesAccesos.getSPerfilesAccesosPK());
            SAccesos SAccesosOld = persistentSPerfilesAccesos.getSAccesos();
            SAccesos SAccesosNew = SPerfilesAccesos.getSAccesos();
            SPerfiles SPerfilesOld = persistentSPerfilesAccesos.getSPerfiles();
            SPerfiles SPerfilesNew = SPerfilesAccesos.getSPerfiles();
            if (SAccesosNew != null) {
                SAccesosNew = em.getReference(SAccesosNew.getClass(), SAccesosNew.getIdAcceso());
                SPerfilesAccesos.setSAccesos(SAccesosNew);
            }
            if (SPerfilesNew != null) {
                SPerfilesNew = em.getReference(SPerfilesNew.getClass(), SPerfilesNew.getIdPerfil());
                SPerfilesAccesos.setSPerfiles(SPerfilesNew);
            }
            SPerfilesAccesos = em.merge(SPerfilesAccesos);
            if (SAccesosOld != null && !SAccesosOld.equals(SAccesosNew)) {
                SAccesosOld.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                SAccesosOld = em.merge(SAccesosOld);
            }
            if (SAccesosNew != null && !SAccesosNew.equals(SAccesosOld)) {
                SAccesosNew.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                SAccesosNew = em.merge(SAccesosNew);
            }
            if (SPerfilesOld != null && !SPerfilesOld.equals(SPerfilesNew)) {
                SPerfilesOld.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                SPerfilesOld = em.merge(SPerfilesOld);
            }
            if (SPerfilesNew != null && !SPerfilesNew.equals(SPerfilesOld)) {
                SPerfilesNew.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                SPerfilesNew = em.merge(SPerfilesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SPerfilesAccesosPK id = SPerfilesAccesos.getSPerfilesAccesosPK();
                if (findSPerfilesAccesos(id) == null) {
                    throw new NonexistentEntityException("The sPerfilesAccesos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SPerfilesAccesosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfilesAccesos SPerfilesAccesos;
            try {
                SPerfilesAccesos = em.getReference(SPerfilesAccesos.class, id);
                SPerfilesAccesos.getSPerfilesAccesosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SPerfilesAccesos with id " + id + " no longer exists.", enfe);
            }
            SAccesos SAccesos = SPerfilesAccesos.getSAccesos();
            if (SAccesos != null) {
                SAccesos.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                SAccesos = em.merge(SAccesos);
            }
            SPerfiles SPerfiles = SPerfilesAccesos.getSPerfiles();
            if (SPerfiles != null) {
                SPerfiles.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                SPerfiles = em.merge(SPerfiles);
            }
            em.remove(SPerfilesAccesos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SPerfilesAccesos> findSPerfilesAccesosEntities() {
        return findSPerfilesAccesosEntities(true, -1, -1);
    }

    public List<SPerfilesAccesos> findSPerfilesAccesosEntities(int maxResults, int firstResult) {
        return findSPerfilesAccesosEntities(false, maxResults, firstResult);
    }

    private List<SPerfilesAccesos> findSPerfilesAccesosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SPerfilesAccesos.class));
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

    public SPerfilesAccesos findSPerfilesAccesos(SPerfilesAccesosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SPerfilesAccesos.class, id);
        } finally {
            em.close();
        }
    }

    public int getSPerfilesAccesosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SPerfilesAccesos> rt = cq.from(SPerfilesAccesos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<SPerfilesAccesos> traerAccesosByPerfil(SPerfiles perfil) {
        List<SPerfilesAccesos> listaAccesos = new ArrayList<>();
        if (perfil != null) {

            //SELECT r FROM RPerfilAcceso r WHERE r.rPerfilAccesoPK.idPerfil = :idPerfil
            EntityManager em = getEntityManager();
            Query query = null;
            try {

                query = em.createNamedQuery("SPerfilesAccesos.findByIdPerfil", SPerfilesAccesos.class).setParameter("idPerfil", perfil.getIdPerfil());

                listaAccesos = query.getResultList();

            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaAccesos;
    }

    public List<SAccesos> traerAccesosAsignados(SPerfiles idPerfil) {
        List<SAccesos> lista = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SAccesos> query = cb.createQuery(SAccesos.class);
            Root<SAccesos> perfil = query.from(SAccesos.class);
            CollectionJoin<SAccesos, SPerfilesAccesos> usuarioPerfil = perfil.join(SAccesos_.sPerfilesAccesosCollection);
            query.select(perfil).where(cb.equal(usuarioPerfil.get(SPerfilesAccesos_.sPerfiles), idPerfil));
            TypedQuery<SAccesos> typedQuery = em.createQuery(query);

            lista = typedQuery.getResultList();
        } catch (Exception ex) {

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return lista;
    }

    public List<SAccesos> traerAccesosDisponibles(SPerfiles idPerfil) {
        List<SAccesos> lista = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SAccesos> query = cb.createQuery(SAccesos.class);
            Root<SAccesos> perfil = query.from(SAccesos.class);
            CollectionJoin<SAccesos, SPerfilesAccesos> usuarioPerfil = perfil.join(SAccesos_.sPerfilesAccesosCollection, JoinType.LEFT);
            usuarioPerfil.on(cb.equal(usuarioPerfil.get(SPerfilesAccesos_.sPerfiles), idPerfil));
            query.select(perfil).where(cb.isNull(usuarioPerfil.get(SPerfilesAccesos_.sPerfiles)));
            TypedQuery<SAccesos> typedQuery = em.createQuery(query);

            lista = typedQuery.getResultList();
        } catch (Exception ex) {

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return lista;
    }
}
