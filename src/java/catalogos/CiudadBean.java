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
import javax.faces.context.FacesContext;
import modelos.CatalogoModeloCiudad;
import objetos.Ciudad;
import respuestas.Respuesta;
import respuestas.RespuestaCiudad;

/**
 *
 * @author Erick Corral
 * @Fecha 2021-04-16
 */
@ManagedBean
@RequestScoped
public class CiudadBean {

    private ArrayList<Ciudad> listaCiudad;
    private Ciudad ciudad;
    
//    private String filtroDescripcion;
//    private int filtroLada;


    public CiudadBean() {
        mostrarCatalogoCiudad();
        ciudad = new Ciudad();
    }

    // Muestra el catalogo de la base de datos 
    public void mostrarCatalogoCiudad() {
        RespuestaCiudad respuesta = CatalogoModeloCiudad.getListaCiudades();
        try {
            if (respuesta.getRespuesta().getId() == 0) {
                setListaCiudad((ArrayList<Ciudad>) respuesta.getListaCiudad());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargando datos ", respuesta.getRespuesta().getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (respuesta.getRespuesta().getId() == 1) {
                setListaCiudad((ArrayList<Ciudad>) respuesta.getListaCiudad());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados", respuesta.getRespuesta().getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, msg);
//                System.out.println(respuesta.getRespuesta().getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Insertar datos en el catalogo C_CIUDAD
    public void insertarDatosCiudad() {
        try {
            Respuesta respuesta = CatalogoModeloCiudad.insertarRegistroCiudad(getCiudad());
            if (respuesta.getId() != 0) {
                System.out.println(respuesta.getMensaje());
            } else {
                mostrarCatalogoCiudad();
                FacesMessage msg = new FacesMessage("Datos agregados exitosamente...");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Editar una fila en el catalogo C_CIUDAD
    public void editarDatosCiudad(Ciudad ciudad) {

        Respuesta respuesta = CatalogoModeloCiudad.editarRegistroCiudad(ciudad);
        try {
            if (respuesta.getId() != 0) {
                System.out.println(respuesta.getMensaje());
            } else {
                mostrarCatalogoCiudad();
                FacesMessage msg = new FacesMessage("Fila editada exitosamente...");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Elimina una fila en el catalogo C_CIUDAD
    public void eliminarDatosCiudad(Ciudad ciudad) {
        Respuesta respuesta = CatalogoModeloCiudad.eliminarRegistroCiudad(ciudad);
        try {
            if (respuesta.getId() != 0) {
                System.out.println(respuesta.getMensaje());
            } else {
                mostrarCatalogoCiudad();
                FacesMessage msg = new FacesMessage("Fila eliminada exitosamente...");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    /**
     * @return the listaCiudad
     */
    public ArrayList<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    /**
     * @param listaCiudad the listaCiudad to set
     */
    public void setListaCiudad(ArrayList<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    /**
     * @return the ciudad
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

//    /**
//     * @return the filtroDescripcion
//     */
//    public String getFiltroDescripcion() {
//        return filtroDescripcion;
//    }
//
//    /**
//     * @param filtroDescripcion the filtroDescripcion to set
//     */
//    public void setFiltroDescripcion(String filtroDescripcion) {
//        this.filtroDescripcion = filtroDescripcion;
//    }
//
//    /**
//     * @return the filtroLada
//     */
//    public int getFiltroLada() {
//        return filtroLada;
//    }
//
//    /**
//     * @param filtroLada the filtroLada to set
//     */
//    public void setFiltroLada(int filtroLada) {
//        this.filtroLada = filtroLada;
//    }
//</editor-fold>

}
