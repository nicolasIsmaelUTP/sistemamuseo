package controlador;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import modelo.Museo;
import vista.VistaMuseo;

/**
 *
 * @author NICOLAS
 */
public class MuseoController {
    public VistaMuseo view;
    public Museo museo;

    public MuseoController() {
        this.view = new VistaMuseo();
        this.museo = new Museo();

        // Action Listeners
        this.view.btn_agregar.addActionListener(e -> agregar());
        this.view.btn_actualizar.addActionListener(e -> actualizar());
        this.view.btn_eliminar.addActionListener(e -> eliminar());
    }

    public void iniciar() {
        this.view.setTitle("Museo");
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }

    public void ocultar() {
        this.view.setVisible(false);
    }

    public void cargarJList() {
        DefaultListModel<String> modelo = new DefaultListModel<String>();
        modelo.clear();
        view.list_museo.setModel(modelo);
        for (Museo museo : Museo.getAllObjects()) {
            modelo.addElement(museo.toString());
        }
    }

    public void agregar() {
        // Atributos de Museo: id, codigo, nombre, calle, numero, codigoPostal, descripcion
        museo.id = view.txt_id.getText();
        museo.codigo = view.txt_codigo.getText();
        museo.nombre = view.txt_nombre.getText();
        museo.calle = view.txt_calle.getText();
        museo.numero = view.txt_numero.getText();
        museo.codigoPostal = view.txt_codigopos.getText();
        museo.descripcion = view.txt_descripcion.getText();
        museo.create();
        cargarJList();
    }

    public void actualizar(){
        // Atributos de Museo: id, codigo, nombre, calle, numero, codigoPostal, descripcion
        museo.codigo = view.txt_codigo.getText();
        museo.nombre = view.txt_nombre.getText();
        museo.calle = view.txt_calle.getText();
        museo.numero = view.txt_numero.getText();
        museo.codigoPostal = view.txt_codigopos.getText();
        museo.descripcion = view.txt_descripcion.getText();
        museo.update();
        cargarJList();
    }

    public void eliminar(){
        museo.id = view.txt_id.getText();
        museo.delete();
        cargarJList();
    }
}
