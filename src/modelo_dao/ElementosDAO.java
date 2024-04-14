package modelo_dao;

import modelo.Elemento;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
 
public class ElementosDAO {
    conexion conectar = new conexion();
    Connection con;
    
    PreparedStatement ps;
    PreparedStatement qs;
    ResultSet rs;
    ResultSet rsB;
    
    public List listar(){
       String sql = "select * from tabla_elementos";
       String sqlSelect = "select grupo_nombre from tabla_grupos where grupo_id = ";
       List<Elemento> datos = new ArrayList<>();
       
       try{
           con=conectar.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            
            while(rs.next()){ 
                
                Elemento e = new Elemento();
                e.setElemento_ID(rs.getInt(1));  
                e.setElemento_Nombre(rs.getString(2));  
                e.setElemento_Desc(rs.getString(3));  
                e.setElemento_Cant(rs.getFloat(4));  
                e.setElemento_Unidad(rs.getString(5));  
                
                try{
                    qs=con.prepareStatement(sqlSelect + rs.getInt(6));
                    rsB=qs.executeQuery();
                    while(rsB.next()){
                        e.setGrupo_Name(rsB.getString(1));  
                    }
                }catch(SQLException err){
                    System.out.println("ERROR SQL: "+err);
                } 
                
                datos.add(e);
            } 
        }catch(SQLException e){
            System.out.println("No se ejecuto el listado bien");
        }
        return datos;
    }
    
    public int Agregar(Elemento e){
           String sql = "INSERT INTO tabla_elementos(elemento_nombre, elemento_desc, elemento_cant, elemento_unidad, grupo_id) VALUES (?,?,?,?,?)";
           String sql2 = "SELECT grupo_id FROM tabla_grupos WHERE grupo_nombre = ?";
           System.out.println(e.getGrupo_Name()); 
           //Comprobación de duplicados
           String sql3= "SELECT COUNT(*) FROM tabla_elementos WHERE elemento_nombre = ?";
           
           Integer nameCount=0;
           
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql3);
               ps.setString(1, e.getElemento_Nombre());
               rsB=ps.executeQuery();
               if(rsB.next()){ 
                    nameCount = rsB.getInt(1);
               }
               System.out.println("NAME COUNT:"+nameCount);
           }catch(SQLException er){
                System.out.println("Error: "+er);
                return 0;
           }
           
           if(nameCount>0){
               //Ya existe un registro con ese nombre
                JOptionPane.showMessageDialog(null, "Ya existe un elemento registrado con ese nombre, prueba con otro, o intenta actualizar anterior registro!.", "Alerta", JOptionPane.WARNING_MESSAGE);
                return 0;
           }
           
           
           //Ejecución
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql);
               ps.setString(1, e.getElemento_Nombre());
               ps.setString(2, e.getElemento_Desc());
               ps.setFloat(3, e.getElemento_Cant());
               ps.setString(4, e.getElemento_Unidad());
               
               try{
                    
                    qs=con.prepareStatement(sql2);
                    qs.setString(1, e.getGrupo_Name());
                    rsB=qs.executeQuery();
                    while(rsB.next()){ 
                        ps.setInt(5, rsB.getInt(1));
                    }
                }catch(SQLException err){
                    System.out.println("ERROR SQL: "+err);
                }
               ps.executeUpdate();
               return 1;
               
           }catch(SQLException er){
               System.out.println("Error: "+er);
               return 0;
               
           }
           
    }
    
    public int Actualizar(Elemento e){
            
        String previousName="";
        String sql3= "SELECT COUNT(*) FROM tabla_elementos WHERE elemento_nombre = ?";
        String sql4 = "SELECT elemento_nombre FROM tabla_elementos WHERE elemento_id = ?";
           
           Integer nameCount=0;
           //Getting Count
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql3);
               ps.setString(1, e.getElemento_Nombre());
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
               ps.setInt(1,e.getElemento_ID());
               rsB=ps.executeQuery();
               if(rsB.next()){ 
                    previousName = rsB.getString(1);
               }
               System.out.println("NAME COUNT:"+nameCount);
           }catch(SQLException er){
                System.out.println("Error: "+er);
                return 0;
           }
           
           if(nameCount>0 && !previousName.equals(e.getElemento_Nombre())){
               //Ya existe un registro con ese nombre
               System.out.println("PREVIUS NAME: "+previousName);
                JOptionPane.showMessageDialog(null, "Ya existe un elemento registrado con ese nombre, prueba con otro, o intenta actualizar anterior registro!.", "Alerta", JOptionPane.WARNING_MESSAGE);
                return 0;
           }
        
        
           String sql = "UPDATE tabla_elementos SET elemento_nombre=?,elemento_desc=?,elemento_cant=?,elemento_unidad=?,grupo_id=? WHERE elemento_id=?";
           String sql2 = "SELECT grupo_id FROM tabla_grupos WHERE grupo_nombre = ?";
           try{
               con = conectar.conectar();
               ps=con.prepareStatement(sql);
               ps.setString(1, e.getElemento_Nombre());
               ps.setString(2, e.getElemento_Desc());
               ps.setFloat(3, e.getElemento_Cant());
               ps.setString(4, e.getElemento_Unidad());
               
               try{
                    qs=con.prepareStatement(sql2);
                    qs.setString(1, e.getGrupo_Name());
                    rsB=qs.executeQuery();
                    while(rsB.next()){ 
                        ps.setInt(5, rsB.getInt(1));
                    }
                }catch(SQLException err){
                    System.out.println("ERROR SQL: "+err);
                }
               
               ps.setInt(6, e.getElemento_ID());
               ps.executeUpdate();
               return 1;
               
           }catch(SQLException er){
               System.out.println("Error: "+er);
               return 0;
               
           }
           
    }
    
    public int Eliminar(Elemento elem){
           String sql = "DELETE FROM tabla_elementos WHERE elemento_id=";
           String sqlV = "SELECT movimiento_id FROM tabla_movimientos WHERE elemento_id=";
           
           try{
               con = conectar.conectar();
               qs=con.prepareStatement(sqlV+elem.getElemento_ID()); 
               rsB=qs.executeQuery(); 
               
               List<String> members = new  ArrayList<>();
               while(rsB.next()){
                   members.add(rsB.getString(1));
               }
               
               if(members.isEmpty()){
                   //NO HAY ERRORES DE LLAVES
                    try{
                         con = conectar.conectar();
                         ps=con.prepareStatement(sql+elem.getElemento_ID()); 
                         ps.executeUpdate();
                         return 1;
                     }catch(SQLException e){
                         System.out.println("Errors"+e);
                         return 0;
                     }
               }else{
                   //Errores de Llaves
                   System.out.println(Arrays.toString(members.toArray()));
                   Object[] opciones = {"Estoy Completamente Seguro", "No, cancelar"};

                int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Se han encontrado los siguientes ID de registros con este elemento, ¿esta seguro de continuar?\n"+members.toString(),
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0] // Botón predeterminado
                );

                 if (seleccion == JOptionPane.YES_OPTION) {
                    deleteALL(elem.getElemento_ID()); 
                    return 1;
                    
                } else if (seleccion == JOptionPane.NO_OPTION || seleccion == JOptionPane.CLOSED_OPTION) {
                    return 0;
                }
               }
               
           }catch(SQLException err){
               System.out.println("ERRORa:"+err);
               return 0;
           }
        return 0;
    }
    
    public void deleteALL(Integer idElemento){
        String sql2 = "DELETE FROM tabla_elementos WHERE elemento_id=?";
        String sql = "DELETE FROM tabla_movimientos WHERE elemento_id=?";
        try{
            con = conectar.conectar();
            
            ps=con.prepareStatement(sql); 
            ps.setInt(1,idElemento);
            ps.executeUpdate(); 
        }catch(SQLException e){
            System.out.println("ERROR MOVES"+e); 
        }
        
        try{
            con = conectar.conectar();
            
            ps=con.prepareStatement(sql2); 
            ps.setInt(1,idElemento);
            ps.executeUpdate(); 
        }catch(SQLException e){
            System.out.println("ERROR ELEMENTO"+e); 
        }
    }
           
    public List elementos(){ 
       String sqlSelect = "select grupo_nombre from tabla_grupos";
        
       List<String> elementosCombo = new ArrayList<>();
       
       try{
           con=conectar.conectar();
            ps=con.prepareStatement(sqlSelect);
            rs=ps.executeQuery();
            
            while(rs.next()){  
                elementosCombo.add(rs.getString(1));
            } 
        }catch(SQLException e){
            System.out.println("No se ejecuto el listado bien: "+e);
        }
       
       return(elementosCombo);
    }
   
}
