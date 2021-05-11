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
public class Ciudad {
    private int idCiudad;
    private String descripcion;
    private int codigo;
    private int lada;
    private boolean activo;
    private String fechaAlta;
    private String fechaBaja;
    private String fechaServidor;
    
//<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    /**
     * @return the idCiudad
     */
    public int getIdCiudad() {
        return idCiudad;
    }
    
    /**
     * @param idCiudad the idCiudad to set
     */
    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
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
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    /**
     * @return the lada
     */
    public int getLada() {
        return lada;
    }
    
    /**
     * @param lada the lada to set
     */
    public void setLada(int lada) {
        this.lada = lada;
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
     * @return the fechaAlta
     */
    public String getFechaAlta() {
        return fechaAlta;
    }
    
    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    /**
     * @return the fechaBaja
     */
    public String getFechaBaja() {
        return fechaBaja;
    }
    
    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
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
