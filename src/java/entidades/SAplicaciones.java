/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Blueweb
 */
@Entity
@Table(name = "S_APLICACIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SAplicaciones.findAll", query = "SELECT s FROM SAplicaciones s"),
    @NamedQuery(name = "SAplicaciones.findByIdAplicacion", query = "SELECT s FROM SAplicaciones s WHERE s.idAplicacion = :idAplicacion"),
    @NamedQuery(name = "SAplicaciones.findByNombreAplicacion", query = "SELECT s FROM SAplicaciones s WHERE s.nombreAplicacion = :nombreAplicacion"),
    @NamedQuery(name = "SAplicaciones.findByIcono", query = "SELECT s FROM SAplicaciones s WHERE s.icono = :icono"),
    @NamedQuery(name = "SAplicaciones.findByUrl", query = "SELECT s FROM SAplicaciones s WHERE s.url = :url"),
    @NamedQuery(name = "SAplicaciones.findByOrden", query = "SELECT s FROM SAplicaciones s WHERE s.orden = :orden"),
    @NamedQuery(name = "SAplicaciones.findByActivo", query = "SELECT s FROM SAplicaciones s WHERE s.activo = :activo"),
    @NamedQuery(name = "SAplicaciones.findByFechaServidor", query = "SELECT s FROM SAplicaciones s WHERE s.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "SAplicaciones.findByFechaBaja", query = "SELECT s FROM SAplicaciones s WHERE s.fechaBaja = :fechaBaja")})
public class SAplicaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_APLICACION")
    private Integer idAplicacion;
    @Basic(optional = false)
    @Column(name = "NOMBRE_APLICACION")
    private String nombreAplicacion;
    @Column(name = "ICONO")
    private String icono;
    @Basic(optional = false)
    @Column(name = "URL")
    private String url;
    @Basic(optional = false)
    @Column(name = "ORDEN")
    private short orden;
    @Basic(optional = false)
    @Column(name = "ACTIVO")
    private boolean activo;
    @Basic(optional = false)
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @JoinColumn(name = "ID_ACCESO", referencedColumnName = "ID_ACCESO")
    @ManyToOne
    private SAccesos idAcceso;

    public SAplicaciones() {
    }

    public SAplicaciones(Integer idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public SAplicaciones(Integer idAplicacion, String nombreAplicacion, String url, short orden, boolean activo, Date fechaServidor) {
        this.idAplicacion = idAplicacion;
        this.nombreAplicacion = nombreAplicacion;
        this.url = url;
        this.orden = orden;
        this.activo = activo;
        this.fechaServidor = fechaServidor;
    }

    public Integer getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Integer idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public SAccesos getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(SAccesos idAcceso) {
        this.idAcceso = idAcceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAplicacion != null ? idAplicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SAplicaciones)) {
            return false;
        }
        SAplicaciones other = (SAplicaciones) object;
        if ((this.idAplicacion == null && other.idAplicacion != null) || (this.idAplicacion != null && !this.idAplicacion.equals(other.idAplicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SAplicaciones[ idAplicacion=" + idAplicacion + " ]";
    }
    
}
