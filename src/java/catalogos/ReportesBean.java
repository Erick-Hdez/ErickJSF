/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import controles.SUsuariosJpaController;
import entidades.SUsuarios;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import objetos.ReporteClientes;
import objetos.Usuario;

/**
 *
 * @author Erick Corral
 */
@ManagedBean
@ViewScoped
public class ReportesBean {

    private List<ReporteClientes> listaReporte;
    private List<SUsuarios> listaUsuarios;
    private SUsuarios usuario;

    public ReportesBean() {
        usuario = new SUsuarios();
        mostrarListaUsuario();
    }

    public void mostrarListaUsuario() {
        SUsuariosJpaController modelo = new SUsuariosJpaController();
        setListaUsuarios(modelo.findSUsuariosEntities());
    }

    public void mostrarListaReporte() {
        try {
            SUsuariosJpaController modeloReporte = new SUsuariosJpaController();
            setListaReporte(modeloReporte.crearReporteClienteStp(usuario.getIdUsuario()));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Buscar", "Busqueda exitosa");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            Logger.getLogger(ReporteClientes.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Buscar", "Se produjo un error");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    /**
     * @return the listaReporte
     */
    public List<ReporteClientes> getListaReporte() {
        return listaReporte;
    }

    /**
     * @param listaReporte the listaReporte to set
     */
    public void setListaReporte(List<ReporteClientes> listaReporte) {
        this.listaReporte = listaReporte;
    }

    /**
     * @return the usuario
     */
    public SUsuarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(SUsuarios usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the listaUsuarios
     */
    public List<SUsuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<SUsuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

//</editor-fold>
}
