/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            // TODO add your handling code here: 
            
            //intentoLogin();
            
            MovimientosVista mVista = new MovimientosVista(); 

            mVista.setVisible(true);
            mVista.setLocationRelativeTo(null); 
            uVista.setVisible(false);
            uVista.dispose();
        }
        
        if(e.getSource() == uVista.btnNuevoUsuario){
            this.uVista.InicioSesionContainer.setVisible(false);
            this.uVista.NuevoUsuarioContainer.setVisible(true);
        }
        
        //Register Related
        if(e.getSource() == uVista.btnRegistrarContinuar){
            //intentoRegistro();
            
            MovimientosVista mVista = new MovimientosVista(); 

            mVista.setVisible(true);
            mVista.setLocationRelativeTo(null); 
            uVista.setVisible(false);
            uVista.dispose();
        }
        
        if(e.getSource() == uVista.btnRegistrarVolver){
            this.uVista.InicioSesionContainer.setVisible(true);
            this.uVista.NuevoUsuarioContainer.setVisible(false);
        }
           
    }
    
    private Integer intentoLogin(){
        
        if(loginEmpty()){
            Usuario user = new Usuario(getLoginUser(),getLoginPass());
            
            if(daoInstance.userExist(user)){
                if(daoInstance.passwordMatches(user)){
                    Usuario dbUser = daoInstance.getFullUser(user);
                    //Full logged
                    MovimientosVista mVista = new MovimientosVista(); 

                    mVista.setVisible(true);
                    mVista.setLocationRelativeTo(null); 
                    uVista.setVisible(false);
                    uVista.dispose();
                }
            }
        
        }
        
        return 0;
    }
    
    private Integer intentoRegistro(){
        
        if(registerEmpty()){
            Usuario user = new Usuario(
                                        getLogupUser(),
                                        getLogupName(),
                                        getLogupPass(),
                                        getLogupPhone()
                                    );
            
            //Verify matching password a and b
            
            if(!daoInstance.userExist(user)){ 
                daoInstance.Agregar(user);
            }
        
        }
         
        return 1;
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
