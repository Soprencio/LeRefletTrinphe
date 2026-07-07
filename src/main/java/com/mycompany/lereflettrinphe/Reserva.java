package com.mycompany.lereflettrinphe;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;

public class Reserva {

    private String Titular;
    private LocalDateTime Fecha;
    private int Num_mesa;
    private Usuario User;
    private ArrayList<Bebida> Bienvenida;
    private GiftCard Invit;

    public Reserva(String _Titular, LocalDateTime _Fecha, int _Num, String _Descripcion, ArrayList<String> _Mail) {
        this.Titular = _Titular;
        this.Fecha = _Fecha;
        this.Num_mesa = _Num;
        this.Invit = new GiftCard(_Mail, _Descripcion); 
        this.Bienvenida = new ArrayList<>();
    }

    public void UsuarioReserva(Usuario User) {
        this.User = User;
    }

    public void AgregarBebidas(Bebida _Beb) {
        Bienvenida.add(_Beb);
    }

    public String getTitular() {
        return Titular;
    }

    public LocalDateTime getFecha() {
        return Fecha;
    }

    public int getNum_mesa() {
        return Num_mesa;
    }

    public Usuario getUser() {
        return User;
    }

    public ArrayList<Bebida> getBienvenida() {
        return Bienvenida;
    }

    public GiftCard getInvit() {
        return Invit;
    }

    public void setTitular(String _Titular) {
        this.Titular = _Titular;
    }

    public void setFecha(LocalDateTime _Fecha) {
        this.Fecha = _Fecha;
    }

    public void setNum_mesa(int _Num) {
        this.Num_mesa = _Num;
    }

    public void setInvit(GiftCard invit) {
        this.Invit = invit;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "Titular='" + Titular + "\n" +
                ", Fecha=" + Fecha + "\n" +
                ", Num_mesa=" + Num_mesa + "\n" +
                ", User=" + (User != null ? User.toString() : "null") + "\n" +
                ", Bienvenida=" + Bienvenida + "\n" +
                ", Invit=" + (Invit != null ? Invit.toString() : "null") + "\n" +
                '}';
    }
}
