package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Conexion;

/**
 *
 * @author NICOLAS
 */
public class Main {
    public static void main(String[] args) {

        MuseoController mc = new MuseoController();
        mc.iniciar();
    }
}
