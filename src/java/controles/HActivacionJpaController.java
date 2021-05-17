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
import entidades.CCiudad;
import entidades.CClientes;
import entidades.CDistribuidor;
import entidades.HActivacion;
import entidades.SUsuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class HActivacionJpaController implements Serializable {

    public HActivacionJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HActivacion HActivacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CCiudad idCiudad = HActivacion.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                HActivacion.setIdCiudad(idCiudad);
            }
            CClientes idCliente = HActivacion.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                HActivacion.setIdCliente(idCliente);
            }
            CDistribuidor idDistribuidor = HActivacion.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor = em.getReference(idDistribuidor.getClass(), idDistribuidor.getIdDistribuidor());
                HActivacion.setIdDistribuidor(idDistribuidor);
            }
            SUsuarios idUsuario = HActivacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                HActivacion.setIdUsuario(idUsuario);
            }
            em.persist(HActivacion);
            if (idCiudad != null) {
                idCiudad.getHActivacionCollection().add(HActivacion);
                idCiudad = em.merge(idCiudad);
            }
            if (idCliente != null) {
                idCliente.getHActivacionCollection().add(HActivacion);
                idCliente = em.merge(idCliente);
            }
            if (idDistribuidor != null) {
                idDistribuidor.getHActivacionCollection().add(HActivacion);
                idDistribuidor = em.merge(idDistribuidor);
            }
            if (idUsuario != null) {
                idUsuario.getHActivacionCollection().add(HActivacion);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HActivacion HActivacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HActivacion persistentHActivacion = em.find(HActivacion.class, HActivacion.getId());
            CCiudad idCiudadOld = persistentHActivacion.getIdCiudad();
            CCiudad idCiudadNew = HActivacion.getIdCiudad();
            CClientes idClienteOld = persistentHActivacion.getIdCliente();
            CClientes idClienteNew = HActivacion.getIdCliente();
            CDistribuidor idDistribuidorOld = persistentHActivacion.getIdDistribuidor();
            CDistribuidor idDistribuidorNew = HActivacion.getIdDistribuidor();
            SUsuarios idUsuarioOld = persistentHActivacion.getIdUsuario();
            SUsuarios idUsuarioNew = HActivacion.getIdUsuario();
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                HActivacion.setIdCiudad(idCiudadNew);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                HActivacion.setIdCliente(idClienteNew);
            }
            if (idDistribuidorNew != null) {
                idDistribuidorNew = em.getReference(idDistribuidorNew.getClass(), idDistribuidorNew.getIdDistribuidor());
                HActivacion.setIdDistribuidor(idDistribuidorNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                HActivacion.setIdUsuario(idUsuarioNew);
            }
            HActivacion = em.merge(HActivacion);
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getHActivacionCollection().remove(HActivacion);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getHActivacionCollection().add(HActivacion);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getHActivacionCollection().remove(HActivacion);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getHActivacionCollection().add(HActivacion);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idDistribuidorOld != null && !idDistribuidorOld.equals(idDistribuidorNew)) {
                idDistribuidorOld.getHActivacionCollection().remove(HActivacion);
                idDistribuidorOld = em.merge(idDistribuidorOld);
            }
            if (idDistribuidorNew != null && !idDistribuidorNew.equals(idDistribuidorOld)) {
                idDistribuidorNew.getHActivacionCollection().add(HActivacion);
                idDistribuidorNew = em.merge(idDistribuidorNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getHActivacionCollection().remove(HActivacion);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getHActivacionCollection().add(HActivacion);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = HActivacion.getId();
                if (findHActivacion(id) == null) {
                    throw new NonexistentEntityException("The hActivacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HActivacion HActivacion;
            try {
                HActivacion = em.getReference(HActivacion.class, id);
                HActivacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The HActivacion with id " + id + " no longer exists.", enfe);
            }
            CCiudad idCiudad = HActivacion.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getHActivacionCollection().remove(HActivacion);
                idCiudad = em.merge(idCiudad);
            }
            CClientes idCliente = HActivacion.getIdCliente();
            if (idCliente != null) {
                idCliente.getHActivacionCollection().remove(HActivacion);
                idCliente = em.merge(idCliente);
            }
            CDistribuidor idDistribuidor = HActivacion.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor.getHActivacionCollection().remove(HActivacion);
                idDistribuidor = em.merge(idDistribuidor);
            }
            SUsuarios idUsuario = HActivacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getHActivacionCollection().remove(HActivacion);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(HActivacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HActivacion> findHActivacionEntities() {
        return findHActivacionEntities(true, -1, -1);
    }

    public List<HActivacion> findHActivacionEntities(int maxResults, int firstResult) {
        return findHActivacionEntities(false, maxResults, firstResult);
    }

    private List<HActivacion> findHActivacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HActivacion.class));
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

    public HActivacion findHActivacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HActivacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHActivacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HActivacion> rt = cq.from(HActivacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    // Criteria Query
    public List<HActivacion> crearReporteActivacion(Date fechaInicio, Date fechaFinal) {

        List<HActivacion> listaActivacion = new ArrayList<>();

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<HActivacion> cq = cb.createQuery(HActivacion.class);

            Root<HActivacion> activacion = cq.from(HActivacion.class);
            Predicate date = cb.between(activacion.get("fechaPeticion"), fechaInicio, fechaFinal);
            cq.where(date);

            TypedQuery<HActivacion> query = em.createQuery(cq);

            listaActivacion = query.getResultList();

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listaActivacion;
    }
}
