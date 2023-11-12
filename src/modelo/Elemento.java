
package modelo; 

public class Elemento {
    private Integer Elemento_ID;
    private String Elemento_Nombre;
    private String Elemento_Desc;
    private Float Elemento_Cant;
    private String Elemento_Unidad;
    private String Grupo_Name;
    private Integer Grupo_ID;

    public Elemento() {
    }

    public Elemento(Integer Elemento_ID, String Elemento_Nombre, String Elemento_Desc, Float Elemento_Cant, String Elemento_Unidad, Integer Grupo_ID) {
        this.Elemento_ID = Elemento_ID;
        this.Elemento_Nombre = Elemento_Nombre;
        this.Elemento_Desc = Elemento_Desc;
        this.Elemento_Cant = Elemento_Cant;
        this.Elemento_Unidad = Elemento_Unidad;
        this.Grupo_ID = Grupo_ID;
    }
    
    public Elemento(String Elemento_Nombre, String Elemento_Desc, Float Elemento_Cant, String Elemento_Unidad, Integer Grupo_ID){
        this.Elemento_Nombre = Elemento_Nombre;
        this.Elemento_Desc = Elemento_Desc;
        this.Elemento_Cant = Elemento_Cant;
        this.Elemento_Unidad = Elemento_Unidad;
        this.Grupo_ID = Grupo_ID;
    }

    public Integer getElemento_ID() {
        return Elemento_ID;
    }

    public void setElemento_ID(Integer Elemento_ID) {
        this.Elemento_ID = Elemento_ID;
    }

    public String getElemento_Nombre() {
        return Elemento_Nombre;
    }

    public void setElemento_Nombre(String Elemento_Nombre) {
        this.Elemento_Nombre = Elemento_Nombre;
    }

    public String getElemento_Desc() {
        return Elemento_Desc;
    }

    public void setElemento_Desc(String Elemento_Desc) {
        this.Elemento_Desc = Elemento_Desc;
    }

    public Float getElemento_Cant() {
        return Elemento_Cant;
    }

    public void setElemento_Cant(Float Elemento_Cant) {
        this.Elemento_Cant = Elemento_Cant;
    }

    public String getElemento_Unidad() {
        return Elemento_Unidad;
    }

    public void setElemento_Unidad(String Elemento_Unidad) {
        this.Elemento_Unidad = Elemento_Unidad;
    }

    public Integer getGrupo_ID() {
        return Grupo_ID;
    }

    public void setGrupo_ID(Integer Grupo_ID) {
        this.Grupo_ID = Grupo_ID;
    }

    public String getGrupo_Name() {
        return Grupo_Name;
    }

    public void setGrupo_Name(String Grupo_Name) {
        this.Grupo_Name = Grupo_Name;
    }
    
    
}
