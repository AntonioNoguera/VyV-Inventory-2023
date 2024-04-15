package modelo_dao;

import modelo.Usuario;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.util.ArrayList; 
import java.util.List; 

/** @author Michael Noguera */

public class UsuarioDAO {
    conexion conectar = new conexion();
    Connection con;
    
    PreparedStatement ps;
    PreparedStatement qs;
    
    ResultSet rs;
    ResultSet rsB;
    
    //Consultas requeridas
    public String getUser = "SELECT * FROM tabla_usuarios WHERE usuario_activado = ?";
    public String newUser = "INSERT INTO tabla_usuarios(usuario_nombre, usuario_completo, usuario_password, usuario_telefono) VALUES (?,?,?,?)";
    public String setUser = "UPDATE tabla_usuarios SET usuario_activado WHERE usuario_id = ?";
    public String deleteUser = "DELETE FROM tabla_usuarios WHERE usuario_id = ?";
    
    public String MovementsByUser = "SELECT * FROM tabla_movimientos where usuario_id = ?";
    
    
    //Pendientes los metodos de verificacion de los valores del usuario, usando SHA-256
    public String getUserInfo = "SELECT * FROM tabla_usuarios WHERE usuario_nombre = ?";
    
    public List getActiveUsers(){
        List<String> allUsersArray = new ArrayList<>(); 
        return allUsersArray;
    }
    
    public List getPendingUsers(){
        List<String> allUsersArray = new ArrayList<>(); 
        return allUsersArray;
    }
    
    public Boolean Agregar(Usuario user){
        return true;
    }
    
    public List Eliminar(){
        List<String> elementosCombo = new ArrayList<>();
        return elementosCombo;
    }
    
    public Boolean SetPermisos(){
        return true;
    }
    
    public Boolean userExist(Usuario user){
        return false;
    }
    
    public Usuario getFullUser(Usuario user){
        return new Usuario(); 
    }
    
    public Boolean passwordMatches(Usuario user){
        return false; 
    }
}