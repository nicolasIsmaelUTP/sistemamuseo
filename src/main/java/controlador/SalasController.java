package controlador;

import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import modelo.Centro;
import modelo.Sala;
import vista.VistaSalas;

/**
 *
 * @author NICOLAS
 */
public class SalasController {
    public VistaSalas view;
    public Sala sala;
    public Centro centro;

    public SalasController() {
        this.view = new VistaSalas();
        this.sala = new Sala();

        // Cargo los datos de la tabla
        cargarJTable();
        cargarCentros();

        // Action Listeners
        this.view.btn_agregar.addActionListener(e -> agregar());
        this.view.btn_eliminar.addActionListener(e -> eliminar());
        this.view.btn_vaciar.addActionListener(e -> vaciar());
        this.view.btn_mostrarTodo.addActionListener(e -> cargarJTable());
        this.view.BT_FiltrarPorCentro.addActionListener(e -> cargarJTablePorCentro());

        // List Mouse Listener
        this.view.TB_Centro.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String idCentro = view.TB_Centro.getValueAt(view.TB_Centro.getSelectedRow(), 0).toString();
                centro = Centro.getObject(idCentro);
                vaciar();
                view.txt_idcentr.setText(centro.id);
            }
        });

        this.view.table_sala.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = view.table_sala.getSelectedRow();
                String idSala = view.table_sala.getValueAt(fila, 0).toString();
                sala = Sala.getObject(idSala);
                // Ahora lleno los campos de texto con los datos de sala
                view.txt_idsal.setText(sala.id);
                view.txt_idcentr.setText(sala.centroId);
            }
        });
    }

    public void iniciar() {
        this.view.setTitle("Salas");
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }

    public void ocultar() {
        this.view.setVisible(false);
    }

    public void cargarJTable() {
        DefaultTableModel modelo = (DefaultTableModel) view.table_sala.getModel();
        modelo.setRowCount(0);
        for (Sala s : Sala.getAllObjects()) {
            modelo.addRow(new Object[] { s.id, s.centroId });
        }
        view.table_sala.setModel(modelo);
    }

    public void cargarJTablePorCentro() {
        DefaultTableModel modelo = (DefaultTableModel) view.table_sala.getModel();
        modelo.setRowCount(0);
        centro.getSalas();
        System.out.println("se hice el get salas");
        for (String s : centro.salaIds) {
            modelo.addRow(new Object[] { s, centro.id });
            System.out.println("se agrego una sala");
            System.out.println(s);
        }
        view.table_sala.setModel(modelo);
    }

    public void cargarCentros() {
        DefaultTableModel modelo = (DefaultTableModel) view.TB_Centro.getModel();
        modelo.setRowCount(0);
        for (Centro c : Centro.getAllObjects()) {
            modelo.addRow(new Object[] { c.id, c.direccion });
        }
        view.TB_Centro.setModel(modelo);
    }

    public void agregar() {
        // Atributos de Sala: id_sala, Centro_id_centro
        sala.id = view.txt_idsal.getText();
        sala.centroId = view.txt_idcentr.getText();
        sala.create();
        cargarJTable();
    }


    public void eliminar() {
        sala.id = view.txt_idsal.getText();
        sala.delete();
        cargarJTable();
        vaciar();
    }

    public void vaciar() {
        view.txt_idsal.setText("");
        view.txt_idcentr.setText("");
    }
}
