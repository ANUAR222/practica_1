package GUI;

import civitas.CasillaCalle;
import civitas.Jugador;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Rosales Cantero
 */
public class JugadorPanel extends javax.swing.JPanel {

    /**
     * Creates new form JugadorPanel
     */
    public JugadorPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NombreJugador = new javax.swing.JLabel();
        TextFieldNombreJugador = new javax.swing.JTextField();
        SaldoJugador = new javax.swing.JLabel();
        TextFieldSaldoJugador = new javax.swing.JTextField();
        JugadorEspeculador = new javax.swing.JLabel();
        TextFieldEspeculaor = new javax.swing.JTextField();
        propiedadesPanel = new javax.swing.JPanel();

        NombreJugador.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        NombreJugador.setText("NOMBRE:");
        add(NombreJugador);

        TextFieldNombreJugador.setEditable(false);
        TextFieldNombreJugador.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        add(TextFieldNombreJugador);

        SaldoJugador.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        SaldoJugador.setText("SALDO:");
        add(SaldoJugador);

        TextFieldSaldoJugador.setEditable(false);
        TextFieldSaldoJugador.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        add(TextFieldSaldoJugador);

        JugadorEspeculador.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        JugadorEspeculador.setText("ES ESPECULADOR:");
        add(JugadorEspeculador);

        TextFieldEspeculaor.setEditable(false);
        TextFieldEspeculaor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        add(TextFieldEspeculaor);
        add(propiedadesPanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JugadorEspeculador;
    private javax.swing.JLabel NombreJugador;
    private javax.swing.JLabel SaldoJugador;
    private javax.swing.JTextField TextFieldEspeculaor;
    private javax.swing.JTextField TextFieldNombreJugador;
    private javax.swing.JTextField TextFieldSaldoJugador;
    private javax.swing.JPanel propiedadesPanel;
    // End of variables declaration//GEN-END:variables

    Jugador jugador;

    public void setJugador(Jugador otro) {
        this.jugador = otro;
        
        String nombre = String.valueOf(otro.getNombre());
        String saldo = String.valueOf(otro.getSaldo());
        String especulador = String.valueOf(otro.esEspeculador());
        
        this.TextFieldNombreJugador.setText(nombre);
        this.TextFieldSaldoJugador.setText(saldo);
        this.TextFieldEspeculaor.setText(especulador);
        
        rellenaPropiedades(jugador.getPropiedades());
        
        repaint();
        revalidate();
    }
    
    private void  rellenaPropiedades (ArrayList<CasillaCalle> lista) {
        
        // Se elimina la informacion antigua
        this.propiedadesPanel.removeAll();
        
        // Se recorre la lista de propiedades para ir creando sus vistas
        // individuales y añadirlas al panel
        for(CasillaCalle t : lista){
            PropiedadPanel vistaPropiedad = new PropiedadPanel();
            vistaPropiedad.setPropiedad(t);
            
            propiedadesPanel.add(vistaPropiedad);
            vistaPropiedad.setVisible(true);
        }
        
        // Se fuerza la actualización visual del papnel propiedades y del panel
        // del jugador
        repaint();
        revalidate();
    }
    
}
