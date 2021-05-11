/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respuestas;

import java.util.ArrayList;
import objetos.Ciudad;
import java.util.List;

/**
 *
 * @author Erick Corral
 * @Fecha 2021-04-16
 */
public class RespuestaCiudad {

    private Respuesta respuesta;
    private ArrayList<Ciudad> listaCiudad;
    private Ciudad ciudad;

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
     * @return the listaCiudad
     */
    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }
    
    /**
     * @param listaCiudad the listaCiudad to set
     */
    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = (ArrayList<Ciudad>) listaCiudad;
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
//</editor-fold>

}
