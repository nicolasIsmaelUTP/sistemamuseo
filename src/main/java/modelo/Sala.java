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
public class Sala {
    String id;
    String centroId;
    ArrayList<String> activoIds = new ArrayList<String>();

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

    static Sala getObject(String id) {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // String consulta = "SELECT * FROM sala WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(consulta);
            // ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Sala sala = new Sala();
                sala.id = rs.getString("id");
                sala.centroId = rs.getString("centro_id");
                return sala;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }

        return null;
    }

    void update() {
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

    void delete() {
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

    void getActivos() {
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
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                this.activoIds.add(rs.getString("activo_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
