/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbUtils;

/**
 *
 * @author miguel.sarabia
 */
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolDB {

    public PoolDB() {
    }

    public static Connection getConnection(String nName)
            throws SQLException, NamingException {
       
        InitialContext cxt = new InitialContext();
        DataSource ds = null;

        if (cxt == null) {
            throw new SQLException("No existe el contexto");
        }
        
        
        //Buscar desde el contexto inicial 
        Context envContext = (Context) cxt.lookup("java:/comp/env/jdbc");
        
        ds = (DataSource) envContext.lookup((new StringBuilder()).append("/").append(nName).toString());
        if (ds == null) {//Si no se encuantra Buscar desde el contexto JDBC
            ds = (DataSource) cxt.lookup((new StringBuilder()).append("/").append(nName).toString());
        }
        if (ds == null) {
            throw new SQLException((new StringBuilder()).append("Origen de Datos ").append(nName).append(" no Encontrado!").toString());
        } else {
            Connection conn = ds.getConnection();
            conn.setAutoCommit(true);
            return conn;
        }
    }
}