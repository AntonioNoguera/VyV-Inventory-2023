package controlador;

import Utils.PasswordUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import modelo_dao.UsuarioDAO;
import modelo.Usuario;
import vistas.LoginVista;
import vistas.MovimientosVista;

/**
 *
 * @author Michael Noguera
 */
public class ControladorLogin implements ActionListener, KeyListener { 
    
    UsuarioDAO daoInstance = new UsuarioDAO();
    LoginVista uVista = new LoginVista();
    
    Usuario actualUser = new Usuario() ;
    
    public ControladorLogin(LoginVista v){
        this.uVista = v; 
        
        this.uVista.btnIniciarSesion.addActionListener(this);
        this.uVista.btnNuevoUsuario.addActionListener(this);
        this.uVista.btnRegistrarContinuar.addActionListener(this);
        this.uVista.btnRegistrarVolver.addActionListener(this);
        
        setConextionState();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Login Related
        if(e.getSource() == uVista.btnIniciarSesion){
            try {
                // TODO add your handling code here:
                
                intentoLogin(); 
            } catch (Exception ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == uVista.btnNuevoUsuario){
            this.uVista.InicioSesionContainer.setVisible(false);
            this.uVista.NuevoUsuarioContainer.setVisible(true);
        }
        
        //Register Related
        if(e.getSource() == uVista.btnRegistrarContinuar){
            try {
                intentoRegistro();
            } catch (Exception ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == uVista.btnRegistrarVolver){
            this.uVista.InicioSesionContainer.setVisible(true);
            this.uVista.NuevoUsuarioContainer.setVisible(false);
        }
           
    }
    
    private Integer intentoLogin() throws Exception{
        PasswordUtils utils = new PasswordUtils();
        
        if(loginEmpty()){
            Usuario user = new Usuario(getLoginUser(),getLoginPass());
            
            if(daoInstance.userExist(user)){
                Usuario userB = daoInstance.getFullUser(user); 
                if(userB.getUsuario_Activado()) {
                    if(utils.hashPassword(user.getUsuario_Password(), userB.getUsuario_Salt()).equals(userB.getUsuario_Password())) {

                    launchMovementView(userB);

                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña Incorrecta", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                        
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario esta pendiente de ser aceptado por un administrador.", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no existe.", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        return 0;
    }
    
    private Integer intentoRegistro() throws Exception{
        
        if(registerEmpty()){
            Usuario user = new Usuario( getLogupUser(),
                                        getLogupName(),
                                        getLogupPass(),
                                        getLogupPhone()
                                        );
            
            //Verify matching password a and b 
            if(!daoInstance.userExist(user)){
                if( getLogupPass().equals(getLogupPassb()) ) {  
                    
                    if(verifyPhoneNumber(uVista.txtRegistrarTelefono.getText())){ 
                        daoInstance.addUser(user);
                        clearRegistry();
                        JOptionPane.showMessageDialog(null, "Tu registro se ha realizado con exito, espera a que un administrador confirme tu peticion.", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                    
                     
                } else {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden, verifique sus contraseñas", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ya existe en la base de datos, intenta con otro", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        }
        return 1;
    }
    
    private boolean verifyPhoneNumber(String phoneString){
        if(phoneString.length() < 10){
            JOptionPane.showMessageDialog(null, "El telefono no cumple el tamaño mínimo.", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            Integer.valueOf(phoneString);
        } catch (NumberFormatException e) { 
            JOptionPane.showMessageDialog(null, "El telefono  es un número no válido.", 
                                                "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void setConextionState(){
        if(this.daoInstance.tryOutConection()){
            this.uVista.conexionState.setText("Conexion Exitosa");
        }else{
            this.uVista.conexionState.setText("Problemas en la base de datos: Consulte soporte Tecnico");
        }
    }
    
    private void launchMovementView(Usuario datos){
        MovimientosVista mVista = new MovimientosVista();
        mVista.setVisible(true);
        mVista.setLocationRelativeTo(null); 
        
        ControladorMovimientos mController = new ControladorMovimientos(mVista);
         
        mController.arrayMembers();
        mController.setUser(datos);
        mController.listar(mVista.MovimientosTabla);
        mController.arrayMembers();
        
        this.uVista.setVisible(false);
        this.uVista.dispose();
    }
    
    //Getters from the view
    private String getLoginUser(){
        return uVista.txt_inicio_usuario.getText();
    }
    
    private String getLoginPass(){
        return uVista.txt_inicio_password.getText();
    }
    
    private String getLogupUser(){
       return uVista.txtRegistrarUsuario.getText();
    }
    
    private String getLogupName(){
        return uVista.txtRegistrarNombreCompleto.getText();
    }
    
    private String getLogupPass(){
        return uVista.txtRegistrarPassword.getText();
    }
    
    private String getLogupPassb(){
        return uVista.txtRegistrarPasswordV.getText();
    }
    
    private String getLogupPhone(){
        return uVista.txtRegistrarTelefono.getText();
    }
    
    //Verifying Methods
    private boolean loginEmpty(){
        
        if(getLoginUser().isEmpty()){
            return false;
        }
        
        if(getLoginPass().isEmpty()){
            return false;
        }
        
        return true;
    }
    
    private boolean registerEmpty(){
        if(getLogupUser().isEmpty()){
            return false;
        }
        
        if(getLogupName().isEmpty()){
            return false;
        }
        
        if(getLogupPass().isEmpty()){
            return false;
        }
        
        if(getLogupPassb().isEmpty()){
            return false;
        }
        
        if(getLogupPhone().isEmpty()){
            return false;
        }
        
        return true;
    }
    
    private void clearRegistry(){  
        uVista.txt_inicio_usuario.setText("");
        uVista.txt_inicio_password.setText("");
        uVista.txtRegistrarUsuario.setText("");
        uVista.txtRegistrarNombreCompleto.setText("");
        uVista.txtRegistrarPassword.setText("");
        uVista.txtRegistrarPasswordV.setText("");
        uVista.txtRegistrarTelefono.setText("");
   
    }
    
    //UnusedMethods
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
