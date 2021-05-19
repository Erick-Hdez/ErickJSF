/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import controles.SAplicacionesJpaController;
import entidades.SAplicaciones;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import objetos.Menu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import utils.TraeDatoSesion;

/**
 *
 * @author Erick Corral
 */
@ManagedBean(name = "menu")
@RequestScoped
@javax.enterprise.context.SessionScoped

public class menuBean implements Serializable {

    private MenuModel model;
    
    public menuBean() {
        model = new DefaultMenuModel();
        this.mostrarMenu();
    }

    // Menú Dinámico (método que muestra el menú dinámico)
    public void mostrarMenu() {
        SAplicacionesJpaController menuModel = new SAplicacionesJpaController();
        List<SAplicaciones> lista = new ArrayList<>();
        String usuario = TraeDatoSesion.traerUsuario();
        lista = menuModel.cargarDatosMenu(usuario);

        DefaultMenuItem index = DefaultMenuItem.builder()
                .value("Inicio")
                .icon("fa fa-home")
                .url("/ErickJsfProject/index.xhtml")
                .build();
                
        setModel(new DefaultMenuModel());
        DefaultSubMenu inicioSubmenu = DefaultSubMenu.builder().label("Inicio").build();

        DefaultSubMenu menuDinamico = new DefaultSubMenu();
        DefaultMenuItem menuDinamicoItem = new DefaultMenuItem();

        inicioSubmenu.getElements().add(index);
        getModel().getElements().add(inicioSubmenu);

        for (SAplicaciones listaMenu : lista) {
            if (listaMenu.getIdAplicacion() == 0) {

                menuDinamico = DefaultSubMenu.builder()
                        .label(listaMenu.getNombreAplicacion())
                        .icon(listaMenu.getIcono())
                        .build();

                String nombreMenu = listaMenu.getNombreAplicacion();
                String nombreMenuConfirmacion = "";

                for (SAplicaciones listaItem : lista) {
                    if (listaItem.getIdAplicacion() == 0) {
                        nombreMenuConfirmacion = listaItem.getNombreAplicacion();
                    }

                    if (nombreMenu == nombreMenuConfirmacion && listaItem.getIdAplicacion() != 0) {
                        menuDinamicoItem = DefaultMenuItem.builder()
                                .value(listaItem.getNombreAplicacion())
                                .url("/ErickJsfProject" + listaItem.getUrl())
                                .icon(listaItem.getIcono())
                                .build();
                        menuDinamico.getElements().add(menuDinamicoItem);
                        menuDinamicoItem = new DefaultMenuItem();
                    }
                }

                getModel().getElements().add(menuDinamico);
                menuDinamico = new DefaultSubMenu();
            }
        }
    }

    // Menú Estático, redirección de contexto (método de redirección a catalogos y reportes)
    public void redirecionar(int id) throws IOException {
        switch (id) {
            case 1:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/catalogo/catalogoCiudad.xhtml");
                break;
            case 2:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/catalogo/catalogoTipoTelefono.xhtml");
                break;
            case 3:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/catalogo/catalogoAccesos.xhtml");
                break;
            case 4:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/catalogo/catalogoPerfil.xhtml");
                break;
            case 5:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/reporte/reporteClientes.xhtml");
                break;
            case 6:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/reporte/reporteActivacion.xhtml");
                break;
            default:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/index.xhtml");
                break;
        }
    }
//<editor-fold defaultstate="collapsed" desc="GETS y SETS">

    /**
     * @return the model
     */
    public MenuModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(MenuModel model) {
        this.model = model;
    }

//</editor-fold>
}
