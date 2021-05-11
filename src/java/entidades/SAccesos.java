
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Blueweb
 */
@Entity
@Table(name = "S_ACCESOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SAccesos.findAll", query = "SELECT s FROM SAccesos s"),
    @NamedQuery(name = "SAccesos.findByIdAcceso", query = "SELECT s FROM SAccesos s WHERE s.idAcceso = :idAcceso"),
    @NamedQuery(name = "SAccesos.findByNombreAcceso", query = "SELECT s FROM SAccesos s WHERE s.nombreAcceso = :nombreAcceso"),
    @NamedQuery(name = "SAccesos.findByOrden", query = "SELECT s FROM SAccesos s WHERE s.orden = :orden"),
    @NamedQuery(name = "SAccesos.findByActivo", query = "SELECT s FROM SAccesos s WHERE s.activo = :activo"),
    @NamedQuery(name = "SAccesos.findByFechaServidor", query = "SELECT s FROM SAccesos s WHERE s.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "SAccesos.findByFechaBaja", query = "SELECT s FROM SAccesos s WHERE s.fechaBaja = :fechaBaja")})
public class SAccesos implements Serializable {

    @OneToMany(mappedBy = "idAcceso")
    private Collection<SAplicaciones> sAplicacionesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACCESO")
    private Integer idAcceso;
    @Basic(optional = false)
    @Column(name = "NOMBRE_ACCESO")
    private String nombreAcceso;
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

    public SAccesos() {
    }

    public SAccesos(Integer idAcceso) {
        this.idAcceso = idAcceso;
    }

    public SAccesos(Integer idAcceso, String nombreAcceso, short orden, boolean activo, Date fechaServidor) {
        this.idAcceso = idAcceso;
        this.nombreAcceso = nombreAcceso;
        this.orden = orden;
        this.activo = activo;
        this.fechaServidor = fechaServidor;
    }

    public Integer getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(Integer idAcceso) {
        this.idAcceso = idAcceso;
    }

    public String getNombreAcceso() {
        return nombreAcceso;
    }

    public void setNombreAcceso(String nombreAcceso) {
        this.nombreAcceso = nombreAcceso;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcceso != null ? idAcceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SAccesos)) {
            return false;
        }
        SAccesos other = (SAccesos) object;
        if ((this.idAcceso == null && other.idAcceso != null) || (this.idAcceso != null && !this.idAcceso.equals(other.idAcceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SAccesos[ idAcceso=" + idAcceso + " ]";
    }

    @XmlTransient
    public Collection<SAplicaciones> getSAplicacionesCollection() {
        return sAplicacionesCollection;
    }

    public void setSAplicacionesCollection(Collection<SAplicaciones> sAplicacionesCollection) {
        this.sAplicacionesCollection = sAplicacionesCollection;
    }
    
}
