/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelos.CatalogoModeloTipoTelefono;
import objetos.TipoTelefono;
import respuestas.Respuesta;
import respuestas.RespuestaTipoTelefono;

/**
 *
 * @author Erick Corral
 * @Fecha 2021-04-16
 */

@ManagedBean
@RequestScoped
@ViewScoped

public class TipoTelefonoBean {

    private ArrayList<TipoTelefono> listaTipoTelefono;
    private TipoTelefono tipoTelefono;

    public TipoTelefonoBean() {
        mostrarCatalogoTipoTelefono();
        tipoTelefono = new TipoTelefono();
    }

    // muestra el catálogo C_TIPO_TELEFONO
    public void mostrarCatalogoTipoTelefono() {
        RespuestaTipoTelefono respuesta = CatalogoModeloTipoTelefono.getListaTipoTelefono();
        try {
            if (respuesta.getRespuesta().getId() == 0) {
               
                listaTipoTelefono = (ArrayList<TipoTelefono>) respuesta.getListaTipoTelefono();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargando datos ", respuesta.getRespuesta().getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {
                System.out.println(respuesta.getRespuesta().getMensaje());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se enontraron resultados ", respuesta.getRespuesta().getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoTelefonoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // insertar datos en el catálogo C_TIPO_TELEFONO
    public void insertarDatosTipoTelefono() {
        Respuesta respuesta = CatalogoModeloTipoTelefono.insertarRegistroTipoTelefono(tipoTelefono);

        try {
            if (respuesta.getId() != 0) {
                System.out.println(respuesta.getMensaje());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados ", respuesta.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                mostrarCatalogoTipoTelefono();
                tipoTelefono = new TipoTelefono();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos agrgados exitosamente ", respuesta.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoTelefonoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // editar fila en catálogo C_TIPO_TELEFONO
    public void editarDatosTipoTelefono(TipoTelefono tipoTelefono) {

        Respuesta respuesta = CatalogoModeloTipoTelefono.editarRegistroTipoTelefono(tipoTelefono);

        try {
            if (respuesta.getId() != 0) {
                System.out.println(respuesta.getMensaje());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados ", respuesta.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                mostrarCatalogoTipoTelefono();
                FacesMessage msg = new FacesMessage("Fila editada exitosamente...");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
        } catch (Exception ex) {
            Logger.getLogger(TipoTelefonoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // eliminar fila de catálogo C_TIPO_TELEFONO
    public void eliminarDatosTipoTelefono(TipoTelefono tipoTelefono) {
        Respuesta respuesta = CatalogoModeloTipoTelefono.eliminarRegistroTipoTelefono(tipoTelefono);
        try {
            if (respuesta.getId() != 0) {
                System.out.println(respuesta.getMensaje());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados ", respuesta.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                mostrarCatalogoTipoTelefono();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fila eliminada exitosamente...", respuesta.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoTelefonoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    public ArrayList<TipoTelefono> getListaTipoTelefono() {
        return listaTipoTelefono;
    }

    public void setListaTipoTelefono(ArrayList<TipoTelefono> listaTipoTelefono) {
        this.listaTipoTelefono = listaTipoTelefono;
    }

    /**
     * @return the tipoTelefono
     */
    public TipoTelefono getTipoTelefono() {
        return tipoTelefono;
    }

    /**
     * @param tipoTelefono the tipoTelefono to set
     */
    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }
//</editor-fold>

}
