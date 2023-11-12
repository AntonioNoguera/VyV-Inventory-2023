/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Grupo {
    private Integer Grupo_ID;
    private String Grupo_Nombre;
    private String Grupo_Desc;

    public Grupo() {
    }
    
    public Grupo(Integer Grupo_ID, String Grupo_Nombre, String Grupo_Desc) {
        this.Grupo_ID = Grupo_ID;
        this.Grupo_Nombre = Grupo_Nombre;
        this.Grupo_Desc = Grupo_Desc;
    }

    public Integer getGrupo_ID() {
        return Grupo_ID;
    }

    public void setGrupo_ID(Integer Grupo_ID) {
        this.Grupo_ID = Grupo_ID;
    }

    public String getGrupo_Nombre() {
        return Grupo_Nombre;
    }

    public void setGrupo_Nombre(String Grupo_Nombre) {
        this.Grupo_Nombre = Grupo_Nombre;
    }

    public String getGrupo_Desc() {
        return Grupo_Desc;
    }

    public void setGrupo_Desc(String Grupo_Desc) {
        this.Grupo_Desc = Grupo_Desc;
    }
    
    
}
