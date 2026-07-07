package com.mycompany.lereflettrinphe;

public class Usuario {
    private String Nombre;
    private String Apellido;
    private String Mail;
    private String Contraseña;

    public Usuario(String _Nombre, String _Apellido, String _Mail, String _Contraseña) {
        this.Nombre = _Nombre;
        this.Apellido = _Apellido;
        this.Mail = _Mail;
        this.Contraseña = _Contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getMail() {
        return Mail;
    }
    
    public String getContraseña() {
        return Contraseña;
    }

    public void setNombre(String _Nombre) {
        this.Nombre = _Nombre;
    }
    
    public void setApellido(String _Apellido) {
        this.Apellido = _Apellido;
    }

    public void setMail(String _Mail) {
        this.Mail = _Mail;
    }
    
    public void setContraseña(String _Contraseña) {
        this.Contraseña = _Contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Nombre='" + Nombre +
                ", Apellido='" + Apellido +
                ", Mail='" + Mail +
                ", Contraseña= " + Contraseña +
                '}';
    }
}
