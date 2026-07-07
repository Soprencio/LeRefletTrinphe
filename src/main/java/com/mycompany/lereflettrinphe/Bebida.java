package com.mycompany.lereflettrinphe;

public class Bebida extends Comida {

    private String Marca;
    private int Litro;

    public Bebida(String _Nombre, Double _Precio, String _Descripcion, String _Marca, int _Litro) {
        super(_Nombre, _Precio, _Descripcion);
        this.Marca = _Marca;
        this.Litro = _Litro;
    }

    public String getMarca() {
        return Marca;
    }

    public int getLitro() {
        return Litro;
    }

    public void setMarca(String _Tipo) {
        this.Marca = _Tipo;
    }

    public void setLitro(String _Tipo) {

        this.Litro = Integer.parseInt(_Tipo);
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "Marca='" + Marca +
                ", Mililitros=" + Litro +
                ", " + super.toString() +
                '}';
    }
}
