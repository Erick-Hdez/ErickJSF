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
import objetos.Ciudad;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import respuestas.Respuesta;
import respuestas.RespuestaCiudad;

/**
 *
 * @author Erick Corral
 * @fecha 2021-04-16
 */
public class CatalogoModeloCiudad {
    
    public static RespuestaCiudad getListaCiudades() {
        RespuestaCiudad respFinal = new RespuestaCiudad();
        Respuesta respuesta = new Respuesta();
        ArrayList<Ciudad> lista = null;
        Connection con = null;
        
//        System.out.println(filtroDescripcion);
//        System.out.println(filtroLada);
        try {
            con = PoolDB.getConnection("conexion");
           
            String query = ("select ID_CIUDAD,"
                    + "DESCRIPCION, "
                    + "CODIGO, "
                    + "LADA, "
                    + "ACTIVO, "
                    + "FECHA_ALTA, "
                    + "FECHA_BAJA, "
                    + "FECHA_SERVIDOR "
                    + "from C_CIUDAD ");
//            
//            if (!filtroDescripcion.trim().equals("") && filtroLada > 0) {
//                query += " WHERE DESCRIPCION LIKE '%" + filtroDescripcion + "%' " + " AND LADA LIKE '%" + filtroLada + "%'";
//            } else if (filtroLada > 0) {
//                query += " WHERE LADA LIKE '%" + filtroLada + "%'";
//            } else if (!filtroDescripcion.trim().equals("")) {
//                 query += " WHERE DESCRIPCION LIKE '%" + filtroDescripcion + "%' ";
//            }          
//            
            PreparedStatement ps = con.prepareStatement(query);                   

            ResultSet result = ps.executeQuery();
            boolean foundCiudad = false;

            lista = new ArrayList();

            while (result.next()) {
                Ciudad datos = new Ciudad();

                datos.setIdCiudad(result.getInt("ID_CIUDAD"));
                datos.setDescripcion(result.getString("DESCRIPCION"));
                datos.setCodigo(result.getInt("CODIGO"));
                datos.setLada(result.getInt("LADA"));
                datos.setActivo(result.getBoolean("ACTIVO"));
                datos.setFechaAlta(result.getString("FECHA_ALTA"));
                datos.setFechaBaja(result.getString("FECHA_BAJA"));
                datos.setFechaServidor(result.getString("FECHA_SERVIDOR"));

                lista.add(datos);
                foundCiudad = true;
            }
            result.close();
            ps.close();

            if (foundCiudad) {
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
        respFinal.setListaCiudad(lista);
        return respFinal;
    }

    public static Respuesta insertarRegistroCiudad(Ciudad ciudad) {

        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {

            con = PoolDB.getConnection("conexion");
            PreparedStatement ps = con.prepareStatement("INSERT INTO C_CIUDAD "
                    + "(DESCRIPCION, "
                    + "CODIGO, "
                    + "LADA, "
                    + "ACTIVO, "
                    + "FECHA_ALTA) "
                    + "VALUES (?, ?, ?, ?, GETDATE())"
            );

            ps.setString(1, ciudad.getDescripcion());
            ps.setInt(2, ciudad.getCodigo());
            ps.setInt(3, ciudad.getLada());
            ps.setBoolean(4, ciudad.isActivo());

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

    public static Respuesta editarRegistroCiudad(Ciudad ciudad) {
        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {

            con = PoolDB.getConnection("conexion");
            PreparedStatement ps = con.prepareStatement("UPDATE C_CIUDAD SET "
                    + "DESCRIPCION=?, "
                    + "LADA=?, "
                    + "ACTIVO=? "
                    + "WHERE ID_CIUDAD =?");

            ps.setString(1, ciudad.getDescripcion());
            ps.setInt(2, ciudad.getLada());
            ps.setBoolean(3, ciudad.isActivo());
            ps.setInt(4, ciudad.getIdCiudad());

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

    public static Respuesta eliminarRegistroCiudad(Ciudad ciudad) {

        Respuesta respuesta = new Respuesta();
        Connection con = null;

        try {
            con = PoolDB.getConnection("conexion");
            PreparedStatement ps = con.prepareStatement("DELETE FROM C_CIUDAD WHERE ID_CIUDAD=?");

            ps.setInt(1, ciudad.getIdCiudad());

            int reg = ps.executeUpdate();
            if (reg > 0) {
                respuesta.setId(0);
                respuesta.setMensaje("Fila eliminada exitosamente!");
                System.out.println("Una nueva fila se a eliminado");
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
    
    
    //<editor-fold defaultstate="collapsed" desc="GETS y SETS">
    
    /**
     * @return the filtroDescripcion
     */
//    public String getFiltroDescripcion() {
//        return filtroDescripcion;
//    }
//    
//    /**
//     * @param filtroDescripcion the filtroDescripcion to set
//     */
//    public void setFiltroDescripcion(String filtroDescripcion) {
//        this.filtroDescripcion = filtroDescripcion;
//    }
//    
//    /**
//     * @return the filtroLada
//     */
//    public int getFiltroLada() {
//        return filtroLada;
//    }
//    
//    /**
//     * @param filtroLada the filtroLada to set
//     */
//    public void setFiltroLada(int filtroLada) {
//        this.filtroLada = filtroLada;
//    }
//</editor-fold>
}

