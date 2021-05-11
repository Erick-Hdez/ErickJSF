/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Erick Corral
 */
@ManagedBean(name = "menu")
@RequestScoped
@ViewScoped
public class menuBean {

    private String pagina;

    public void redirecionar(int id) {

        switch (id) {
            case 1:
                setPagina("/catalogos/catalogoCiudad.xhtml");
                break;
            case 2:
                setPagina("/catalogos/catalogoTipoTelefono.xhtml");
                break;
            case 3:
                setPagina("/catalogos/catalogoAccesos.xhtml");
                break;
            case 4:
                setPagina("/catalogos/catalogoPerfiles.xhtml");
                break;
            default:
                setPagina("/index.xhtml");
                break;
        }

    }

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

}
