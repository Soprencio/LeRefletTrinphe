package com.mycompany.lereflettrinphe;

public class Comida {
    protected String Nombre;
    protected Double Precio;
    protected String Descripcion;

    public Comida(String _Nombre, Double _Precio, String _Descripcion) {
        this.Nombre = _Nombre;
        this.Precio = _Precio;
        this.Descripcion = _Descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public Double getPrecio() {
        return Precio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setNombre(String _Nombre) {
        this.Nombre = _Nombre;
    }

    public void setPrecio(Double _Precio) {
        this.Precio = _Precio;
    }

    public void setDescripcion(String _Descripcion) {
        this.Descripcion = _Descripcion;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "Nombre='" + Nombre +
                ", Precio=" + Precio +
                ", Descripcion='" + Descripcion +
                '}';
    }
}
