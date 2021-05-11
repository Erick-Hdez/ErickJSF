package entidades;

import entidades.SPerfiles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-05-11T11:30:15")
@StaticMetamodel(SUsuarios.class)
public class SUsuarios_ { 

    public static volatile SingularAttribute<SUsuarios, Date> fechaBaja;
    public static volatile SingularAttribute<SUsuarios, Date> fechaAlta;
    public static volatile SingularAttribute<SUsuarios, Integer> idUsuario;
    public static volatile SingularAttribute<SUsuarios, String> nombreUsuario;
    public static volatile SingularAttribute<SUsuarios, Date> ultimaSesion;
    public static volatile SingularAttribute<SUsuarios, String> password;
    public static volatile SingularAttribute<SUsuarios, Long> idCliente;
    public static volatile SingularAttribute<SUsuarios, SPerfiles> idPerfil;
    public static volatile SingularAttribute<SUsuarios, String> correo;
    public static volatile SingularAttribute<SUsuarios, String> usuario;
    public static volatile SingularAttribute<SUsuarios, Integer> idUsuarioModifica;
    public static volatile SingularAttribute<SUsuarios, Date> fechaServidor;
    public static volatile SingularAttribute<SUsuarios, Boolean> activo;

}