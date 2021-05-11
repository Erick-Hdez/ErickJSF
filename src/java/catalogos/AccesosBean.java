/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import controles.SAccesosJpaController;
import entidades.SAccesos;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Erick Corral
 */
@ManagedBean
@RequestScoped
public class AccesosBean {

    private List<SAccesos> listaAccesos;
    private SAccesos accesos;
    private int idAcceso;

    public AccesosBean() {
        mostrarCatalogoAccesos();
        accesos = new SAccesos();
    }

    public void mostrarCatalogoAccesos() {
        try {
            SAccesosJpaController modelo = new SAccesosJpaController();
            listaAccesos = modelo.findSAccesosEntities();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargando datos ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarNuevoAcceso() {
        try {
            SAccesosJpaController modelo = new SAccesosJpaController();
            accesos.setActivo(true);
            accesos.setFechaServidor(new Date());
            modelo.create(accesos);
            mostrarCatalogoAccesos();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos registrados", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        } catch (Exception ex) {
            Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarAcceso() {
        try {
             SAccesosJpaController modelo = new SAccesosJpaController();
            modelo.edit(accesos);
            mostrarCatalogoAccesos();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Editados", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarAcceso(SAccesos accesos) {
        try {
            SAccesosJpaController modelo = new SAccesosJpaController();
            modelo.destroy(accesos.getIdAcceso());
            mostrarCatalogoAccesos();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Eliminados", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    /**
     * @return the listaAccesos
     */
    public List<SAccesos> getListaAccesos() {
        return listaAccesos;
    }

    /**
     * @param listaAccesos the listaAccesos to set
     */
    public void setListaAccesos(List<SAccesos> listaAccesos) {
        this.listaAccesos = listaAccesos;
    }

    /**
     * @return the accesos
     */
    public SAccesos getAccesos() {
        return accesos;
    }

    /**
     * @param accesos the accesos to set
     */
    public void setAccesos(SAccesos accesos) {
        this.accesos = accesos;
    }
    
    /**
     * @return the idAcceso
     */
    public int getIdAcceso() {
        return idAcceso;
    }

    /**
     * @param idAcceso the idAcceso to set
     */
    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }
//</editor-fold>
}
