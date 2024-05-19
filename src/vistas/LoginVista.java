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
        jPanel1 = new javax.swing.JPanel();
        InicioSesionContainer = new javax.swing.JPanel();
        txt_inicio_usuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        btnNuevoUsuario = new javax.swing.JButton();
        txt_inicio_password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
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
        jLabel5 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
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
        setPreferredSize(new java.awt.Dimension(800, 510));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        InicioSesionContainer.setBackground(new java.awt.Color(255, 255, 255));
        InicioSesionContainer.setToolTipText("Inicio");

        txt_inicio_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inicio_usuarioActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Usuario");

        btnIniciarSesion.setBackground(new java.awt.Color(0, 102, 102));
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Continuar");
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setDefaultCapable(false);
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setFocusable(false);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        btnNuevoUsuario.setBackground(new java.awt.Color(0, 204, 102));
        btnNuevoUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoUsuario.setText("Nuevo Usuario");
        btnNuevoUsuario.setBorderPainted(false);
        btnNuevoUsuario.setDefaultCapable(false);
        btnNuevoUsuario.setFocusPainted(false);
        btnNuevoUsuario.setFocusable(false);
        btnNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoUsuarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Contraseña:");

        javax.swing.GroupLayout InicioSesionContainerLayout = new javax.swing.GroupLayout(InicioSesionContainer);
        InicioSesionContainer.setLayout(InicioSesionContainerLayout);
        InicioSesionContainerLayout.setHorizontalGroup(
            InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioSesionContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(txt_inicio_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_inicio_password, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioSesionContainerLayout.createSequentialGroup()
                        .addComponent(btnNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        InicioSesionContainerLayout.setVerticalGroup(
            InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioSesionContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txt_inicio_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGroup(InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicioSesionContainerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_inicio_password, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 76, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioSesionContainerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(InicioSesionContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIniciarSesion)
                            .addComponent(btnNuevoUsuario))
                        .addGap(18, 18, 18))))
        );

        jPanel1.add(InicioSesionContainer);
        InicioSesionContainer.setBounds(50, 120, 289, 230);

        NuevoUsuarioContainer.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Nombre de Usuario");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nombre Completo");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Constraseña");

        labelPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPhone.setText("Numero Telefonico");

        btnRegistrarContinuar.setBackground(new java.awt.Color(0, 102, 102));
        btnRegistrarContinuar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarContinuar.setText("Continuar");
        btnRegistrarContinuar.setBorderPainted(false);
        btnRegistrarContinuar.setDefaultCapable(false);
        btnRegistrarContinuar.setFocusPainted(false);
        btnRegistrarContinuar.setFocusable(false);
        btnRegistrarContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarContinuarActionPerformed(evt);
            }
        });

        btnRegistrarVolver.setBackground(new java.awt.Color(0, 102, 102));
        btnRegistrarVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarVolver.setText("Volver");
        btnRegistrarVolver.setBorderPainted(false);
        btnRegistrarVolver.setDefaultCapable(false);
        btnRegistrarVolver.setFocusPainted(false);
        btnRegistrarVolver.setFocusable(false);
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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Verifica Contraseña");

        javax.swing.GroupLayout NuevoUsuarioContainerLayout = new javax.swing.GroupLayout(NuevoUsuarioContainer);
        NuevoUsuarioContainer.setLayout(NuevoUsuarioContainerLayout);
        NuevoUsuarioContainerLayout.setHorizontalGroup(
            NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                        .addComponent(btnRegistrarVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrarContinuar))
                    .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                        .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRegistrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPhone)
                            .addComponent(txtRegistrarTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRegistrarNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRegistrarPasswordV, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRegistrarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        NuevoUsuarioContainerLayout.setVerticalGroup(
            NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevoUsuarioContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegistrarNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegistrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelPhone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegistrarTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegistrarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegistrarPasswordV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(NuevoUsuarioContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarVolver)
                    .addComponent(btnRegistrarContinuar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel1.add(NuevoUsuarioContainer);
        NuevoUsuarioContainer.setBounds(60, 90, 260, 380);
        NuevoUsuarioContainer.getAccessibleContext().setAccessibleName("Inicio de Sesion");
        NuevoUsuarioContainer.getAccessibleContext().setAccessibleDescription("");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Iniciar Sesión");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(80, 20, 240, 71);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(400, 0, 900, 730);

        Left.setBackground(new java.awt.Color(0, 102, 102));
        Left.setPreferredSize(new java.awt.Dimension(400, 500));
        Left.setLayout(null);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        Left.add(jLabel7);
        jLabel7.setBounds(150, 104, 0, 0);

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Inventario 3000");
        Left.add(jLabel9);
        jLabel9.setBounds(69, 257, 270, 47);

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Bienvenido");
        Left.add(jLabel10);
        jLabel10.setBounds(150, 360, 110, 26);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/logo.png"))); // NOI18N
        Left.add(jLabel11);
        jLabel11.setBounds(156, 104, 100, 135);

        conexionState.setForeground(new java.awt.Color(255, 255, 255));
        conexionState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conexionState.setText("jLabel5");
        Left.add(conexionState);
        conexionState.setBounds(20, 410, 360, 16);

        getContentPane().add(Left);
        Left.setBounds(0, 0, 400, 500);

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

                lVista.NuevoUsuarioContainer.setVisible(false);
                
                lVista.setLocationRelativeTo(null);
                lVista.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel InicioSesionContainer;
    private javax.swing.JPanel Left;
    public javax.swing.JPanel NuevoUsuarioContainer;
    public javax.swing.JButton btnIniciarSesion;
    public javax.swing.JButton btnNuevoUsuario;
    public javax.swing.JButton btnRegistrarContinuar;
    public javax.swing.JButton btnRegistrarVolver;
    public javax.swing.JLabel conexionState;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelPhone;
    public javax.swing.JTextField txtRegistrarNombreCompleto;
    public javax.swing.JTextField txtRegistrarPassword;
    public javax.swing.JTextField txtRegistrarPasswordV;
    public javax.swing.JTextField txtRegistrarTelefono;
    public javax.swing.JTextField txtRegistrarUsuario;
    public javax.swing.JPasswordField txt_inicio_password;
    public javax.swing.JTextField txt_inicio_usuario;
    // End of variables declaration//GEN-END:variables
}
