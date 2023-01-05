package GUI;

import civitas.CasillaCalle;

/**
 *
 * @author Alejandro Rosales Cantero
 */
public class PropiedadPanel extends javax.swing.JPanel {

    /**
     * Creates new form PropiedadPanel
     */
    public PropiedadPanel() {
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

        NombreCalle = new javax.swing.JLabel();
        NumCasas = new javax.swing.JLabel();
        NumHoteles = new javax.swing.JLabel();
        PrecioCompra = new javax.swing.JLabel();
        AlquilerBase = new javax.swing.JLabel();
        TextFieldNombreCalle = new javax.swing.JTextField();
        TextFieldNumCasas = new javax.swing.JTextField();
        TextFieldNumHoteles = new javax.swing.JTextField();
        TextFieldPrecioCompra = new javax.swing.JTextField();
        TextFieldAlquilerBase = new javax.swing.JTextField();

        NombreCalle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        NombreCalle.setText("NOMBRE DE LA CALLE:");

        NumCasas.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        NumCasas.setText("Nº CASAS:");

        NumHoteles.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        NumHoteles.setText("Nº HOTELES");

        PrecioCompra.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        PrecioCompra.setText("PRECIO COMPRA:");

        AlquilerBase.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        AlquilerBase.setText("ALQUILER BASE:");

        TextFieldNombreCalle.setEditable(false);
        TextFieldNombreCalle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        TextFieldNumCasas.setEditable(false);
        TextFieldNumCasas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        TextFieldNumHoteles.setEditable(false);
        TextFieldNumHoteles.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        TextFieldPrecioCompra.setEditable(false);
        TextFieldPrecioCompra.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        TextFieldAlquilerBase.setEditable(false);
        TextFieldAlquilerBase.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NombreCalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldNombreCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NumCasas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldNumCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NumHoteles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldNumHoteles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PrecioCompra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AlquilerBase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldAlquilerBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombreCalle)
                    .addComponent(TextFieldNombreCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NumCasas)
                    .addComponent(TextFieldNumCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumHoteles)
                    .addComponent(TextFieldNumHoteles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PrecioCompra)
                    .addComponent(TextFieldPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlquilerBase)
                    .addComponent(TextFieldAlquilerBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AlquilerBase;
    private javax.swing.JLabel NombreCalle;
    private javax.swing.JLabel NumCasas;
    private javax.swing.JLabel NumHoteles;
    private javax.swing.JLabel PrecioCompra;
    private javax.swing.JTextField TextFieldAlquilerBase;
    private javax.swing.JTextField TextFieldNombreCalle;
    private javax.swing.JTextField TextFieldNumCasas;
    private javax.swing.JTextField TextFieldNumHoteles;
    private javax.swing.JTextField TextFieldPrecioCompra;
    // End of variables declaration//GEN-END:variables


    CasillaCalle tituloPropiedad;

    public void setPropiedad(CasillaCalle Propiedad) {
        this.tituloPropiedad = Propiedad;
        
        String nombre = String.valueOf(Propiedad.getNombre());
        String NumCasas = String.valueOf(Propiedad.getNumCasas());
        String NumHoteles = String.valueOf(Propiedad.getNumHoteles());
        String PrecioCompra = String.valueOf(Propiedad.getPrecioCompra());
        String AlquilerBase = String.valueOf(Propiedad.getPrecioBaseAlquiler());
        
        this.TextFieldNombreCalle.setText(nombre);
        this.TextFieldNumCasas.setText(NumCasas);
        this.TextFieldNumHoteles.setText(NumHoteles);
        this.TextFieldPrecioCompra.setText(PrecioCompra);
        this.TextFieldAlquilerBase.setText(AlquilerBase);
        
        repaint();
        revalidate();
    }
}