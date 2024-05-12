/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas; 

import controlador.ControladorLogin;

public class LoginVista extends javax.swing.JFrame {
    
    public LoginVista() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        NuevoUsuarioContainer = new javax.swing.JPanel();
        txtRegistrarNombreCompleto = new javax.swing.JTextField();
        txtRegistrarUsuario = new javax.swing.JTextField();
        txtRegistrarPassword = new javax.swing.JTextField();
        txtRegistrarPasswordV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelPhone = new javax.swing.JLabel();
        btnRegistrarContinuar = new javax.swing.JButton();
        btnRegistrarVolver = new javax.swing.JButton();
        txtRegistrarTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        InicioSesionContainer = new javax.swing.JPanel();
        txt_inicio_usuario = new javax.swing.JTextField();
        txt_inicio_password = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        btnNuevoUsuario = new javax.swing.JButton();
        conexionState = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio");

        NuevoUsuarioContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Usuario"));
        NuevoUsuarioContainer.setToolTipText("Inicio");
        NuevoUsuarioContainer.setName("nuevoUsuario"); // NOI18N

        txtRegistrarNombreCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegistrarNombreCompletoActionPerformed(evt);
            }
        });

        txtRegistrarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegistrarPasswordActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre de Usuario:");

        jLabel4.setText("Nombre Completo:");

        jLabel6.setText("Constraseña:");

        labelPhone.setText("Numero Telefonico:");

        btnRegistrarContinuar.setText("Continuar");
        btnRegistrarContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarContinuarActionPerformed(evt);
            }
        });

        btnRegistrarVolver.setText("Volver");
        btnRegistrarVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVolverActionPerformed(evt);
            }
        });

        txtRegistrarTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegistrarTelefonoActionPerformed(evt);
            }
        });

        jLabel8.setText("Verifica Contraseña:");

        javax.swing.GroupLayout NuevoUsuarioContainerLayout = new javax.swing.GroupLayout(NuevoUsuarioContainer);
        NuevoUsuarioContainer.setLayout(NuevoUsuarioContainerLayout);
        NuevoUsuarioContainerLayout.setHorizontalGroup(
            NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnRegistrarVolver)))
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRegistrarNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(txtRegistrarUsuario))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevoUsuarioContainerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrarContinuar)
                        .addGap(61, 61, 61))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevoUsuarioContainerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel6))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(36, 36, 36)
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRegistrarPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(txtRegistrarPasswordV))
                .addGap(91, 91, 91))
            .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(labelPhone)
                .addGap(36, 36, 36)
                .addComponent(txtRegistrarTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        NuevoUsuarioContainerLayout.setVerticalGroup(
            NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRegistrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRegistrarNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRegistrarTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPhone))
                .addGap(18, 18, 18)
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRegistrarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRegistrarPasswordV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(48, 48, 48)
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarVolver)
                    .addComponent(btnRegistrarContinuar))
                .addContainerGap())
        );

        InicioSesionContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Inicio de Sesión"));
        InicioSesionContainer.setToolTipText("Inicio");

        txt_inicio_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inicio_usuarioActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        btnIniciarSesion.setText("Continuar");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        btnNuevoUsuario.setText("Nuevo Usuario");
        btnNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InicioSesionContainerLayout = new javax.swing.GroupLayout(InicioSesionContainer);
        InicioSesionContainer.setLayout(InicioSesionContainerLayout);
        InicioSesionContainerLayout.setHorizontalGroup(
            InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioSesionContainerLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnNuevoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIniciarSesion)
                .addGap(29, 29, 29))
            .addGroup(InicioSesionContainerLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(40, 40, 40)
                .addGroup(InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_inicio_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_inicio_usuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        InicioSesionContainerLayout.setVerticalGroup(
            InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioSesionContainerLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_inicio_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioSesionContainerLayout.createSequentialGroup()
                        .addGroup(InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_inicio_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioSesionContainerLayout.createSequentialGroup()
                        .addGroup(InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevoUsuario)
                            .addComponent(btnIniciarSesion))
                        .addContainerGap())))
        );

        conexionState.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(InicioSesionContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NuevoUsuarioContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(conexionState)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InicioSesionContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(NuevoUsuarioContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(conexionState)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        NuevoUsuarioContainer.getAccessibleContext().setAccessibleName("Inicio de Sesion");
        NuevoUsuarioContainer.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_inicio_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inicio_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_inicio_usuarioActionPerformed

    private void txtRegistrarNombreCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegistrarNombreCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegistrarNombreCompletoActionPerformed

    private void txtRegistrarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegistrarPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegistrarPasswordActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        //Click Event
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnRegistrarContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarContinuarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarContinuarActionPerformed

    private void btnNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoUsuarioActionPerformed
        // TODO add your handling code here:  
    }//GEN-LAST:event_btnNuevoUsuarioActionPerformed

    private void btnRegistrarVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVolverActionPerformed
        // TODO add your handling code here: 
    }//GEN-LAST:event_btnRegistrarVolverActionPerformed

    private void txtRegistrarTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegistrarTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegistrarTelefonoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginVista lVista = new LoginVista();
                ControladorLogin controler = new ControladorLogin(lVista);

                //lVista.NuevoUsuarioContainer.setVisible(false);
                
                lVista.setLocationRelativeTo(null);
                lVista.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel InicioSesionContainer;
    public javax.swing.JPanel NuevoUsuarioContainer;
    public javax.swing.JButton btnIniciarSesion;
    public javax.swing.JButton btnNuevoUsuario;
    public javax.swing.JButton btnRegistrarContinuar;
    public javax.swing.JButton btnRegistrarVolver;
    public javax.swing.JLabel conexionState;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel labelPhone;
    public javax.swing.JTextField txtRegistrarNombreCompleto;
    public javax.swing.JTextField txtRegistrarPassword;
    public javax.swing.JTextField txtRegistrarPasswordV;
    public javax.swing.JTextField txtRegistrarTelefono;
    public javax.swing.JTextField txtRegistrarUsuario;
    public javax.swing.JTextField txt_inicio_password;
    public javax.swing.JTextField txt_inicio_usuario;
    // End of variables declaration//GEN-END:variables
}
