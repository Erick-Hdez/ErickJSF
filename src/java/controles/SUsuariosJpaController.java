/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import controles.exceptions.NonexistentEntityException;
import entidades.SUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import objetos.ReporteClientes;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class SUsuariosJpaController implements Serializable {

    public SUsuariosJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SUsuarios SUsuarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(SUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SUsuarios SUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios = em.merge(SUsuarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SUsuarios.getIdUsuario();
                if (findSUsuarios(id) == null) {
                    throw new NonexistentEntityException("The sUsuarios with id " + id + " no longer exists.");
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
            SUsuarios SUsuarios;
            try {
                SUsuarios = em.getReference(SUsuarios.class, id);
                SUsuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SUsuarios with id " + id + " no longer exists.", enfe);
            }
            em.remove(SUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SUsuarios> findSUsuariosEntities() {
        return findSUsuariosEntities(true, -1, -1);
    }

    public List<SUsuarios> findSUsuariosEntities(int maxResults, int firstResult) {
        return findSUsuariosEntities(false, maxResults, firstResult);
    }

    private List<SUsuarios> findSUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SUsuarios.class));
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

    public SUsuarios findSUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getSUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SUsuarios> rt = cq.from(SUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<ReporteClientes> crearReporteClienteStp(int idUsuario) {

        List<ReporteClientes> listaClientes = new ArrayList<>();
        ReporteClientes cliente = new ReporteClientes();

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            StoredProcedureQuery query = em.createStoredProcedureQuery("stp_selectCClientesErick");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.setParameter(1, idUsuario);

            query.execute();

            List<Object[]> listaResultados = query.getResultList();

            for (Object[] items : listaResultados) {
                cliente.setNumeroCliente(items[0].toString());
                cliente.setNombreCliente(items[1].toString());
                cliente.setTelefono(items[2].toString());
                cliente.setNombreDistribuidor(items[3].toString());
                cliente.setNombreCiudad(items[4].toString());
                cliente.setNombreusuario(items[5].toString());
                
                listaClientes.add(cliente);
                cliente = new ReporteClientes();
         
            }

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listaClientes;
    }

}
