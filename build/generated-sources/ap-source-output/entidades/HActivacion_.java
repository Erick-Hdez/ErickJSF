package entidades;

import entidades.CCiudad;
import entidades.CClientes;
import entidades.CDistribuidor;
import entidades.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-05-17T15:27:38")
@StaticMetamodel(HActivacion.class)
public class HActivacion_ { 

    public static volatile SingularAttribute<HActivacion, Long> idActivacion;
    public static volatile SingularAttribute<HActivacion, SUsuarios> idUsuario;
    public static volatile SingularAttribute<HActivacion, String> distribuidor;
    public static volatile SingularAttribute<HActivacion, Date> fechaRespuesta;
    public static volatile SingularAttribute<HActivacion, CCiudad> idCiudad;
    public static volatile SingularAttribute<HActivacion, String> descripcionTipo;
    public static volatile SingularAttribute<HActivacion, String> cliente;
    public static volatile SingularAttribute<HActivacion, String> iccid;
    public static volatile SingularAttribute<HActivacion, Long> monto;
    public static volatile SingularAttribute<HActivacion, CClientes> idCliente;
    public static volatile SingularAttribute<HActivacion, String> ciudad;
    public static volatile SingularAttribute<HActivacion, Date> fechaPeticion;
    public static volatile SingularAttribute<HActivacion, String> imei;
    public static volatile SingularAttribute<HActivacion, Long> id;
    public static volatile SingularAttribute<HActivacion, String> telefono;
    public static volatile SingularAttribute<HActivacion, Long> idTipoTelefonia;
    public static volatile SingularAttribute<HActivacion, CDistribuidor> idDistribuidor;
    public static volatile SingularAttribute<HActivacion, String> respuestaAplicacion;
    public static volatile SingularAttribute<HActivacion, Date> fechaServidor;

}