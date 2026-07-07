package com.mycompany.lereflettrinphe;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class LeRefletTrinphe {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String cuenta = "0";
        
        String mail = "nada";
        String nombre;
        String apellido;
        String contraseña = "nada";
        String entrada1;
        String entrada2;
        
        Platillo milanesa = new Platillo("Milanesa",7000.0,"Mila de Ternera","Plato Principal");
        Platillo spaghetti = new Platillo("Spaghetti",6300.0,"Spaghetti Bolognesa","Plato Principal");
        Platillo sushi = new Platillo("Sushi",9500.0,"Tabla de 8 piezas","Entrante");
        
        Bebida coca = new Bebida("Coca-Cola",1500.0,"Coca cola regular","Coca-Cola",600);
        Bebida sprite = new Bebida("Sprite",1500.0,"Sprite zero","Coca-Cola",600);
        
        Usuario usuario = null;
        
        while(!"1".equals(cuenta) && !"2".equals(cuenta)){
            
            System.out.println("1. Iniciar Sesión \n2. Registrarse");
            System.out.print("Ingresa: ");
            cuenta = entrada.nextLine();
            int aux = 0;
            
            switch(cuenta){
                case "1":
                    while(aux==0){
                        System.out.println("Ingrese \"salir\" para voler atras");
                        System.out.print("Ingrese su Mail: ");
                        entrada1 = entrada.nextLine();
                        entrada2 = "";
                        if(entrada1.equals("salir")){
                            aux = 1;
                        } else {
                            System.out.print("Ingrese su Contraseña: ");
                            entrada2 = entrada.nextLine();
                            if(entrada2.equals("salir")){
                                aux = 1;
                            }
                        }
                        
                    
                        if(aux == 0){
                            if(mail != "nada" && contraseña != "nada"){
                                if(entrada1.equals(usuario.getMail()) && entrada2.equals(usuario.getContraseña())){
                                    cuenta = "1";
                                    aux = 1;
                                } else {
                                    System.out.println("Credenciales Incorrectas \n");
                                }
                            } else {
                                System.out.println("Credenciales Incorrectas \n");
                            }
                        }
                        else{
                            System.out.println("Volviendo... \n");
                            cuenta = "0";
                        }
                    }
                    
                    break;
                    
                case "2":
                    System.out.print("Ingrese su Mail: ");
                    mail = entrada.nextLine();
                    System.out.print("Ingrese su Nombre: ");
                    nombre = entrada.nextLine();
                    System.out.print("Ingrese su Apellido: ");
                    apellido = entrada.nextLine();
                    System.out.print("Ingrese su Contraseña: ");
                    contraseña = entrada.nextLine();
                    
                    System.out.println(mail + " " + nombre + " " + apellido + " " + contraseña);
                    
                    usuario = new Usuario(nombre,apellido,mail,contraseña);
                    System.out.println("\n");
                    cuenta = "0";
                    break;
                    
                default:
                    System.out.println("Debes ingresar un número dentro de las opciones \n");
            }
        }
        System.out.println("Hola "+usuario.getNombre()+ "\n");

        String titular;
        LocalDateTime fecha;
        int num;
        Bebida bien;
        GiftCard gc;
        String auxiliar;
        String auxiliar2 = "0";
        String desc;
        ArrayList<String> giftm = new ArrayList<>();        
        int gift = 1;
        int existe = 0;
        Reserva reser = null;
        
        cuenta = "0";
        while(!"1".equals(cuenta) && !"2".equals(cuenta) && !"3".equals(cuenta)){
            System.out.print("Ingrese que quiere hacer:\n  1. Ver menú\n  2. Reservar Mesa\n  3. Actualizar GiftCard \nIngrese: ");
            cuenta = entrada.nextLine();
            System.out.println("");
            switch(cuenta){
                case "1":
                    System.out.println(milanesa.toString());
                    System.out.println(spaghetti.toString());
                    System.out.println(sushi.toString());
                    System.out.println("\nPrecione entrer para volver");
                    cuenta = entrada.nextLine();
                    cuenta = "0";
                    break;
                    
                case "2":
                    System.out.print("Titular de la mesa: ");
                    titular = entrada.nextLine();
                    
                    System.out.print("Fecha de reserva (dd/MM/yyyy HH:mm): ");
                    auxiliar = entrada.nextLine();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");             
                    fecha = LocalDateTime.parse(auxiliar, formato);
                    System.out.println(fecha);
                    
                    System.out.print("Numero de Mesa: ");
                    num = Integer.parseInt(entrada.nextLine());
                    
                    System.out.print("GiftCard\nDescripcion de la GiftCard: ");
                    desc = entrada.nextLine();
                    while(gift != 0){
                        System.out.print("Mail invitado "+gift+" (Ingrese 0 para continuar): ");
                        gift += 1;
                        auxiliar = entrada.nextLine();
                        if(!auxiliar.equals("0")){
                            giftm.add(auxiliar);
                        } else {
                            gift = 0;
                        }
                        System.out.println("");
                    }       
                    reser = new Reserva(titular,fecha,num,desc,giftm);
                    reser.UsuarioReserva(usuario);
                    
                    while(auxiliar2 == "0"){
                        System.out.print("Bebidas de Bienvenida (Ingrese cualquier otra cosa para continuar):\n  1. "+coca.toString()+"\n  2. "+sprite.toString()+"\nAgregar: ");
                        auxiliar=entrada.nextLine();
                        switch(auxiliar){
                            case "1":
                                reser.AgregarBebidas(coca);
                                break;
                            case "2":
                                reser.AgregarBebidas(sprite);
                                break;
                            default:
                                auxiliar2 = "1";
                        }
                    }
                    System.out.println(reser.toString());
                    existe = 1;
                    break;
                    
                case "3":
                    if(existe == 1){
                        gift = 1;
                        System.out.print("GiftCard\nDescripcion de la GiftCard: ");
                        desc = entrada.nextLine();
                        while(gift != 0){
                            System.out.print("Mail invitado "+gift+" (Ingrese 0 para continuar): ");
                            gift += 1;
                            auxiliar = entrada.nextLine();
                            if(!auxiliar.equals("0")){
                                giftm.add(auxiliar);
                            } else {
                                gift = 0;
                            }
                            System.out.println("");
                        }
                        gc = new GiftCard(giftm,desc);
                        reser.setInvit(gc);
                    } else {
                        System.out.println("No tienes una reserva todavía");
                    }
                    break;
                default:
                    System.out.println("Debe ingresar un número dentro de las opciones \n");
            }
        }
    }
    
}
