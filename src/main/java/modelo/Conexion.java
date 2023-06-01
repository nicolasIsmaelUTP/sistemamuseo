package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NICOLAS
 */
public class Conexion {

    // JDBC driver name and database URL
    public static Connection getConexion() {
        // Database credentials
        String conexionUrl = "jdbc:sqlserver://LAPTOP-DA3TVIP4:1433;"
                + "database=museo;"
                + "user=sa;"
                + "password=1234;"
                + "TrustServerCertificate=True;"
                + "loginTimeout=30;";

        // Set up the connection with the DB
        try {
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
