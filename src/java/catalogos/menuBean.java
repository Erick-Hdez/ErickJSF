/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Erick Corral
 */
@ManagedBean(name = "menu")
@RequestScoped
@ViewScoped
public class menuBean {

    private String pagina;
    
    public void redirecionar(int id) throws IOException {

        switch (id) {
            case 1:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/catalogos/catalogoCiudad.xhtml");
                break;
            case 2:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/catalogos/catalogoTipoTelefono.xhtml");
                break;
            case 3:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/catalogos/catalogoAccesos.xhtml");
                break;
            case 4:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/catalogos/catalogoPerfiles.xhtml");
                break;
            case 5:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(FacesContext.getCurrentInstance()
                                .getExternalContext().getRequestContextPath() + "/catalogos/catalogoReportes.xhtml");
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
     * @return the pagina
     */
    public String getPagina() {
        return pagina;
    }

    /**
     * @param pagina the pagina to set
     */
    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
//</editor-fold>

}
