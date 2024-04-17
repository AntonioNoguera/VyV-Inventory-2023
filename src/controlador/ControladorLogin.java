package controlador;

import Utils.PasswordUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public ControladorLogin(LoginVista v){
        this.uVista = v; 
        
        this.uVista.btnIniciarSesion.addActionListener(this);
        this.uVista.btnNuevoUsuario.addActionListener(this);
        this.uVista.btnRegistrarContinuar.addActionListener(this);
        this.uVista.btnRegistrarVolver.addActionListener(this);
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
                System.out.println(userB.getUsuario_Salt());
                    
                    if(utils.hashPassword(user.getUsuario_Password(), userB.getUsuario_Salt()).equals(userB.getUsuario_Password())){
                        
                        launchMovementView(userB);
                    
                }else{
                    System.out.println("It doesnt matches");
                }
            }else{
                System.out.println("El usuario no existe");
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
                    Usuario readedUser = daoInstance.addUser(user);
                    
                    if(readedUser.equals(new Usuario())){
                        launchMovementView(readedUser);
                    }else{
                        System.out.println("Something went wrong!");
                    }
                } else {
                    System.out.println(" Password Wont Match! ");
                }
                daoInstance.addUser(user);
            }
        }
        return 1;
    }
    
    private void launchMovementView(Usuario datos){
        MovimientosVista mVista = new MovimientosVista(); 

        mVista.setVisible(true);
        mVista.setLocationRelativeTo(null); 
        ControladorMovimientos mController = new ControladorMovimientos(mVista);
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
