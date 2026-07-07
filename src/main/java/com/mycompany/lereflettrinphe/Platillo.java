package com.mycompany.lereflettrinphe;

public class Platillo extends Comida {

    private String Tipo;

    public Platillo(String _Nombre, Double _Precio, String _Descripcion, String _Tipo) {
        super(_Nombre, _Precio, _Descripcion);
        this.Tipo = _Tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String _Tipo) {
        this.Tipo = _Tipo;
    }

    @Override
    public String toString() {
        return "Platillo{" +
                "Tipo='" + Tipo +
                ", " + super.toString() +
                '}';
    }
}
