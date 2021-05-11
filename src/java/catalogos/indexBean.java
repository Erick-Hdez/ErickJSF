package catalogos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import utils.TraeDatoSesion;

/**
 *
 * @author Erick Corral
 */
@ViewScoped
@ManagedBean
@RequestScoped

public class indexBean {

    // variables de sesión (Persistencia)
    private int idUsuario;
    private String user;
    private String nombreUsuario;
    
    public indexBean() {
         nombreUsuario = TraeDatoSesion.traerNombreUsuario();
         user = TraeDatoSesion.traerUsuario();

    }
    

//<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    
    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }
    
    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
    
    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
//</editor-fold>

}
