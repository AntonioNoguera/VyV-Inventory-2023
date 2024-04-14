package modelo_dao;

import modelo.Usuario;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.util.ArrayList; 
import java.util.List; 

/**
 *
 * @author Michael Noguera
 */
public class UsuarioDAO {
    conexion conectar = new conexion();
    Connection con;
    
    PreparedStatement ps;
    PreparedStatement qs;
    
    ResultSet rs;
    ResultSet rsB;
    
    public List Listar(){
        List<String> allUsersArray = new ArrayList<>();
         
        return allUsersArray;
    }
    
    public List Agregar(Usuario user){
        List<String> elementosCombo = new ArrayList<>();
         
        return elementosCombo;
    }
    
    public List Eliminar(){
        List<String> elementosCombo = new ArrayList<>();
         
        return elementosCombo;
    }
    
    public Boolean SetPermisos(){
        
        return true;
    }
     
}