package modelo_dao;

import Utils.PasswordUtils;
import modelo.Usuario;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Noguera
 */
public class UsuarioDAO {

    conexion conectar = new conexion();
    Connection con;

    PreparedStatement ps;
    PreparedStatement qs;

    ResultSet rs;
    ResultSet rsB;
    
    //Consultas requeridas
    public String getUser = "SELECT * FROM tabla_usuarios WHERE usuario_activado = ?";
    public String newUser = "INSERT INTO tabla_usuarios(usuario_nombre, usuario_completo, usuario_password, usuario_telefono, usuario_salt, usuario_activado) VALUES (?,?,?,?,?,?)";
    public String setUser = "UPDATE tabla_usuarios SET usuario_activado WHERE usuario_id = ?";
    public String deleteUser = "DELETE FROM tabla_usuarios WHERE usuario_id = ?";

    public String MovementsByUser = "SELECT * FROM tabla_movimientos where usuario_id = ?";

    //Pendientes los metodos de verificacion de los valores del usuario, usando SHA-256
    public String getUserInfo = "SELECT * FROM tabla_usuarios WHERE usuario_nombre = ?";

    public String userExist = "SELECT COUNT(*) FROM tabla_usuarios WHERE usuario_nombre = ?";
    
    public String setPermissions = "UPDATE tabla_usuarios SET usuario_permisos = ? , usuario_activado = ? WHERE usuario_id = ?";
    
    PasswordUtils utilsPassword = new PasswordUtils();

    public List getUsers(int userState) throws SQLException {
        List<Usuario> allUsersArray = new ArrayList<>();
        
        con = conectar.conectar();
        ps = con.prepareStatement(getUser);
        ps.setInt(1, userState); 
        
        try{
            rs = ps.executeQuery();
        
            while(rs.next()){
                Usuario user = new Usuario();
                
                user.setUsuario_ID(rs.getInt(1));
                user.setUsuario_Nombre(rs.getString(2));
                user.setUsuario_Completo(rs.getString(3));
                user.setUsuario_Telefono(rs.getString(5));
                user.setUsuario_Permisos(rs.getString(7));
                
                allUsersArray.add(user);
            }
            
        }catch(Exception e){
            System.out.println("No se ejecuto el listado bien");
        }
        
        return allUsersArray;
    }
    
     public boolean tryOutConection(){  
        return this.conectar.testConectionMySQL();
    }

    public void Eliminar(Integer user) {
        //Pending  
        
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(deleteUser); 
            ps.setInt(1, user); 
            
            if(ps.executeUpdate() != 0){
                System.out.println("AFECTED ROWS");
            }else{
                System.out.println("Not any row");
            }
           
           System.out.println(ps.toString()); 
        }catch(SQLException e){
            System.out.println("Error"+e); 
        }  
    }

    public Boolean SetPermisos(Integer user, String permiso) {
        //"UPDATE tabla_usuarios SET usuario_permisos = ? , usuario_activado = ? WHERE usuario_id = ?";
           
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(setPermissions);
            
            ps.setString(1, permiso);
            ps.setInt(2, 1);
            ps.setInt(3, user);
            
            System.out.println(ps.toString());
            
            ps.executeUpdate(); 
            return true;
            
        }catch(SQLException e){
            System.out.println("Error"+e); 
        }
        
        return false;
    }

    public Usuario addUser(Usuario user) throws Exception {
        //   (usuario_nombre, usuario_completo, usuario_password, usuario_telefono, usuario_salt) VALUES (?,?,?,?,?)"; 
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(newUser);
               
               ps.setString(1, user.getUsuario_Nombre());
               ps.setString(2, user.getUsuario_Completo());
               
               //Pending to hash!!
               String generatedSalt = utilsPassword.generateSalt();
               String generatedHash = utilsPassword.hashPassword(user.getUsuario_Password(), generatedSalt);
              
               ps.setString(5, generatedSalt);
               ps.setString(3, generatedHash);
               
               ps.setString(4, user.getUsuario_Telefono());
               ps.setInt(6, 0);
               
               ps.executeUpdate();
               
               user.setUsuario_Password("");
               
               return user;
           }catch(SQLException e){
               System.out.println("Error"+e); 
           }
        
         return new Usuario();
    }

    public Boolean userExist(Usuario user) {
        con = conectar.conectar();

        try {
            ps = con.prepareStatement(userExist);
            ps.setString(1, user.getUsuario_Nombre());

            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getInt(1) >= 1) {
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error aaa" + e);
        }
        return false;
    }

    public Usuario getFullUser(Usuario user) {
        Usuario gotUser = new Usuario();

        con = conectar.conectar();

        try {
            ps = con.prepareStatement(getUserInfo);
            ps.setString(1, user.getUsuario_Nombre());

            rs = ps.executeQuery();
                      
            while(rs.next()){ 
                gotUser.setUsuario_ID(rs.getInt(1)); 
                gotUser.setUsuario_Nombre(rs.getString(2));
                gotUser.setUsuario_Completo(rs.getString(3));
                gotUser.setUsuario_Password(rs.getString(4));
                gotUser.setUsuario_Telefono(rs.getString(5));
                gotUser.setUsuario_Activado(rs.getBoolean(6));
                gotUser.setUsuario_Permisos(rs.getString(7));
                gotUser.setUsuario_Salt(rs.getString(8));
                
                return gotUser;
            }


        } catch (SQLException e) {
            System.out.println("Error aaa" + e);
        }

        return new Usuario();
    }
}
