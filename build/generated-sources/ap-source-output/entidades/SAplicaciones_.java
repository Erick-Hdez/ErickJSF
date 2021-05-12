package entidades;

import entidades.SAccesos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-05-12T12:39:04")
@StaticMetamodel(SAplicaciones.class)
public class SAplicaciones_ { 

    public static volatile SingularAttribute<SAplicaciones, String> icono;
    public static volatile SingularAttribute<SAplicaciones, Integer> idAplicacion;
    public static volatile SingularAttribute<SAplicaciones, Date> fechaBaja;
    public static volatile SingularAttribute<SAplicaciones, Short> orden;
    public static volatile SingularAttribute<SAplicaciones, SAccesos> idAcceso;
    public static volatile SingularAttribute<SAplicaciones, String> nombreAplicacion;
    public static volatile SingularAttribute<SAplicaciones, String> url;
    public static volatile SingularAttribute<SAplicaciones, Date> fechaServidor;
    public static volatile SingularAttribute<SAplicaciones, Boolean> activo;

}