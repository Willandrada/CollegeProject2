package LojaDeMoveis;

import View.CadastroView;
import View.Login;
import View.MenuPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Raiz {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Raiz.class.getName()).log(Level.SEVERE, null, ex);
        }

        Login p = new Login();
        p.setVisible(true);
    }

    public static boolean verifSaida(JPanel teste) {
        int simounao = YES_OPTION;
        boolean verificar = false;

        for (Component c : teste.getComponents()) {
            verificar = false;

            if (c instanceof JTextField) {
                if (!((JTextField) c).getText().trim().equals("")) {
                    verificar = true;
                }
            } else if (c instanceof JFormattedTextField) {
                if (!((JFormattedTextField) c).getText().trim().equals("")) {
                    verificar = true;
                }
            } else if (c instanceof JTextArea) {
                if (!((JTextArea) c).getText().trim().equals("")) {
                    verificar = true;
                }
            } else if (c instanceof JComboBox) {
                if (((JComboBox) c).getSelectedIndex() > 0) {
                    verificar = true;
                }
            } else if (c instanceof JPanel) {
                verifSaida((JPanel) c);
            }

            if (verificar) {
                simounao = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar? As informações alteradas serão perdidas.", "Atenção", YES_NO_OPTION);

                if (simounao == YES_OPTION) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public static String getStringNumber(String n) {
        n = getStringEmptyDefault(n, "0.0");

        return n.replaceAll(",", ".");
    }

    public static double getNumber(String n) {
        n = getStringEmptyDefault(n.trim(), "");

        if (n.equals("")) {
            return 0.0;
        } else {
            return Double.parseDouble(n.replaceAll(",", "."));
        }
    }

    public static String getStringEmptyDefault(String n, String emptyValue) {
        if (n == null || n.equals("")) {
            return emptyValue;
        } else {
            return n;
        }
    }

    public static String convDataBanco(String dataSistema) {
        java.util.Date dtFormat;
        String data = "";

        if (dataSistema == null || dataSistema.equals("")) {
            return "NULL";
        }

        try {
            dtFormat = new SimpleDateFormat("dd/MM/yyyy").parse(dataSistema);
            data = new SimpleDateFormat("yyyy-MM-dd").format(dtFormat);
        } catch (ParseException ex) {
        }

        if (data.equals("")) {
            return "NULL";
        } else {
            return "'" + data + "'";
        }
    }

    public static String convDataSistema(String dataBanco) {
        java.util.Date dtFormat;
        String data = "";

        if (dataBanco == null || dataBanco.equals("")) {
            return "  /  /    ";
        }

        try {
            dtFormat = new SimpleDateFormat("yyyy-MM-dd").parse(dataBanco);
            data = new SimpleDateFormat("dd/MM/yyyy").format(dtFormat);
        } catch (ParseException ex) {
        }

        return data;
    }

    public static double round(double n, int decimais) {
        if (decimais < 0) {
            System.out.println("Casas decimais não podem ser negativas");
            return n;
        }
        long aux, fator;

        fator = (long) Math.pow(10, decimais);
        n = n * fator;
        aux = Math.round(n);

        return (double) aux / fator;
    }

    public static String tiraNoNum(String c) {
        String retorno = "";

        for (int i = 0; i < c.length(); i++) {
            if ("0123456789".contains(c.substring(i, i + 1))) {
                retorno += c.substring(i, i + 1);
            }
        }

        return retorno;
    }

    public static String tiraNoNum(String c, String excecao) {
        String retorno = "", parte;

        for (int i = 0; i < c.length(); i++) {
            parte = c.substring(i, i + 1);

            if ("0123456789".contains(parte) && !excecao.contains(parte)) {
                retorno += parte;
            }
        }

        return retorno;
    }

    public static Color getCorObrigatorio() {
        Color cor = new Color(255, 255, 204);

        return cor;
    }

    public String formataDataRetornaBD(String data) throws ParseException {
        //veja este metodo....ele recebe uma String como dia/mes/ano e retorna ano/mes/dia  
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date d = df.parse(data);
        df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(d);
        return s;
    }
}
