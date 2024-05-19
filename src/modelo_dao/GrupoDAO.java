package modelo_dao; 

import modelo.Grupo;

import java.sql.Connection;
import controlador.conexion;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GrupoDAO {
    conexion conectar = new conexion();
    Connection con;
    
    PreparedStatement ps;
    PreparedStatement qs;
    ResultSet rs; 
    ResultSet rsB; 
    
    public List listar(){
        String sql="select * from tabla_grupos";
        List<Grupo> datos = new ArrayList<>();
        
        
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Grupo g = new Grupo();
                g.setGrupo_ID(rs.getInt(1));
                g.setGrupo_Nombre(rs.getString(2));
                g.setGrupo_Desc(rs.getString(3));
                datos.add(g);
            }
        }catch(Exception e){
            System.out.println("No se ejecuto el listado bien");
        }
        return datos;
    }
    
    public int Agregar(Grupo g){
        String sql3= "SELECT COUNT(*) FROM tabla_grupos WHERE grupo_nombre = ?";
           
           Integer nameCount = 0;
           
           try {
               con = conectar.conectar();
               ps = con.prepareStatement(sql3);
               ps.setString(1, g.getGrupo_Nombre());
               rsB = ps.executeQuery();
               
               if(rsB.next()) { 
                    nameCount = rsB.getInt(1);
               } 
           } catch(SQLException er){ 
                return 0;
           }
           
           if(nameCount>0){
               //Ya existe un registro con ese nombre
                JOptionPane.showMessageDialog(null, "Ya existe un elemento registrado con ese nombre, prueba con otro, o intenta actualizar anterior registro!.", "Alerta", JOptionPane.WARNING_MESSAGE);
                return 0;
           }
        
           String sql = "INSERT INTO tabla_grupos(grupo_nombre,grupo_desc) VALUES (?,?)";
           try{
               
               ps = con.prepareStatement(sql);
               ps.setString(1, g.getGrupo_Nombre());
               ps.setString(2, g.getGrupo_Desc());
               ps.executeUpdate();
               return 1;
           }catch(SQLException e){
               System.out.println("Error"+e);
               return 0;
           }
           
    }
    
    public int Eliminar(Grupo g){
           String sql = "DELETE FROM tabla_grupos WHERE grupo_id=";
           String sqlV = "SELECT elemento_nombre FROM tabla_elementos WHERE grupo_id=";
           
           try{
               con = conectar.conectar();
               qs=con.prepareStatement(sqlV+g.getGrupo_ID()); 
               rsB=qs.executeQuery(); 
               
               List<String> members = new  ArrayList<>();
               while(rsB.next()){
                   members.add(rsB.getString(1));
               }
               
               if(members.isEmpty()){
                   //NO HAY ERRORES DE LLAVES
                   try{
                        con = conectar.conectar();
                        ps=con.prepareStatement(sql+g.getGrupo_ID()); 
                        ps.executeUpdate();
                        return 1;
                    }catch(SQLException e){
                        System.out.println("Error"+e);
                        return 0;
                    }
               }else{
                   //Errores de Llaves
                    JOptionPane.showMessageDialog(null, "Los siguientes elementos pertenecen a este grupo, \nintenta cambiarlos de grupo antes de eliminar este grupo: \n"+members.toString(), "Alerta", JOptionPane.WARNING_MESSAGE);

               }
               
           }catch(SQLException err){
               System.out.println("ERROR:"+err);
               return 0;
           }
        return 0;
    }
    
    public int Actualizar(Grupo g){
        System.out.println("ACTUALIZAR");
        String previousName="";
        String sql3= "SELECT COUNT(*) FROM tabla_grupos WHERE grupo_nombre = ?";
        String sql4 = "SELECT grupo_nombre FROM tabla_grupos WHERE grupo_id = ?";
           
           Integer nameCount=0;
           //Getting Count
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql3);
               ps.setString(1, g.getGrupo_Nombre());
               rsB=ps.executeQuery();
               if(rsB.next()){ 
                    nameCount = rsB.getInt(1);
               }
               System.out.println("NAME COUNT:"+nameCount);
           }catch(SQLException er){
                System.out.println("Error: "+er);
                return 0;
           }
           //GettingName
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql4);
               ps.setInt(1, g.getGrupo_ID());
               rsB=ps.executeQuery();
               if(rsB.next()){ 
                    previousName = rsB.getString(1);
               }
               System.out.println("NAME COUNT:"+nameCount);
           }catch(SQLException er){
                System.out.println("Error: "+er);
                return 0;
           }
           
           if(nameCount>0 && !previousName.equals(g.getGrupo_Nombre())){
               //Ya existe un registro con ese nombre
               System.out.println("PREVIUS NAME: "+previousName);
                JOptionPane.showMessageDialog(null, "Ya existe un elemento registrado con ese nombre, prueba con otro, o intenta actualizar anterior registro!.", "Alerta", JOptionPane.WARNING_MESSAGE);
                return 0;
           }
        
           String sql = "UPDATE tabla_grupos SET grupo_nombre=?,grupo_desc=? WHERE grupo_id=?";
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql);
               ps.setString(1, g.getGrupo_Nombre());
               ps.setString(2, g.getGrupo_Desc());
               ps.setInt(3, g.getGrupo_ID());
               ps.executeUpdate();
               return 1;
           }catch(SQLException e){
               System.out.println("Error"+e);
               return 0;
           }
           
    }
}