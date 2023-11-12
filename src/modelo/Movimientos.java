package modelo;
 
public class Movimientos {
    private Integer Movimiento_ID;
    private Boolean Movimiento_Tipo;
    private Integer Elemento_ID;
    private String Movimiento_Tiempo;

    public Movimientos() {
    }

    public Movimientos(Integer Movimiento_ID, Boolean Movimiento_Tipo, Integer Elemento_ID, String Movimiento_Tiempo) {
        this.Movimiento_ID = Movimiento_ID;
        this.Movimiento_Tipo = Movimiento_Tipo;
        this.Elemento_ID = Elemento_ID;
        this.Movimiento_Tiempo = Movimiento_Tiempo;
    }
    
    
    public Integer getMovimiento_ID() {
        return Movimiento_ID;
    }

    public void setMovimiento_ID(Integer Movimiento_ID) {
        this.Movimiento_ID = Movimiento_ID;
    }

    public Boolean getMovimiento_Tipo() {
        return Movimiento_Tipo;
    }

    public void setMovimiento_Tipo(Boolean Movimiento_Tipo) {
        this.Movimiento_Tipo = Movimiento_Tipo;
    }

    public Integer getElemento_ID() {
        return Elemento_ID;
    }

    public void setElemento_ID(Integer Elemento_ID) {
        this.Elemento_ID = Elemento_ID;
    }

    public String getMovimiento_Tiempo() {
        return Movimiento_Tiempo;
    }

    public void setMovimiento_Tiempo(String Movimiento_Tiempo) {
        this.Movimiento_Tiempo = Movimiento_Tiempo;
    }
    
    
}
