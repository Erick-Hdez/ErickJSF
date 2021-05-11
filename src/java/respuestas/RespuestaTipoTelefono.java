/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respuestas;

import java.util.ArrayList;
import java.util.List;
import objetos.TipoTelefono;

/**
 *
 * @author Erick Corral
 * @echa 2021-04-16
 */

public class RespuestaTipoTelefono {

    private Respuesta respuesta;
    private ArrayList<TipoTelefono> listaTipoTelefono;
    private TipoTelefono tipoTelefono;

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
     * @return the ListaTipoTelefono
     */
    public List<TipoTelefono> getListaTipoTelefono() {
        return listaTipoTelefono;
    }

    /**
     * @param listaTipoTelefono the listaTipoTelefono to set
     */
    public void setListaTipoTelefono(List<TipoTelefono> listaTipoTelefono) {
        this.listaTipoTelefono = (ArrayList<TipoTelefono>) listaTipoTelefono;
    }

    /**
     * @return the tipoTelefono
     */
    public TipoTelefono getTipoTelefono() {
        return tipoTelefono;
    }

    /**
     * @param tipoTelefono the tipoTelefono to set
     */
    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

}
