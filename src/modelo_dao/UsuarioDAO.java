package modelo_dao;

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
    public String newUser = "INSERT INTO tabla_usuarios(usuario_nombre, usuario_completo, usuario_password, usuario_telefono) VALUES (?,?,?,?)";
    public String setUser = "UPDATE tabla_usuarios SET usuario_activado WHERE usuario_id = ?";
    public String deleteUser = "DELETE FROM tabla_usuarios WHERE usuario_id = ?";

    public String MovementsByUser = "SELECT * FROM tabla_movimientos where usuario_id = ?";

    //Pendientes los metodos de verificacion de los valores del usuario, usando SHA-256
    public String getUserInfo = "SELECT * FROM tabla_usuarios WHERE usuario_nombre = ?";

    public String userExist = "SELECT COUNT(*) FROM tabla_usuarios WHERE usuario_nombre = ?";

    public Boolean tryLogin(Usuario user) {

        return true;
    }

    public List getActiveUsers() {
        List<String> allUsersArray = new ArrayList<>();
        return allUsersArray;
    }

    public List getPendingUsers() {
        List<String> allUsersArray = new ArrayList<>();
        return allUsersArray;
    }

    public List Eliminar() {
        List<String> elementosCombo = new ArrayList<>();
        return elementosCombo;
    }

    public Boolean SetPermisos() {
        return true;
    }

    public Boolean Agregar(Usuario user) {
        return true;
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

    public Boolean passwordMatches(Usuario user) {
        return false;
    }
}
