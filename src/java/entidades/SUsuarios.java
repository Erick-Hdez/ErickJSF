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
@Table(name = "S_USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SUsuarios.findAll", query = "SELECT s FROM SUsuarios s"),
    @NamedQuery(name = "SUsuarios.findByIdUsuario", query = "SELECT s FROM SUsuarios s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "SUsuarios.findByUsuario", query = "SELECT s FROM SUsuarios s WHERE s.usuario = :usuario"),
    @NamedQuery(name = "SUsuarios.findByNombreUsuario", query = "SELECT s FROM SUsuarios s WHERE s.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "SUsuarios.findByPassword", query = "SELECT s FROM SUsuarios s WHERE s.password = :password"),
    @NamedQuery(name = "SUsuarios.findByCorreo", query = "SELECT s FROM SUsuarios s WHERE s.correo = :correo"),
    @NamedQuery(name = "SUsuarios.findByActivo", query = "SELECT s FROM SUsuarios s WHERE s.activo = :activo"),
    @NamedQuery(name = "SUsuarios.findByUltimaSesion", query = "SELECT s FROM SUsuarios s WHERE s.ultimaSesion = :ultimaSesion"),
    @NamedQuery(name = "SUsuarios.findByFechaAlta", query = "SELECT s FROM SUsuarios s WHERE s.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "SUsuarios.findByFechaBaja", query = "SELECT s FROM SUsuarios s WHERE s.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "SUsuarios.findByFechaServidor", query = "SELECT s FROM SUsuarios s WHERE s.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "SUsuarios.findByIdUsuarioModifica", query = "SELECT s FROM SUsuarios s WHERE s.idUsuarioModifica = :idUsuarioModifica"),
    @NamedQuery(name = "SUsuarios.findByIdCliente", query = "SELECT s FROM SUsuarios s WHERE s.idCliente = :idCliente")})
public class SUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CORREO")
    private String correo;
    @Basic(optional = false)
    @Column(name = "ACTIVO")
    private boolean activo;
    @Column(name = "ULTIMA_SESION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaSesion;
    @Basic(optional = false)
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Basic(optional = false)
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @Column(name = "ID_USUARIO_MODIFICA")
    private Integer idUsuarioModifica;
    @Column(name = "ID_CLIENTE")
    private Long idCliente;
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
    @ManyToOne
    private SPerfiles idPerfil;

    public SUsuarios() {
    }

    public SUsuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public SUsuarios(Integer idUsuario, String usuario, String nombreUsuario, String password, boolean activo, Date fechaAlta, Date fechaServidor) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaServidor = fechaServidor;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getUltimaSesion() {
        return ultimaSesion;
    }

    public void setUltimaSesion(Date ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public Integer getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(Integer idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public SPerfiles getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(SPerfiles idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SUsuarios)) {
            return false;
        }
        SUsuarios other = (SUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SUsuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
