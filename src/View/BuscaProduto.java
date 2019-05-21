package View;

import Controller.ProdutoController;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class BuscaProduto extends javax.swing.JDialog {

    JFrame parent;

    public BuscaProduto(JFrame parent) {
        super();
        this.parent = parent;
        initComponents();
        carregarGrade();
    }

    public BuscaProduto(JFrame parent, String modo) {
        super();
        this.parent = parent;
        initComponents();
        carregarGrade();
       
    }

    private void carregarGrade() {
        ArrayList<String[]> linhasProdutos = ProdutoController.getProdutos();

        DefaultTableModel tmProdutos = new DefaultTableModel();
        tmProdutos.addColumn("ID");
        tmProdutos.addColumn("Nome");
        tmProdutos.addColumn("Tipo");
        tmProdutos.addColumn("Preco");

        for (String[] c : linhasProdutos) {
            tmProdutos.addRow(c);
        }

        tblProdutos.setModel(tmProdutos);

        tblProdutos.getColumnModel().getColumn(0).setPreferredWidth(50); //ID
        tblProdutos.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblProdutos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblProdutos.getColumnModel().getColumn(3).setPreferredWidth(150);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnFiltro = new javax.swing.JPanel();
        lbFiltro = new javax.swing.JLabel();
        tfFiltro = new javax.swing.JTextField();
        spGrade = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Produtos");

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

        getContentPane().add(pnFiltro, java.awt.BorderLayout.PAGE_START);

        tblProdutos.setAutoCreateRowSorter(true);
        tblProdutos.setModel(tblProdutos.getModel());
        spGrade.setViewportView(tblProdutos);

        getContentPane().add(spGrade, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(500, 350));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tfFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFiltroKeyReleased
        TableRowSorter rs = (TableRowSorter) tblProdutos.getRowSorter();
        rs.setRowFilter(RowFilter.regexFilter("(?i)" + tfFiltro.getText(), 0, 1));
    }//GEN-LAST:event_tfFiltroKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbFiltro;
    private javax.swing.JPanel pnFiltro;
    private javax.swing.JScrollPane spGrade;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField tfFiltro;
    // End of variables declaration//GEN-END:variables
}
