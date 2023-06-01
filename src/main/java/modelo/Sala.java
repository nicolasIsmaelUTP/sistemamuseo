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

        // Declarar el objeto PreparedStatement
        PreparedStatement ps = null;

        try {
            String consulta = "INSERT INTO dbo.Sala (Centro_id_centro) VALUES (?)";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.centroId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    static Sala getObject(String id) {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM dbo.Sala WHERE id_sala = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, id);
            rs = ps.executeQuery();

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

        // Declarar el objeto PreparedStatement
        PreparedStatement ps = null;

        try {
            String consulta = "UPDATE dbo.Sala SET Centro_id_centro = ? WHERE id_sala = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.centroId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    void delete() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement
        PreparedStatement ps = null;

        try {
            String consulta = "DELETE FROM dbo.Sala WHERE id_sala = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    void getActivos() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT id_alfanumerico FROM dbo.Activo WHERE Sala_id_sala = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.id);
            rs = ps.executeQuery();

            while (rs.next()) {
                this.activoIds.add(rs.getString("id_alfanumerico"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
