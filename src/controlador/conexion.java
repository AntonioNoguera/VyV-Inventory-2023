package controlador; 

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexion {
    String db ="inventario_db";
    String url ="jdbc:mysql://localhost:3306/";
    String user="root";
    String password="";
    String driver="com.mysql.cj.jdbc.Driver";
    Connection cx;
    
    public conexion(){}
    
    public Connection conectar(){
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url+db,user,password);
            System.out.println("Se conecto!");
        }catch (ClassNotFoundException |SQLException ex){ 
            System.out.println("No se conecto!");
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cx;
    }
    
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        conexion c = new conexion();
        c.conectar();
    }       
}
