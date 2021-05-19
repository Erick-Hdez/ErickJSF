/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author Erick Corral
 */
public class Menu {

    private int idMenu;
    private int orden;
    private String nombreAplicacion;
    private String icono;
    private String url;

//<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    /**
     * @return the idMenu
     */
    public int getIdMenu() {
        return idMenu;
    }
    
    /**
     * @param idMenu the idMenu to set
     */
    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }
    
    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }
    
    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }
    
    /**
     * @return the nombreAplicacion
     */
    public String getNombreAplicacion() {
        return nombreAplicacion;
    }
    
    /**
     * @param nombreAplicacion the nombreAplicacion to set
     */
    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }
    
    /**
     * @return the icono
     */
    public String getIcono() {
        return icono;
    }
    
    /**
     * @param icono the icono to set
     */
    public void setIcono(String icono) {
        this.icono = icono;
    }
    
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
//</editor-fold>

}
