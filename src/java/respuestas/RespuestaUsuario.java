/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respuestas;

import java.util.ArrayList;
import objetos.Usuario;

/**
 *
 * @author Erick Corral
 */
public class RespuestaUsuario {
    
    private Respuesta respuesta;
    private ArrayList<Usuario> listaUsuario;
    private Usuario login;
    
    //<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    /**
     * @return the respuesta
     */
    public Respuesta getRespuesta() {
        return respuesta;
    }
    
    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
    
    /**
     * @return the listaUsuario
     */
    public ArrayList<Usuario> getListaUsuario() {
        return listaUsuario;
    }
    
    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    /**
     * @return the login
     */
    public Usuario getLogin() {
        return login;
    }
    
    /**
     * @param login the login to set
     */
    public void setLogin(Usuario login) {
        this.login = login;
    }
//</editor-fold>
    
}