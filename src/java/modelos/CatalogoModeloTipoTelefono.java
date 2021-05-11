/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import dbUtils.PoolDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;
import objetos.TipoTelefono;
import respuestas.Respuesta;
import respuestas.RespuestaTipoTelefono;
/**
 *
 * @author Erick Corral
 */

@ManagedBean
@RequestScoped
@ViewScoped
public class CatalogoModeloTipoTelefono {
    
    public static RespuestaTipoTelefono getListaTipoTelefono() {
        RespuestaTipoTelefono respFinal = new RespuestaTipoTelefono();
        Respuesta respuesta = new Respuesta();
        ArrayList<TipoTelefono> lista = null;
        Connection con = null;

        try {
           
            con = PoolDB.getConnection("conexion");
            PreparedStatement ps = con.prepareStatement("select ID, DESCRIPCION, CLAVE, ID_TELEFONIA, ACTIVO, FECHA_SERVIDOR from C_TIPO_TELEFONO");
            ResultSet result = ps.executeQuery();
            boolean foundTipoTelefono = false;

            lista = new ArrayList();

            while (result.next()) {
                TipoTelefono datos = new TipoTelefono();

                datos.setId(result.getInt("ID"));
                datos.setDescripcion(result.getString("DESCRIPCION"));
                datos.setClave(result.getString("CLAVE"));
                datos.setIdTelefonia(result.getInt("ID_TELEFONIA"));
                datos.setActivo(result.getBoolean("ACTIVO"));
                datos.setFechaServidor(result.getString("FECHA_SERVIDOR"));

                lista.add(datos);
                foundTipoTelefono = true;
            }

            result.close();
            ps.close();

            if (foundTipoTelefono) {
                respuesta.setId(0);
                respuesta.setMensaje("Consulta exitosa!");

            } else {
                respuesta.setId(1);
                respuesta.setMensaje("No existe información");
            }

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
        respFinal.setListaTipoTelefono(lista);
        return respFinal;
    }
    
     public static Respuesta insertarRegistroTipoTelefono(TipoTelefono tipoTelefono) {

        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {

            con = PoolDB.getConnection("conexion");
            PreparedStatement ps = con.prepareStatement("INSERT INTO C_TIPO_TELEFONO "
                    + "(DESCRIPCION, "
                    + "CLAVE, "
                    + "ID_TELEFONIA, "
                    + "ACTIVO) "
                    + "VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, tipoTelefono.getDescripcion());
            ps.setString(2, tipoTelefono.getClave());
            ps.setInt(3, tipoTelefono.getIdTelefonia());
            ps.setBoolean(4, tipoTelefono.isActivo());

            int reg = ps.executeUpdate();
            if (reg > 0) {
                respuesta.setId(0);
                respuesta.setMensaje("Fila Insertada exitosamente!");
                System.out.println("Una nueva fila se a insertado");
            } else {
                respuesta.setId(1);
                respuesta.setMensaje("No existe información");
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
        return respuesta;
    }

    public static Respuesta editarRegistroTipoTelefono(TipoTelefono tipoTelefono) {
        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {

            con = PoolDB.getConnection("conexion");
            PreparedStatement ps = con.prepareStatement("UPDATE C_TIPO_TELEFONO SET "
                    + "DESCRIPCION=?, "
                    + "CLAVE=?, "
                    + "ID_TELEFONIA=?, "
                    + "ACTIVO=?, "
                    + "FECHA_SERVIDOR = GETDATE() "
                    + "WHERE ID =?" );
                   
            ps.setString(1, tipoTelefono.getDescripcion());
            ps.setString(2, tipoTelefono.getClave());
            ps.setInt(3, tipoTelefono.getIdTelefonia());
            ps.setBoolean(4, tipoTelefono.isActivo());
            ps.setString(5, tipoTelefono.getFechaServidor());
            ps.setInt(6, tipoTelefono.getId() );
                
            int reg = ps.executeUpdate();
            if (reg > 0) {
                respuesta.setId(0);
                respuesta.setMensaje("Fila editada exitosamente!");
                System.out.println("Una fila se a editado");
            } else {
                respuesta.setId(1);
                respuesta.setMensaje("No existe información");
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
        return respuesta;
    }

    public static Respuesta eliminarRegistroTipoTelefono(TipoTelefono tipoTelefono) {

        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {
            con = PoolDB.getConnection("/conexion");
            PreparedStatement ps = con.prepareStatement("DELETE FROM C_TIPO_TELEFONO WHERE ID=?");

            ps.setInt(1, tipoTelefono.getId());
             
            int reg = ps.executeUpdate();
            if (reg > 0) {
                respuesta.setId(0);
                respuesta.setMensaje("Fila eliminada exitosamente!");
                System.out.println("Una nueva fila se a eliminao");
            } else {
                respuesta.setId(1);
                respuesta.setMensaje("No existe información");
            }
            
            ps.close();

        } catch (SQLException ex) {
            respuesta.setId(-1);
            respuesta.setMensaje("Error en la base de datos");
            Logger.getLogger(CatalogoModeloCiudad.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            respuesta.setId(-2);
            respuesta.setMensaje("Error de conexión");
            Logger.getLogger(CatalogoModeloCiudad.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            respuesta.setId(-3);
            respuesta.setMensaje("Error de aplicación");
            Logger.getLogger(CatalogoModeloCiudad.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    respuesta.setId(-4);
                    respuesta.setMensaje("Error al cerrar conexión");
                    Logger.getLogger(CatalogoModeloCiudad.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return respuesta;
    }
}