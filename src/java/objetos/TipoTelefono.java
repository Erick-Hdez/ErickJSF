/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author Erick Corral
 * @Fecha 2021-04-16
 */
public class TipoTelefono {

    private int id;
    private String descripcion;
    private String clave;
    private int idTelefonia;
    private boolean activo;
    private String fechaServidor;
    
//<editor-fold defaultstate="collapsed" desc="GETSySETS">
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }
    
    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    /**
     * @return the idTelefonia
     */
    public int getIdTelefonia() {
        return idTelefonia;
    }
    
    /**
     * @param idTelefonia the idTelefonia to set
     */
    public void setIdTelefonia(int idTelefonia) {
        this.idTelefonia = idTelefonia;
    }
    
    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }
    
    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    /**
     * @return the fechaServidor
     */
    public String getFechaServidor() {
        return fechaServidor;
    }
    
    /**
     * @param fechaServidor the fechaServidor to set
     */
    public void setFechaServidor(String fechaServidor) {
        this.fechaServidor = fechaServidor;
    }
//</editor-fold>

}
