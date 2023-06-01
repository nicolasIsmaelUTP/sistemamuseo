package controlador;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;

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

        // Cargo los datos de la tabla
        cargarJTable();

        // Action Listeners
        this.view.btn_agregar.addActionListener(e -> agregar());
        this.view.btn_actualizar.addActionListener(e -> actualizar());
        this.view.btn_eliminar.addActionListener(e -> eliminar());
        this.view.btn_vaciar.addActionListener(e -> vaciar());

        // List Mouse Listener
        this.view.table_museo.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = view.table_museo.getSelectedRow();
                String idMuseo = view.table_museo.getValueAt(fila, 0).toString();
                museo = Museo.getObject(idMuseo);
                // Ahora lleno los campos de texto con los datos de museo
                view.txt_id.setText(museo.id);
                view.txt_codigo.setText(museo.codigo);
                view.txt_nombre.setText(museo.nombre);
                view.txt_calle.setText(museo.calle);
                view.txt_numero.setText(museo.numero);
                view.txt_codigopos.setText(museo.codigoPostal);
                view.txt_descripcion.setText(museo.descripcion);
            }
        });
    }

    public void iniciar() {
        this.view.setTitle("Museo");
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }

    public void ocultar() {
        this.view.setVisible(false);
    }

    public void cargarJTable() {
        DefaultTableModel modelo = (DefaultTableModel) view.table_museo.getModel();
        modelo.setRowCount(0);
        for (Museo m : Museo.getAllObjects()) {
            modelo.addRow(new Object[]{m.id, m.codigo, m.nombre, m.descripcion, m.calle});
        }
        view.table_museo.setModel(modelo);
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
        cargarJTable();
    }

    public void actualizar() {
        // Atributos de Museo: id, codigo, nombre, calle, numero, codigoPostal, descripcion
        museo.codigo = view.txt_codigo.getText();
        museo.nombre = view.txt_nombre.getText();
        museo.calle = view.txt_calle.getText();
        museo.numero = view.txt_numero.getText();
        museo.codigoPostal = view.txt_codigopos.getText();
        museo.descripcion = view.txt_descripcion.getText();
        museo.update();
        cargarJTable();
    }

    public void eliminar() {
        museo.id = view.txt_id.getText();
        museo.delete();
        cargarJTable();
    }

    public void vaciar() {
        view.txt_id.setText("");
        view.txt_codigo.setText("");
        view.txt_nombre.setText("");
        view.txt_calle.setText("");
        view.txt_numero.setText("");
        view.txt_codigopos.setText("");
        view.txt_descripcion.setText("");
    }
}
