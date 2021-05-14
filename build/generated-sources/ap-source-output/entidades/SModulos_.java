package entidades;

import entidades.SAccesos;
import entidades.SAplicaciones;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-05-14T17:34:02")
@StaticMetamodel(SModulos.class)
public class SModulos_ { 

    public static volatile SingularAttribute<SModulos, String> icono;
    public static volatile SingularAttribute<SModulos, Date> fechaBaja;
    public static volatile SingularAttribute<SModulos, SAplicaciones> idAplicacion;
    public static volatile SingularAttribute<SModulos, Integer> idModulo;
    public static volatile SingularAttribute<SModulos, Short> orden;
    public static volatile SingularAttribute<SModulos, SAccesos> idAcceso;
    public static volatile SingularAttribute<SModulos, String> nombreModulo;
    public static volatile SingularAttribute<SModulos, String> url;
    public static volatile SingularAttribute<SModulos, Date> fechaServidor;
    public static volatile SingularAttribute<SModulos, Boolean> activo;

}