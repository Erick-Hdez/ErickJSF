/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import controles.HActivacionJpaController;
import entidades.HActivacion;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Erick Corral
 */
@ManagedBean
@ViewScoped
public class ReportesActivacionBean {

    private Date fechaInicio;
    private Date fechaFin;
    private List<HActivacion> listaReporteActivacion;

    public ReportesActivacionBean() {

    }

    public void mostrarListaActivaciones() {
        HActivacionJpaController modelo = new HActivacionJpaController();
        setListaReporteActivacion(modelo.findHActivacionEntities());
    }

    // Buscar activaciones por fecha de peticion con criteria query
    public void buscarActivaciones() {
        try {
            if (fechaInicio.before(fechaFin) || fechaInicio.equals(fechaFin)) {
                Calendar calendar = Calendar.getInstance().getInstance();
                calendar.setTime(fechaFin);
                calendar.add(calendar.HOUR, 23);
                calendar.add(calendar.MINUTE, 59);
                fechaFin = calendar.getTime();

                HActivacionJpaController modeloActivacion = new HActivacionJpaController();
                listaReporteActivacion = modeloActivacion.crearReporteActivacion(fechaInicio, fechaFin);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Buscar", "Busqueda exitosa");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Buscar", "La fecha de inicio no puede ser mayor que fecha fin");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } catch (Exception ex) {
//            Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);         
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Buscar", "Se produjo un error");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the listaReporteActivacion
     */
    public List<HActivacion> getListaReporteActivacion() {
        return listaReporteActivacion;
    }

    /**
     * @param listaReporteActivacion the listaReporteActivacion to set
     */
    public void setListaReporteActivacion(List<HActivacion> listaReporteActivacion) {
        this.listaReporteActivacion = listaReporteActivacion;
    }
//</editor-fold>
}
