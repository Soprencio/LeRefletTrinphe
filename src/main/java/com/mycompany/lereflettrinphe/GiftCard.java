package com.mycompany.lereflettrinphe;
import java.util.ArrayList;

public class GiftCard {
    private ArrayList<String> mails;
    private String Descripcion;

    public GiftCard(ArrayList<String> _mails, String _Descripcion) {
        this.mails = _mails;
        this.Descripcion = _Descripcion;
    }

    public ArrayList<String> getMails() {
        return mails;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setMails(ArrayList<String> _mails) {
        this.mails = _mails;
    }

    public void setDescripcion(String _Descripcion) {
        this.Descripcion = _Descripcion;
    }

    @Override
    public String toString() {
        return "GiftCard{" +
                "mails=" + mails +
                ", Descripcion='" + Descripcion + '\'' +
                '}';
    }
}
