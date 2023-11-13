package modelo; 
import java.sql.Connection;
import controlador.conexion;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
           String sql = "INSERT INTO tabla_grupos(grupo_nombre,grupo_desc) VALUES (?,?)";
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql);
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
                   System.out.println("ERRORES DE LLAVES");
               }
               
           }catch(SQLException err){
               System.out.println("ERROR:"+err);
               return 0;
           }
        return 0;
    }
    
    public int Actualizar(Grupo g){
        System.out.println("ACTUALIZAR");
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