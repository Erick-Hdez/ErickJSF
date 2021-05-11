package modelos;


import dbUtils.PoolDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingException;
import objetos.Usuario;
import respuestas.Respuesta;
import respuestas.RespuestaUsuario;
import utils.HexDigest;

/**
 *
 * @author Erick Corral
 */

@ManagedBean
@RequestScoped
public class UsuarioModelo {

    public static RespuestaUsuario getUsuario(Usuario usuario) {
        RespuestaUsuario respFinal = new RespuestaUsuario();
        HexDigest encriptar = new HexDigest();
        Respuesta respuesta = new Respuesta();
        Usuario login = null;
        Connection con = null;

        try {
            con = PoolDB.getConnection("conexion");

            String passwordEncriptada = encriptar.hexDigest(usuario.getPasword());
            System.out.println(passwordEncriptada);

            PreparedStatement ps = con.prepareStatement("SELECT "
                    + "ID_USUARIO, "
                    + "ID_PERFIL, "
                    + "USUARIO, "
                    + "NOMBRE_USUARIO, "
                    + "PASSWORD, "
                    + "CORREO, "
                    + "ACTIVO, "
                    + "ULTIMA_SESION, "
                    + "FECHA_ALTA, "
                    + "FECHA_BAJA, "
                    + "FECHA_SERVIDOR, "
                    + "ID_USUARIO_MODIFICA, "
                    + "ID_CLIENTE FROM S_USUARIOS where USUARIO=? AND PASSWORD=?");

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, passwordEncriptada);

            ResultSet rs = ps.executeQuery();
            login = new Usuario();
            if (rs.next()) {
                login.setUsuario(rs.getString("USUARIO"));
                login.setPasword(rs.getString("PASSWORD"));
                login.setIdUsuario(rs.getInt("ID_USUARIO"));
                login.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));

                respuesta.setId(0);
                respuesta.setMensaje("Autenticación exitosa!");

            } else {
                respuesta.setId(1);
                respuesta.setMensaje("Contraseña invalida");
            }

            ps.close();

        } catch (SQLException ex) {
            respuesta.setId(-1);
            respuesta.setMensaje("Error en la base de datos");
            Logger.getLogger(CatalogoModeloCiudad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setId(-2);
            respuesta.setMensaje("Error de conexión");
            Logger.getLogger(CatalogoModeloCiudad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setId(-3);
            respuesta.setMensaje("Error de aplicación");
            Logger.getLogger(CatalogoModeloCiudad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    respuesta.setId(-4);
                    respuesta.setMensaje("Error al cerrar conexión");
                    Logger.getLogger(CatalogoModeloCiudad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        respFinal.setRespuesta(respuesta);
        respFinal.setLogin(login);
        return respFinal;
    }
}

