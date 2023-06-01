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

        // Declarar el objeto PreparedStatement
        PreparedStatement ps = null;

        try {
            String consulta = "INSERT INTO dbo.Centro (Museo_codigo_autonumerico, direccion) VALUES (?, ?)";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.museoId);
            ps.setString(2, this.direccion);
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
            String consulta = "SELECT * FROM dbo.Centro WHERE id_centro = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, id);
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

        // Declarar el objeto PreparedStatement
        PreparedStatement ps = null;

        try {
            String consulta = "UPDATE dbo.Centro SET Museo_codigo_autonumerico = ?, direccion = ? WHERE id_centro = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.museoId);
            ps.setString(2, this.direccion);
            ps.setString(3, this.id);
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
            String consulta = "DELETE FROM dbo.Centro WHERE id_centro = ?";
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
            String consulta = "SELECT * FROM sala WHERE Centro_id_centro = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.id);
            rs = ps.executeQuery();

            while (rs.next()) {
                this.salaIds.add(rs.getString("id_sala"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
