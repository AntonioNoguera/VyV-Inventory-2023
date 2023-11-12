package modelo; 
import java.sql.Connection;
import controlador.conexion;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {
    conexion conectar = new conexion();
    Connection con;
    
    PreparedStatement ps;
    ResultSet rs; 
    
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
}