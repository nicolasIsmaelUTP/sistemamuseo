package controlador;

import java.util.ArrayList;

import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import modelo.Centro;
import modelo.Museo;
import vista.VistaCentros;

/**
 *
 * @author NICOLAS
 */
public class CentrosController {
    public VistaCentros view;
    public Centro centro;
    public Museo museo;

    public CentrosController() {
        this.view = new VistaCentros();
        this.centro = new Centro();

        // Cargo los datos de la tabla
        cargarJTable();
        cargarMuseos();

        // Action Listeners
        this.view.btn_agregar.addActionListener(e -> agregar());
        this.view.btn_actualizar.addActionListener(e -> actualizar());
        this.view.btn_eliminar.addActionListener(e -> eliminar());
        this.view.btn_vaciar.addActionListener(e -> vaciar());
        this.view.btn_mostrarTodos.addActionListener(e -> cargarJTable());
        this.view.BT_FiltrarPorMuseo.addActionListener(e -> cargarJTablePorMuseo());

        // List Mouse Listener
        this.view.table_museos.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String idMuseo = view.table_museos.getValueAt(view.table_museos.getSelectedRow(), 0).toString();
                museo = Museo.getObject(idMuseo);
                vaciar();
                view.TF_MuseoID.setText(museo.id);
            }
        });

        this.view.table_centros.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = view.table_centros.getSelectedRow();
                String idCentro = view.table_centros.getValueAt(fila, 0).toString();
                centro = Centro.getObject(idCentro);
                // Ahora lleno los campos de texto con los datos de museo
                view.txt_idcen.setText(centro.id);
                view.TF_MuseoID.setText(centro.museoId);
                view.txt_direc.setText(centro.direccion);
            }
        });
    }

    public void iniciar() {
        this.view.setTitle("Centros");
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }

    public void ocultar() {
        this.view.setVisible(false);
    }

    public void cargarJTable() {
        DefaultTableModel modelo = (DefaultTableModel) view.table_centros.getModel();
        modelo.setRowCount(0);
        for (Centro c : Centro.getAllObjects()) {
            modelo.addRow(new Object[] { c.id, c.museoId, c.direccion });
        }
        view.table_centros.setModel(modelo);
    }

    public void cargarJTablePorMuseo() {
        DefaultTableModel modelo = (DefaultTableModel) view.table_centros.getModel();
        modelo.setRowCount(0);
        museo.getCentros();
        for (String idCentro : museo.centroIds) {
            Centro c = Centro.getObject(idCentro);
            modelo.addRow(new Object[] { c.id, c.museoId, c.direccion });
        }

        view.table_centros.setModel(modelo);
    }

    public void cargarMuseos() {
        DefaultTableModel modelo = (DefaultTableModel) view.table_museos.getModel();
        modelo.setRowCount(0);
        for (Museo m : Museo.getAllObjects()) {
            modelo.addRow(new Object[] { m.id, m.nombre });
        }
        view.table_museos.setModel(modelo);
    }

    public void agregar() {
        // Atributos de Centro: id_centro, Museo_codigo_autonumerico, direccion
        centro.id = view.txt_idcen.getText();
        centro.museoId = view.TF_MuseoID.getText();
        centro.direccion = view.txt_direc.getText();
        centro.create();
        cargarJTable();
    }

    private void actualizar() {
        // Atributos de Centro: id_centro, Museo_codigo_autonumerico, direccion
        centro.id = view.txt_idcen.getText();
        centro.museoId = view.TF_MuseoID.getText();
        centro.direccion = view.txt_direc.getText();
        centro.update();
        cargarJTable();
    }

    private void eliminar() {
        centro.id = view.txt_idcen.getText();
        centro.delete();
        cargarJTable();
    }

    private void vaciar() {
        view.txt_idcen.setText("");
        view.txt_direc.setText("");
        view.TF_MuseoID.setText("");
    }
}
