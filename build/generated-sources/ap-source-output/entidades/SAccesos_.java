package entidades;

import entidades.SAplicaciones;
import entidades.SPerfilesAccesos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-05-17T15:27:38")
@StaticMetamodel(SAccesos.class)
public class SAccesos_ { 

    public static volatile SingularAttribute<SAccesos, Date> fechaBaja;
    public static volatile CollectionAttribute<SAccesos, SPerfilesAccesos> sPerfilesAccesosCollection;
    public static volatile SingularAttribute<SAccesos, String> nombreAcceso;
    public static volatile SingularAttribute<SAccesos, Short> orden;
    public static volatile SingularAttribute<SAccesos, Integer> idAcceso;
    public static volatile CollectionAttribute<SAccesos, SAplicaciones> sAplicacionesCollection;
    public static volatile SingularAttribute<SAccesos, Date> fechaServidor;
    public static volatile SingularAttribute<SAccesos, Boolean> activo;

}