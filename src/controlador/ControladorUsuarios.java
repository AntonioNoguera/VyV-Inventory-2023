/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import vistas.MovimientosVista;
import vistas.UsuariosVista;

/**
 *
 * @author Michael Noguera
 */
public class ControladorUsuarios implements ActionListener, KeyListener { 
    UsuariosVista uVista = new UsuariosVista();
    
    public ControladorUsuarios(UsuariosVista u){
        System.out.println("Runned COntroler bidn?");
        this.uVista = u;
        
        //Buttons
        this.uVista.btnDenegar.addActionListener(this);
        this.uVista.btnAceptarSolicitud.addActionListener(this);
        this.uVista.btnLimpiarCancelar.addActionListener(this);
        this.uVista.btnEliminarUsuario.addActionListener(this);
        this.uVista.btnEstablecerPermisos.addActionListener(this);
        this.uVista.btnVolver.addActionListener(this);
        
        //Tables
       // this.uVista.btnDenegar.addActionListener(this);
       // this.uVista.btnDenegar.addActionListener(this);
        
        //Combo
        //this.uVista.btnDenegar.addActionListener(this);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Nuevos
        if(e.getSource() == uVista.btnAceptarSolicitud){ 
            System.out.println("CLICKED RIGHT");
        }
        
        if(e.getSource() == uVista.btnDenegar){ 
            System.out.println("CLICKED RIGHT");
        }
        
        //Existentes
        if(e.getSource() == uVista.btnEstablecerPermisos){ 
            System.out.println("CLICKED RIGHT");
        }
        
        if(e.getSource() == uVista.btnEliminarUsuario){ 
            
            System.out.println("CLICKED RIGHT");
        }
        
        if(e.getSource() == uVista.btnLimpiarCancelar){ 
            System.out.println("CLICKED RIGHT");
        }
        
        //Volver
        if(e.getSource() == uVista.btnVolver){ 
            System.out.println("CLICKED RIGHT");
            MovimientosVista mVista = new MovimientosVista(); 

            mVista.setVisible(true);
            mVista.setLocationRelativeTo(null); 
            
            uVista.setVisible(false);
            uVista.dispose();
            
        }
        
           
    }
    
    public void test(){
        System.out.println("LAUNCEHD RIGHT");
    }
    
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
