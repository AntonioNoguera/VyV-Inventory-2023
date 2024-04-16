package modelo;

/**
 *
 * @author Michael Noguera
 */
public class Usuario {
    private Integer Usuario_ID;
    private String Usuario_Nombre;
    private String Usuario_Completo;
    private String Usuario_Password;
    private String Usuario_Telefono;
    private Boolean Usuario_Activado;
    private String Usuario_Permisos;
    private Boolean Usuario_Value;
    private String Usuario_Salt;
    
    public Usuario(){ }
    
    public Usuario(String Usuario_Nombre, String Usuario_Password){
        this.Usuario_Nombre = Usuario_Nombre;
        this.Usuario_Password = Usuario_Password;
    }
    
    public Usuario(String Usuario_Nombre, 
                   String Usuario_Completo,
                   String Usuario_Password,
                   String Usuario_Telefono){
        
        this.Usuario_Nombre = Usuario_Nombre;
        this.Usuario_Completo = Usuario_Completo;
        this.Usuario_Password = Usuario_Password;
        this.Usuario_Telefono = Usuario_Telefono; 
    }
    
    //Getter And Setters
    public Integer getUsuario_ID() {
        return Usuario_ID;
    }

    public void setUsuario_ID(Integer Usuario_ID) {
        this.Usuario_ID = Usuario_ID;
    }

    public String getUsuario_Nombre() {
        return Usuario_Nombre;
    }

    public void setUsuario_Nombre(String Usuario_Nombre) {
        this.Usuario_Nombre = Usuario_Nombre;
    }

    public String getUsuario_Completo() {
        return Usuario_Completo;
    }

    public void setUsuario_Completo(String Usuario_Completo) {
        this.Usuario_Completo = Usuario_Completo;
    }

    public String getUsuario_Password() {
        return Usuario_Password;
    }

    public void setUsuario_Password(String Usuario_Password) {
        this.Usuario_Password = Usuario_Password;
    }

    public String getUsuario_Telefono() {
        return Usuario_Telefono;
    }

    public void setUsuario_Telefono(String Usuario_Telefono) {
        this.Usuario_Telefono = Usuario_Telefono;
    }

    public Boolean getUsuario_Activado() {
        return Usuario_Activado;
    }

    public void setUsuario_Activado(Boolean Usuario_Activado) {
        this.Usuario_Activado = Usuario_Activado;
    }

    public String getUsuario_Permisos() {
        return Usuario_Permisos;
    }

    public void setUsuario_Permisos(String Usuario_Permisos) {
        this.Usuario_Permisos = Usuario_Permisos;
    }

    public Boolean getUsuario_Value() {
        return Usuario_Value;
    }

    public void setUsuario_Value(Boolean Usuario_Value) {
        this.Usuario_Value = Usuario_Value;
    }

    public String getUsuario_Salt() {
        return Usuario_Salt;
    }

    public void setUsuario_Salt(String Usuario_Salt) {
        this.Usuario_Salt = Usuario_Salt;
    }
    
    
}
