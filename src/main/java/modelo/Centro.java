package modelo;

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
public class Centro {
    String id;
    String museoId;
    String direccion;
    ArrayList<String> salaIds = new ArrayList<String>();

    void create() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // String consulta = "";
            PreparedStatement ps = con.prepareStatement(consulta);
            // ps.setString(1, this.codigo);
            // ps.setString(2, this.nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    void getSalas() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // String consulta = "SELECT * FROM sala WHERE centro_id = ?";
            PreparedStatement ps = con.prepareStatement(consulta);
            // ps.setString(1, this.id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                this.salaIds.add(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
