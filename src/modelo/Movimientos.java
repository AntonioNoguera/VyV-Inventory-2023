package modelo;

public class Movimientos {
    private Integer Movimiento_ID;
    private String Movimiento_Tipo;
    private String Elemento_Nombre;
    private Float Movimiento_Cant;
    private String Movimiento_Tiempo;
    
    private Integer Elemento_ID;

    public Movimientos() {
    }

    public Movimientos(Integer Movimiento_ID, String Movimiento_Tipo, String Elemento_Nombre,Float Movimiento_Cant, String Movimiento_Tiempo) {
        this.Movimiento_ID = Movimiento_ID;
        this.Movimiento_Tipo = Movimiento_Tipo;
        this.Movimiento_Cant = Movimiento_Cant; 
        this.Elemento_Nombre = Elemento_Nombre;
        this.Movimiento_Tiempo = Movimiento_Tiempo;
    }
    
    public Movimientos(Integer Movimiento_ID, String Movimiento_Tipo, String Elemento_Nombre,Float Movimiento_Cant) {
        this.Movimiento_ID = Movimiento_ID;
        this.Movimiento_Tipo = Movimiento_Tipo;
        this.Movimiento_Cant = Movimiento_Cant; 
        this.Elemento_Nombre = Elemento_Nombre; 
    }
    
    public Movimientos(String Movimiento_Tipo,String Elemento_Nombre,Float Movimiento_Cant) {
        this.Movimiento_Tipo = Movimiento_Tipo;
        this.Elemento_Nombre = Elemento_Nombre; 
        this.Movimiento_Cant = Movimiento_Cant;
    }

    public String getElemento_Nombre() {
        return Elemento_Nombre;
    }

    public void setElemento_Nombre(String Elemento_Nombre) {
        this.Elemento_Nombre = Elemento_Nombre;
    }
    
    
    public Integer getMovimiento_ID() {
        return Movimiento_ID;
    }

    public void setMovimiento_ID(Integer Movimiento_ID) {
        this.Movimiento_ID = Movimiento_ID;
    }

    public String getMovimiento_Tipo() {
        return Movimiento_Tipo;
    }

    public void setMovimiento_Tipo(String Movimiento_Tipo) {
        this.Movimiento_Tipo = Movimiento_Tipo;
    }

    public String getElementoNombre() {
        return Elemento_Nombre;
    }

    public void setElementoNombre(String Elemento_Nombre) {
        this.Elemento_Nombre = Elemento_Nombre;
    }

    public String getMovimiento_Tiempo() {
        return Movimiento_Tiempo;
    }

    public void setMovimiento_Tiempo(String Movimiento_Tiempo) {
        this.Movimiento_Tiempo = Movimiento_Tiempo;
    }

    public Float getMovimiento_Cant() {
        return Movimiento_Cant;
    }

    public void setMovimiento_Cant(Float Movimiento_Cant) {
        this.Movimiento_Cant = Movimiento_Cant;
    }

    public Integer getElemento_ID() {
        return Elemento_ID;
    }

    public void setElemento_ID(Integer Elemento_ID) {
        this.Elemento_ID = Elemento_ID;
    }   
}