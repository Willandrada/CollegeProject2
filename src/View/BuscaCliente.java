package View;

import Controller.ClienteController;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class BuscaCliente extends javax.swing.JDialog {

    JFrame parent;

    public BuscaCliente(JFrame parent) {
        super();
        this.parent = parent;
        initComponents();
        carregarGrade();
    }

    public BuscaCliente(JFrame parent, String modo) {
        super();
        this.parent = parent;
        initComponents();
        carregarGrade();
        if (modo.equals("Consulta")) {
            btnSelecionar.setVisible(false);
        }
    }

    private void carregarGrade() {
//        try {
//            ClienteDAO clienteDAO = new ClienteDAO();
//            ClienteTableModel ctm = (ClienteTableModel) tbGrade.getModel();
//            ctm.setDados(clienteDAO.listarTodos());
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Erro ao carregar grade.\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//        }
        ArrayList<String[]> linhasClientes = ClienteController.getClientes();

        DefaultTableModel tmClientes = new DefaultTableModel();
        tmClientes.addColumn("ID");
        tmClientes.addColumn("Nome");
        tmClientes.addColumn("CPF");

        for (String[] c : linhasClientes) {
            tmClientes.addRow(c);
        }

        tblClientes.setModel(tmClientes);

        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(50); //ID
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(150);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnFiltro = new javax.swing.JPanel();
        lbFiltro = new javax.swing.JLabel();
        tfFiltro = new javax.swing.JTextField();
        btnSelecionar = new javax.swing.JButton();
        spGrade = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Clientes");

        pnFiltro.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbFiltro.setText("Filtro:");
        pnFiltro.add(lbFiltro);

        tfFiltro.setColumns(30);
        tfFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFiltroKeyReleased(evt);
            }
        });
        pnFiltro.add(tfFiltro);

        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });
        pnFiltro.add(btnSelecionar);

        getContentPane().add(pnFiltro, java.awt.BorderLayout.PAGE_START);

        tblClientes.setAutoCreateRowSorter(true);
        tblClientes.setModel(tblClientes.getModel());
        spGrade.setViewportView(tblClientes);

        getContentPane().add(spGrade, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(500, 350));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFiltroKeyReleased
        TableRowSorter rs = (TableRowSorter) tblClientes.getRowSorter();
        rs.setRowFilter(RowFilter.regexFilter("(?i)" + tfFiltro.getText(), 0, 1));
    }//GEN-LAST:event_tfFiltroKeyReleased

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        if (tblClientes.getRowCount() > 0) {
            if (tblClientes.getSelectedRow() >= 0) {
                if (parent instanceof VendaCadastro) {
                    VendaCadastro pd = (VendaCadastro) parent;
                    pd.setIDCliente(Integer.parseInt(tblClientes.getModel().getValueAt(tblClientes.getSelectedRow(), 0).toString()));
                    pd.setNomeCliente(tblClientes.getModel().getValueAt(tblClientes.getSelectedRow(), 1).toString());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Campo de destino inválido.", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_btnSelecionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JLabel lbFiltro;
    private javax.swing.JPanel pnFiltro;
    private javax.swing.JScrollPane spGrade;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField tfFiltro;
    // End of variables declaration//GEN-END:variables
}
