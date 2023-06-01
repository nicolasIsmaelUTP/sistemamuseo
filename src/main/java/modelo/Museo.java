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
public class Museo {
    String id;
    String codigo;
    String nombre;
    String calle;
    String numero;
    String codigoPostal;
    String descripcion;
    ArrayList<String> centroIds = new ArrayList<String>();

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

    static Museo getObject(String id) {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // String consulta = "SELECT * FROM museo WHERE codigo = ?";
            PreparedStatement ps = con.prepareStatement(consulta);
            // ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Museo museo = new Museo();
                museo.id = rs.getInt("id");
                museo.codigo = rs.getString("codigo");
                museo.nombre = rs.getString("nombre");
                museo.calle = rs.getString("calle");
                museo.numero = rs.getString("numero");
                museo.codigoPostal = rs.getString("codigo_postal");
                museo.descripcion = rs.getString("descripcion");
                return museo;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
        return null;
    }

    void update(){
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // String consulta = "UPDATE museo SET codigo = ?, nombre = ? WHERE codigo = ?";
            PreparedStatement ps = con.prepareStatement(consulta);
            // ps.setString(1, this.codigo);
            // ps.setString(2, this.nombre);
            // ps.setString(3, this.codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    void delete(){
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // String consulta = "DELETE FROM museo WHERE codigo = ?";
            PreparedStatement ps = con.prepareStatement(consulta);
            // ps.setString(1, this.codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
