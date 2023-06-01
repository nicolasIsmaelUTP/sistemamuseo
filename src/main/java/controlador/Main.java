package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import modelo.Conexion;

/**
 *
 * @author NICOLAS
 */
public class Main {
    public static void main(String[] args) {

        String bases = "";
        try {
            Statement st = Conexion.getConexion().createStatement();

            String consulta = "SELECT name FROM master.dbo.sysdatabases";
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                bases += rs.getString(1) + "\n";
            }

            System.out.println(bases);
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
