/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import controles.SAccesosJpaController;
import controles.SPerfilesAccesosJpaController;
import controles.SPerfilesJpaController;
import controles.exceptions.NonexistentEntityException;
import entidades.SAccesos;
import entidades.SPerfiles;
import entidades.SPerfilesAccesos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;
import utils.TraeDatoSesion;

/**
 *
 * @author Erick Corral
 */
@ManagedBean
@ViewScoped
public class PerfilesBean {

    private SPerfiles perfiles = new SPerfiles();
    private List<SPerfiles> listaPerfiles;
    private List<SAccesos> listaAccesosDisponibles;
    private List<SAccesos> listaAccesosAsignados = new ArrayList<>();
    private DualListModel<SAccesos> dualListAccesos;
    private Integer idPerfil;

    public PerfilesBean() {
        mostrarListaPerfiles();
        mostrarListaAccesos();
        dualListAccesos = new DualListModel<>(getListaAccesosDisponibles(), getListaAccesosAsignados());
    }

    public void mostrarListaPerfiles() {
        SPerfilesJpaController modelo = new SPerfilesJpaController();
        setListaPerfiles(modelo.findSPerfilesEntities());
    }

    public void mostrarListaAccesos() {
        SAccesosJpaController modelo = new SAccesosJpaController();
        setListaAccesosDisponibles(modelo.findSAccesosEntities());
    }

    public void registrarPerfil() {
        List<SAccesos> ListaIdAccesosStr = new ArrayList<>();
        SPerfilesAccesos perfilesAccesos = new SPerfilesAccesos();

        SPerfilesJpaController modeloPerfiles = new SPerfilesJpaController();
        SPerfilesAccesosJpaController modeloPerfilesAccesos = new SPerfilesAccesosJpaController();
        perfiles.setActivo(true);
        perfiles.setFechaAlta(new Date());
        perfiles.setFechaServidor(new Date());
//        perfiles.setIdUsuarioModifica(TraeDatoSesion.traerIdUsuario());

        modeloPerfiles.create(perfiles);
        ListaIdAccesosStr = dualListAccesos.getTarget();

        SAccesos objAccesos = new SAccesos();
        try {
            for (Object listaId : ListaIdAccesosStr) {
                int id = Integer.parseInt(listaId.toString());
                objAccesos.setIdAcceso(id);
                perfilesAccesos.setSPerfiles(perfiles);
                perfilesAccesos.setSAccesos(objAccesos);
                perfilesAccesos.setFechaServidor(new Date());
//                perfilesAccesos.setIdUsuarioModifica(TraeDatoSesion.traerIdUsuario());
                modeloPerfilesAccesos.create(perfilesAccesos);
            }
            FacesMessage msg = new FacesMessage("Se registro correctamente.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        perfiles = new SPerfiles();
        mostrarListaPerfiles();
        mostrarListaAccesos();
        listaAccesosAsignados = new ArrayList<>();
        dualListAccesos = new DualListModel<>(listaAccesosDisponibles, listaAccesosAsignados);
    }

    public void eliminarPerfil(SPerfiles perfil) {
        SPerfilesJpaController modeloPerfiles = new SPerfilesJpaController();
        SPerfilesAccesosJpaController modeloPerfilesAccesos = new SPerfilesAccesosJpaController();
        List<SPerfilesAccesos> listaPerfilesAccesos = new ArrayList<>();

        listaPerfilesAccesos = modeloPerfilesAccesos.traerAccesosByPerfil(perfil);
        try {
            for (SPerfilesAccesos lista : listaPerfilesAccesos) {
                modeloPerfilesAccesos.destroy(lista.getSPerfilesAccesosPK());
            }
            modeloPerfiles.destroy(perfil.getIdPerfil());
            FacesMessage msg = new FacesMessage("Registro eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarListaPerfiles();
    }

    public void editarPerfil() {
        List<SAccesos> listaIdAccesosAsignadosString = new ArrayList<>();
        SPerfilesAccesos perfilesAccesos = new SPerfilesAccesos();
        List<SPerfilesAccesos> listaPerfilesAccesos = new ArrayList<>();

        SPerfilesJpaController modeloPerfiles = new SPerfilesJpaController();
        SPerfilesAccesosJpaController modeloPerfilesAccesos = new SPerfilesAccesosJpaController();

//        perfiles.setIdUsuarioModifica(TraeDatoSesion.traerIdUsuario());

        listaIdAccesosAsignadosString = dualListAccesos.getTarget();

        SAccesos objAccesos = new SAccesos();

        listaPerfilesAccesos = modeloPerfilesAccesos.traerAccesosByPerfil(perfiles);
        try {
            for (SPerfilesAccesos lista : listaPerfilesAccesos) {
                modeloPerfilesAccesos.destroy(lista.getSPerfilesAccesosPK());
            }
            for (Object listaId : listaIdAccesosAsignadosString) {

                int id = Integer.parseInt(listaId.toString());
                objAccesos.setIdAcceso(id);
                perfilesAccesos.setSPerfiles(perfiles);
                perfilesAccesos.setSAccesos(objAccesos);
                perfilesAccesos.setFechaServidor(new Date());
                perfilesAccesos.setIdUsuarioModifica(TraeDatoSesion.traerIdUsuario());
                try {
                    modeloPerfilesAccesos.create(perfilesAccesos);
                } catch (Exception ex) {
                    Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                modeloPerfiles.edit(perfiles);
                FacesMessage msg = new FacesMessage("Registro modificado correctamente", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PerfilesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarListaPerfiles();
    }

    public void traerAccesosPerfil(SPerfiles perfil) {
        perfiles = perfil;
        dualListAccesos = new DualListModel<>();
        listaAccesosDisponibles = new ArrayList<>();
        listaAccesosAsignados = new ArrayList<>();
        SPerfilesAccesosJpaController modelo = new SPerfilesAccesosJpaController();

        listaAccesosDisponibles = modelo.traerAccesosDisponibles(perfil);
        listaAccesosAsignados = modelo.traerAccesosAsignados(perfil);
        dualListAccesos = new DualListModel<>(listaAccesosDisponibles, listaAccesosAsignados);

    }
    //<editor-fold defaultstate="collapsed" desc="GETS y SETS">

    /**
     * @return the perfiles
     */
    public SPerfiles getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(SPerfiles perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * @return the listaPerfiles
     */
    public List<SPerfiles> getListaPerfiles() {
        return listaPerfiles;
    }

    /**
     * @param listaPerfiles the listaPerfiles to set
     */
    public void setListaPerfiles(List<SPerfiles> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    /**
     * @return the listaAccesosDisponibles
     */
    public List<SAccesos> getListaAccesosDisponibles() {
        return listaAccesosDisponibles;
    }

    /**
     * @param listaAccesosDisponibles the listaAccesosDisponibles to set
     */
    public void setListaAccesosDisponibles(List<SAccesos> listaAccesosDisponibles) {
        this.listaAccesosDisponibles = listaAccesosDisponibles;
    }

    /**
     * @return the listaAccesosAsignados
     */
    public List<SAccesos> getListaAccesosAsignados() {
        return listaAccesosAsignados;
    }

    /**
     * @param listaAccesosAsignados the listaAccesosAsignados to set
     */
    public void setListaAccesosAsignados(List<SAccesos> listaAccesosAsignados) {
        this.listaAccesosAsignados = listaAccesosAsignados;
    }

    /**
     * @return the dualListAccesos
     */
    public DualListModel<SAccesos> getDualListAccesos() {
        return dualListAccesos;
    }

    /**
     * @param dualListAccesos the dualListAccesos to set
     */
    public void setDualListAccesos(DualListModel<SAccesos> dualListAccesos) {
        this.dualListAccesos = dualListAccesos;
    }
    
      /**
     * @return the idPerfil
     */
    public Integer getIdPerfil() {
        return idPerfil;
    }

    /**
     * @param idPerfil the idPerfil to set
     */
    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }
//</editor-fold>
}

