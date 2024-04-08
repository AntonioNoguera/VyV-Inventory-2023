package modelo;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 
import javax.swing.JOptionPane;

public class DashboardDAO {
    conexion conectar = new conexion();
    Connection con;
    
    PreparedStatement ps;
    PreparedStatement qs;
    PreparedStatement ws;
    
    ResultSet rs;
    ResultSet rsB;
    ResultSet rsA;
    
    public List listar(){
       String sql = "select * from tabla_movimientos";
       String sqlSelect = "select elemento_nombre from tabla_elementos where elemento_id = ";
       
       List<Movimientos> datos = new ArrayList<>();
       List<String> elementosCombo = new ArrayList<>();
       
       try{
           con=conectar.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Movimientos e = new Movimientos();
                e.setMovimiento_ID(rs.getInt(1));  
                e.setMovimiento_Tipo(rs.getString(2));  
                
                try{
                    qs=con.prepareStatement(sqlSelect + rs.getInt(3));
                    rsB=qs.executeQuery();
                    while(rsB.next()){
                        e.setElementoNombre(rsB.getString(1)); 
                        elementosCombo.add(rsB.getString(1));
                    }
                }catch(SQLException err){
                    System.out.println("ERROR SQL: "+err);
                }
                e.setMovimiento_Cant(rs.getFloat(4));  
                e.setMovimiento_Tiempo(rs.getString(5));   
                
                datos.add(e);
            } 
        }catch(SQLException e){
            System.out.println("No se ejecuto el listado bien: "+e);
        } 
       elementos();
        return datos;
    }
    
    public List elementos(){ 
       String sqlSelect = "select elemento_nombre from tabla_elementos";
        
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
    
     public int Agregar(Movimientos m){ 
         
           Float actualAmount = null;
           Integer elementoID = null;
           String sql0 = "SELECT elemento_cant FROM tabla_elementos WHERE elemento_nombre = ?";
           String sql = "INSERT INTO tabla_movimientos(movimiento_tipo,elemento_id,movimiento_cant) VALUES (?,?,?)";
           String sql2 = "SELECT elemento_id FROM tabla_elementos WHERE elemento_nombre = ?";
           String sql3 = "UPDATE tabla_elementos SET elemento_cant = ? WHERE elemento_id=? ";
           
           con = conectar.conectar();

           try{
               //Verificación de la disponibilidad del movimiento
               
               System.out.println("INSERT DETECTADO");
               try{
                   ws=con.prepareStatement(sql0);
                   ws.setString(1,m.getElementoNombre());
                   rsA = ws.executeQuery();
                   if(rsA.next()){
                       actualAmount=rsA.getFloat(1);
                       System.out.println(rsA.getFloat(1));
                       //Se desarrolla la lógica!
                       System.out.println(m.getMovimiento_Tipo());
                       if("Salida".equals(m.getMovimiento_Tipo())){
                           
                           if(m.getMovimiento_Cant()>rsA.getFloat(1)){
                               //Movimento Imposible
                               JOptionPane.showMessageDialog(null, "El movimiento es imposible, tenemos menos cantidad en existencia!",
                                "Alerta", JOptionPane.WARNING_MESSAGE);
                               return 0;
                           }
                       }
                        
                   }
               }catch(SQLException e){
                   System.out.println("Error aaa"+e);
               }
               
               //Ejecución del Movimiento
               ps=con.prepareStatement(sql);
               
               ps.setString(1, m.getMovimiento_Tipo());
               
               try{
                   qs=con.prepareStatement(sql2); 
                   qs.setString(1, m.getElementoNombre());
                   rs=qs.executeQuery();
                   while(rs.next()){  
                       elementoID = rs.getInt(1);
                        ps.setInt(2,rs.getInt(1));
                    }
               }catch(SQLException e){
                   System.out.println("Error aaa"+e);
               }
               
               ps.setFloat(3, m.getMovimiento_Cant());
               
               ps.executeUpdate();
               
               //Impacto del Movimiento
               try{
                   qs=con.prepareStatement(sql3); 
                   
                   
                   Float finalAmount=null;
                   
                   if("Salida".equals(m.getMovimiento_Tipo())){
                       finalAmount = actualAmount - m.getMovimiento_Cant();
                   }else{
                        finalAmount = actualAmount + m.getMovimiento_Cant();
                   }
                   
                   qs.setFloat(1, finalAmount);
                   qs.setInt(2,elementoID);
                   qs.executeUpdate(); 
               }catch(SQLException e){
                System.out.println("Error"+e);
                return 0;
               }
               
               return 1;
           }catch(SQLException e){
               System.out.println("Error"+e);
               return 0;
           }
    }
    
    public Integer Eliminar(Movimientos m){ 
        Integer elementoID = null;
        Float actualAmount = null;
        
        String sql1 = "SELECT elemento_cant FROM tabla_elementos WHERE elemento_nombre = ?"; 
        String sql2 = "SELECT elemento_id FROM tabla_elementos WHERE elemento_nombre = ?"; 
        
        //Getting ID
        try{ 
            ws=con.prepareStatement(sql2);
            ws.setString(1,m.getElementoNombre());
            rsA = ws.executeQuery();
            if(rsA.next()){
                elementoID=rsA.getInt(1);
            }
             
        }catch(SQLException e){
            System.out.println("Error"+e);
            return 0;
        }
        
        //Getting Amount
        try{
            con = conectar.conectar(); 
            ws=con.prepareStatement(sql1);
            ws.setString(1,m.getElementoNombre());
            rsA = ws.executeQuery();
            if(rsA.next()){
                actualAmount=rsA.getFloat(1);
            }
             
        }catch(SQLException e){
            System.out.println("Error"+e);
            return 0;
        }
        
        //Verificar que se trata del ultimo miembro
        String sqlA = "SELECT movimiento_id FROM tabla_movimientos WHERE elemento_id=?";
        try{ 
            ws=con.prepareStatement(sqlA);
            ws.setInt(1,elementoID);
            rsA = ws.executeQuery();
            boolean max = true;
            while(rsA.next()){
                System.out.println(m.getMovimiento_ID()+"<"+rsA.getInt(1));
                if(m.getMovimiento_ID()<rsA.getInt(1)){
                    max = false;
                    break;
                }
            }
            
            if(!max){
                JOptionPane.showMessageDialog(null, "Solo es posible eliminar el último registro de cada elemento, prueba actualizar el registro.",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
                
                return 0;
            }
             
        }catch(SQLException e){
            System.out.println("Error"+e);
            return 0;
        }
        
        
        //Impacto!
        String sql3 = "UPDATE tabla_elementos SET elemento_cant = ? WHERE elemento_id=? ";
        Float finalAmount = null;
        
        //Ejecución
        String sql = "DELETE FROM tabla_movimientos WHERE movimiento_id=?";

        try{
            con = conectar.conectar();
            ps=con.prepareStatement(sql);

            ps.setInt(1, m.getMovimiento_ID()); 
            ps.executeUpdate(); 
        }catch(SQLException e){
            System.out.println("Error"+e);
            return 0;
        }
        
        //Impacto
        try{ 
            ps=con.prepareStatement(sql3);
            
            if("Salida".equals(m.getMovimiento_Tipo())){
                //Se suma
                finalAmount = m.getMovimiento_Cant() + actualAmount;
            }else{
                //Se resta
                finalAmount = actualAmount - m.getMovimiento_Cant();
                if(finalAmount<0){
                    JOptionPane.showMessageDialog(null, "Editar el movimiento de esta forma genera incongruencias, verificar los movimientos posteriores a este es necesario.",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
                    return 0;
                }
            }
            ps.setFloat(1, finalAmount);
            ps.setInt(2,elementoID);
            ps.executeUpdate(); 
        }catch(SQLException e){
            System.out.println("Error"+e);
            return 0;
        }
        
        return 1;
    }
    
    public int Actualizar(Movimientos m){
        String movimientoAnterior = "-";
        Float cantidadAnterior = 0.0f;
        
        Float cantidadPosterior = m.getMovimiento_Cant();
        String movimientoPosterior = m.getMovimiento_Tipo();
        
        Float actualAmount = 0.0f;
        
        Float CantidadFinal = 0.0f;
        
        //Extraer los datos anteriores
        String sql1 = "SELECT movimiento_tipo, movimiento_cant FROM tabla_movimientos WHERE movimiento_id = ?";
        try{
            qs=con.prepareStatement(sql1); 
            qs.setInt(1, m.getMovimiento_ID());
            rs=qs.executeQuery();
            if(rs.next()){  
                movimientoAnterior = rs.getString(1); 
                cantidadAnterior = rs.getFloat(2);
            }
        }catch(SQLException e){
            System.out.println("Error en el primero"+e);
        }
               
        //Obtener cantidad del elemento
        String sql2 = "SELECT elemento_cant FROM tabla_elementos WHERE elemento_nombre = ?";
        try{
            qs=con.prepareStatement(sql2); 
            qs.setString(1, m.getElementoNombre());
            rs=qs.executeQuery();
            if(rs.next()){  
                actualAmount = rs.getFloat(1);
            }
        }catch(SQLException e){
            System.out.println("Error en el amount: "+e);
        }
        //Calcular el cambio 
        if(movimientoAnterior.equals(movimientoPosterior)){
            //Iguales
            if(movimientoAnterior.equals("Entrada")){
                //Ambos Entrada
                CantidadFinal = (actualAmount - cantidadAnterior) + cantidadPosterior;
            }else{
                //Ambos Salida
                CantidadFinal = (actualAmount + cantidadAnterior) - cantidadPosterior;
            }
        }else{
            //Distintos
            System.out.println("DISTINTOS");

            if(movimientoAnterior.equals("Entrada") && movimientoPosterior.equals("Salida")){
                //Ambos Entrada 
                System.out.println("ENTADA SALIDA");
                CantidadFinal = (actualAmount - cantidadAnterior) - cantidadPosterior;
            }else if(movimientoAnterior.equals("Salida") && movimientoPosterior.equals("Entrada")){
                //Ambos Salida
                System.out.println("SALIIDA ENTRADA");
                CantidadFinal = (actualAmount + cantidadAnterior) + cantidadPosterior;
            }
        }
        
        if(CantidadFinal<0){
            JOptionPane.showMessageDialog(null, "El movimiento es imposible, esta actualización genera discongruencia "
                    + "con los demas registros, genera un total de: "+ CantidadFinal,
            "Alerta", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        //Actualización del Elemento
        String sql3 = "UPDATE tabla_elementos SET elemento_cant = ? WHERE elemento_nombre = ?";
        try{
            qs=con.prepareStatement(sql3); 
            qs.setFloat(1, CantidadFinal);
            qs.setString(2, m.getElementoNombre());
            qs.executeUpdate(); 
        }catch(SQLException e){
            System.out.println("Error en el actualizacion: "+e);
        }
        
        
        
        //Actualización del Movimiento
        String sql4 = "UPDATE tabla_movimientos SET movimiento_tipo = ?, movimiento_cant = ? WHERE movimiento_id=?"; 
        try{
            qs=con.prepareStatement(sql4); 
            qs.setString(1, m.getMovimiento_Tipo());
            qs.setFloat(2, cantidadPosterior);
            qs.setInt(3, m.getMovimiento_ID());
            qs.executeUpdate(); 
        }catch(SQLException e){
            System.out.println("Error en el actualizacion: "+e);
        }
        return 1;
    }
}