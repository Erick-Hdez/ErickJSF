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
@Table(name = "S_MODULOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SModulos.findAll", query = "SELECT s FROM SModulos s"),
    @NamedQuery(name = "SModulos.findByIdModulo", query = "SELECT s FROM SModulos s WHERE s.idModulo = :idModulo"),
    @NamedQuery(name = "SModulos.findByNombreModulo", query = "SELECT s FROM SModulos s WHERE s.nombreModulo = :nombreModulo"),
    @NamedQuery(name = "SModulos.findByIcono", query = "SELECT s FROM SModulos s WHERE s.icono = :icono"),
    @NamedQuery(name = "SModulos.findByUrl", query = "SELECT s FROM SModulos s WHERE s.url = :url"),
    @NamedQuery(name = "SModulos.findByOrden", query = "SELECT s FROM SModulos s WHERE s.orden = :orden"),
    @NamedQuery(name = "SModulos.findByActivo", query = "SELECT s FROM SModulos s WHERE s.activo = :activo"),
    @NamedQuery(name = "SModulos.findByFechaServidor", query = "SELECT s FROM SModulos s WHERE s.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "SModulos.findByFechaBaja", query = "SELECT s FROM SModulos s WHERE s.fechaBaja = :fechaBaja")})
public class SModulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MODULO")
    private Integer idModulo;
    @Basic(optional = false)
    @Column(name = "NOMBRE_MODULO")
    private String nombreModulo;
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
    @ManyToOne(optional = false)
    private SAccesos idAcceso;
    @JoinColumn(name = "ID_APLICACION", referencedColumnName = "ID_APLICACION")
    @ManyToOne(optional = false)
    private SAplicaciones idAplicacion;

    public SModulos() {
    }

    public SModulos(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public SModulos(Integer idModulo, String nombreModulo, String url, short orden, boolean activo, Date fechaServidor) {
        this.idModulo = idModulo;
        this.nombreModulo = nombreModulo;
        this.url = url;
        this.orden = orden;
        this.activo = activo;
        this.fechaServidor = fechaServidor;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
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

    public SAplicaciones getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(SAplicaciones idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModulo != null ? idModulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SModulos)) {
            return false;
        }
        SModulos other = (SModulos) object;
        if ((this.idModulo == null && other.idModulo != null) || (this.idModulo != null && !this.idModulo.equals(other.idModulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SModulos[ idModulo=" + idModulo + " ]";
    }
    
}
