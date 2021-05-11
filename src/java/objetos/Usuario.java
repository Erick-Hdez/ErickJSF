/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;


/**
 *
 * @author Erick Corral 
 * 
 */
public class Usuario {
    
    private int idUsuario;
    private int idPerfil;
    private String usuario;
    private String nombreUsuario;
    private String pasword;
    private String correo; 
    private boolean activo;
    private String ultimaSesion;
    private String fechaAlta;
    private String fechaBaja;
    private String fechaServidor;
    private int idUsuarioModifica;
    private int idCliente;

   
   
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
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
    
    /**
     * @return the pasword
     */
    public String getPasword() {
        return pasword;
    }
    
    /**
     * @param pasword the pasword to set
     */
    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
    
    /**
     * @return the ultimaSesion
     */
    public String getUltimaSesion() {
        return ultimaSesion;
    }
    
    /**
     * @param ultimaSesion the ultimaSesion to set
     */
    public void setUltimaSesion(String ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }
    
     /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
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
     * @return the idPerfil
     */
    public int getIdPerfil() {
        return idPerfil;
    }

    /**
     * @param idPerfil the idPerfil to set
     */
    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
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

    /**
     * @return the idUsuarioModifica
     */
    public int getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    /**
     * @param idUsuarioModifica the idUsuarioModifica to set
     */
    public void setIdUsuarioModifica(int idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

//</editor-fold>

}