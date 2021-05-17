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
@Table(name = "H_ACTIVACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HActivacion.findAll", query = "SELECT h FROM HActivacion h"),
    @NamedQuery(name = "HActivacion.findById", query = "SELECT h FROM HActivacion h WHERE h.id = :id"),
    @NamedQuery(name = "HActivacion.findByIdActivacion", query = "SELECT h FROM HActivacion h WHERE h.idActivacion = :idActivacion"),
    @NamedQuery(name = "HActivacion.findByIccid", query = "SELECT h FROM HActivacion h WHERE h.iccid = :iccid"),
    @NamedQuery(name = "HActivacion.findByImei", query = "SELECT h FROM HActivacion h WHERE h.imei = :imei"),
    @NamedQuery(name = "HActivacion.findByDistribuidor", query = "SELECT h FROM HActivacion h WHERE h.distribuidor = :distribuidor"),
    @NamedQuery(name = "HActivacion.findByCliente", query = "SELECT h FROM HActivacion h WHERE h.cliente = :cliente"),
    @NamedQuery(name = "HActivacion.findByCiudad", query = "SELECT h FROM HActivacion h WHERE h.ciudad = :ciudad"),
    @NamedQuery(name = "HActivacion.findByIdTipoTelefonia", query = "SELECT h FROM HActivacion h WHERE h.idTipoTelefonia = :idTipoTelefonia"),
    @NamedQuery(name = "HActivacion.findByDescripcionTipo", query = "SELECT h FROM HActivacion h WHERE h.descripcionTipo = :descripcionTipo"),
    @NamedQuery(name = "HActivacion.findByRespuestaAplicacion", query = "SELECT h FROM HActivacion h WHERE h.respuestaAplicacion = :respuestaAplicacion"),
    @NamedQuery(name = "HActivacion.findByMonto", query = "SELECT h FROM HActivacion h WHERE h.monto = :monto"),
    @NamedQuery(name = "HActivacion.findByTelefono", query = "SELECT h FROM HActivacion h WHERE h.telefono = :telefono"),
    @NamedQuery(name = "HActivacion.findByFechaPeticion", query = "SELECT h FROM HActivacion h WHERE h.fechaPeticion = :fechaPeticion"),
    @NamedQuery(name = "HActivacion.findByFechaRespuesta", query = "SELECT h FROM HActivacion h WHERE h.fechaRespuesta = :fechaRespuesta"),
    @NamedQuery(name = "HActivacion.findByFechaServidor", query = "SELECT h FROM HActivacion h WHERE h.fechaServidor = :fechaServidor")})
public class HActivacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "ID_ACTIVACION")
    private long idActivacion;
    @Column(name = "ICCID")
    private String iccid;
    @Column(name = "IMEI")
    private String imei;
    @Column(name = "DISTRIBUIDOR")
    private String distribuidor;
    @Column(name = "CLIENTE")
    private String cliente;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "ID_TIPO_TELEFONIA")
    private Long idTipoTelefonia;
    @Column(name = "DESCRIPCION_TIPO")
    private String descripcionTipo;
    @Column(name = "RESPUESTA_APLICACION")
    private String respuestaAplicacion;
    @Column(name = "MONTO")
    private Long monto;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "FECHA_PETICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPeticion;
    @Column(name = "FECHA_RESPUESTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID_CIUDAD")
    @ManyToOne
    private CCiudad idCiudad;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne
    private CClientes idCliente;
    @JoinColumn(name = "ID_DISTRIBUIDOR", referencedColumnName = "ID_DISTRIBUIDOR")
    @ManyToOne
    private CDistribuidor idDistribuidor;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private SUsuarios idUsuario;

    public HActivacion() {
    }

    public HActivacion(Long id) {
        this.id = id;
    }

    public HActivacion(Long id, long idActivacion) {
        this.id = id;
        this.idActivacion = idActivacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdActivacion() {
        return idActivacion;
    }

    public void setIdActivacion(long idActivacion) {
        this.idActivacion = idActivacion;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getIdTipoTelefonia() {
        return idTipoTelefonia;
    }

    public void setIdTipoTelefonia(Long idTipoTelefonia) {
        this.idTipoTelefonia = idTipoTelefonia;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public String getRespuestaAplicacion() {
        return respuestaAplicacion;
    }

    public void setRespuestaAplicacion(String respuestaAplicacion) {
        this.respuestaAplicacion = respuestaAplicacion;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaPeticion() {
        return fechaPeticion;
    }

    public void setFechaPeticion(Date fechaPeticion) {
        this.fechaPeticion = fechaPeticion;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public CCiudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(CCiudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public CClientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(CClientes idCliente) {
        this.idCliente = idCliente;
    }

    public CDistribuidor getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(CDistribuidor idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public SUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SUsuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HActivacion)) {
            return false;
        }
        HActivacion other = (HActivacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HActivacion[ id=" + id + " ]";
    }
    
}
