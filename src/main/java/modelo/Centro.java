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
        PreparedStatement ps = null;

        try {
            // String consulta = "";
            ps = con.prepareStatement(consulta);
            // ps.setString(1, this.codigo);
            // ps.setString(2, this.nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    static Centro getObject(String id) {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // String consulta = "SELECT * FROM centro WHERE id = ?";
            ps = con.prepareStatement(consulta);
            // ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Centro centro = new Centro();
                centro.id = rs.getString("id");
                centro.museoId = rs.getString("museo_id");
                centro.direccion = rs.getString("direccion");
                return centro;
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
        PreparedStatement ps = null;

        try {
            // String consulta = "";
            ps = con.prepareStatement(consulta);
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
        PreparedStatement ps = null;

        try {
            // Se debe eliminar el registro pero antes se debe eliminar en cascada
            // sus centros asociados
            for (String salaId : this.salaIds) {
                Sala.getObject(salaId).delete();
            }

            // Eliminar el registro
            // String consulta = "";
            ps = con.prepareStatement(consulta);
            // ps.setString(1, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    void getSalas() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // String consulta = "SELECT * FROM sala WHERE centro_id = ?";
            ps = con.prepareStatement(consulta);
            // ps.setString(1, this.id);
            rs = ps.executeQuery();

            while (rs.next()) {
                this.salaIds.add(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
