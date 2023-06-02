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
    public String id;
    public String centroId;
    public ArrayList<String> activoIds = new ArrayList<String>();

    public void create() {
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

    public static Sala getObject(String id) {
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
                sala.id = rs.getString("id_sala");
                sala.centroId = rs.getString("Centro_id_centro");
                return sala;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }

        return null;
    }

    public static ArrayList<Sala> getAllObjects() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto Statement y ResultSet
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM dbo.Sala";
            stmt = con.createStatement();
            rs = stmt.executeQuery(consulta);

            ArrayList<Sala> salas = new ArrayList<Sala>();

            while (rs.next()) {
                Sala sala = new Sala();
                sala.id = rs.getString("id_sala");
                sala.centroId = rs.getString("Centro_id_centro");
                salas.add(sala);
            }

            return salas;
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }

        return null;
    }


    public void update() {
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

    public void delete() {
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

    public void getActivos() {
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
