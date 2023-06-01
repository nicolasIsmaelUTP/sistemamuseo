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
    public String id;
    public String museoId;
    public String direccion;
    public ArrayList<String> salaIds = new ArrayList<String>();

    public void create() {
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

    public static Centro getObject(String id) {
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
                centro.id = rs.getString("id_centro");
                centro.museoId = rs.getString("Museo_codigo_autonumerico");
                centro.direccion = rs.getString("direccion");
                return centro;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }

        return null;
    }

    public static ArrayList<Centro> getAllObjects() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto Statement y ResultSet
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM dbo.Centro";
            stmt = con.createStatement();
            rs = stmt.executeQuery(consulta);

            ArrayList<Centro> centros = new ArrayList<Centro>();

            while (rs.next()) {
                Centro centro = new Centro();
                centro.id = rs.getString("id_centro");
                centro.museoId = rs.getString("Museo_codigo_autonumerico");
                centro.direccion = rs.getString("direccion");
                centros.add(centro);
            }

            return centros;
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

    public void delete() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement ps = null;

        try {
            // Se debe eliminar el registro pero antes se debe eliminar en cascada
            // sus centros asociados
            getSalas();
            for (String salaId : this.salaIds) {
                Sala.getObject(salaId).delete();
            }

            // Eliminar el registro
            String consulta = "DELETE FROM dbo.Centro WHERE id_centro = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public void getSalas() {
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
