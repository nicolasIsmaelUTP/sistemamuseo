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
    public String id;
    public String codigo;
    public String nombre;
    public String calle;
    public String numero;
    public String codigoPostal;
    public String descripcion;
    public ArrayList<String> centroIds = new ArrayList<String>();

    public void create() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement
        PreparedStatement ps = null;

        try {
            String consulta = "INSERT INTO dbo.Museo (codigo_unico, nombre, calle, numero, codigo_postal, descripcion) VALUES (?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(consulta);
            ps.setString(1, this.codigo);
            ps.setString(2, this.nombre);
            ps.setString(3, this.calle);
            ps.setString(4, this.numero);
            ps.setString(5, this.codigoPostal);
            ps.setString(6, this.descripcion);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static Museo getObject(String id) {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM dbo.Museo WHERE codigo_autonumerico = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Museo museo = new Museo();
                museo.id = rs.getString("codigo_autonumerico");
                museo.codigo = rs.getString("codigo_unico");
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

    public static ArrayList<Museo> getAllObjects() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto Statement y ResultSet
        Statement st = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM dbo.Museo";
            st = con.createStatement();
            rs = st.executeQuery(consulta);

            ArrayList<Museo> museos = new ArrayList<>();

            while (rs.next()) {
                Museo museo = new Museo();
                museo.id = rs.getString("codigo_autonumerico");
                museo.codigo = rs.getString("codigo_unico");
                museo.nombre = rs.getString("nombre");
                museo.calle = rs.getString("calle");
                museo.numero = rs.getString("numero");
                museo.codigoPostal = rs.getString("codigo_postal");
                museo.descripcion = rs.getString("descripcion");
                museos.add(museo);
            }
            return museos;
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
        return null;
    }

    public void update() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement ps = null;

        try {
            String consulta = "UPDATE dbo.Museo SET codigo_unico = ?, nombre = ?, calle = ?, numero = ?, codigo_postal = ?, descripcion = ? WHERE codigo_autonumerico = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.codigo);
            ps.setString(2, this.nombre);
            ps.setString(3, this.calle);
            ps.setString(4, this.numero);
            ps.setString(5, this.codigoPostal);
            ps.setString(6, this.descripcion);
            ps.setString(7, this.id);
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
            for (String centroId : this.centroIds) {
                Centro.getObject(centroId).delete();
            }

            // Eliminar el registro del museo
            String consulta = "DELETE FROM dbo.Museo WHERE codigo_autonumerico = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public void getCentros() {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Declarar el objeto PreparedStatement y ResultSet
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT id_centro FROM dbo.Centro WHERE Museo_codigo_autonumerico = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, this.id);
            rs = ps.executeQuery();

            while (rs.next()) {
                this.centroIds.add(rs.getString("id_centro"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public String toString() {
        return this.nombre;
    }
}
