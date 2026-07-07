package com.mycompany.lereflettrinphe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class LeRefletTrinpheGUI extends JFrame {

    // ----- Navegacion -----
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);

    private static final String LOGIN = "LOGIN";
    private static final String REGISTRO = "REGISTRO";
    private static final String MENU = "MENU";
    private static final String VERMENU = "VERMENU";
    private static final String RESERVA = "RESERVA";
    private static final String GIFTCARD = "GIFTCARD";

    private Usuario usuarioRegistrado = null;
    private Usuario usuarioActual = null;
    private Reserva reservaActual = null;
    private boolean existeReserva = false;

    private final Platillo milanesa = new Platillo("Milanesa", 7000.0, "Mila de Ternera", "Plato Principal");
    private final Platillo spaghetti = new Platillo("Spaghetti", 6300.0, "Spaghetti Bolognesa", "Plato Principal");
    private final Platillo sushi = new Platillo("Sushi", 9500.0, "Tabla de 8 piezas", "Entrante");

    private final Bebida coca = new Bebida("Coca-Cola", 1500.0, "Coca cola regular", "Coca-Cola", 600);
    private final Bebida sprite = new Bebida("Sprite", 1500.0, "Sprite zero", "Coca-Cola", 600);
    private final Bebida fernet = new Bebida("Fernet", 2000.0, "Fernet Branca", "Branca", 500);

    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private DefaultListModel<String> invitadosReservaModel;
    private DefaultListModel<String> invitadosGiftcardModel;

    private JLabel bienvenidaLabel;

    public LeRefletTrinpheGUI() {
        super("Le Reflet Trinphe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        cards.add(crearPanelLogin(), LOGIN);
        cards.add(crearPanelRegistro(), REGISTRO);
        cards.add(crearPanelMenu(), MENU);
        cards.add(crearPanelVerMenu(), VERMENU);
        cards.add(crearPanelReserva(), RESERVA);
        cards.add(crearPanelGiftcard(), GIFTCARD);

        add(cards);
        cardLayout.show(cards, LOGIN);
    }

    // =========================================================
    //  PANEL: LOGIN
    // =========================================================
    private JPanel crearPanelLogin() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = baseConstraints();

        JLabel titulo = new JLabel("Iniciar Sesión");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titulo, gbc);
        gbc.gridwidth = 1;

        JTextField mailField = new JTextField(18);
        JPasswordField passField = new JPasswordField(18);

        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("Mail:"), gbc);
        gbc.gridx = 1; panel.add(mailField, gbc);

        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; panel.add(passField, gbc);

        JButton loginBtn = new JButton("Iniciar Sesión");
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(loginBtn, gbc);

        JButton irRegistroBtn = new JButton("¿No tienes cuenta? Registrarse");
        gbc.gridy = 4;
        panel.add(irRegistroBtn, gbc);

        loginBtn.addActionListener((ActionEvent e) -> {
            String mail = mailField.getText().trim();
            String pass = new String(passField.getPassword());

            if (mail.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa mail y contraseña.",
                        "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (usuarioRegistrado != null
                    && mail.equals(usuarioRegistrado.getMail())
                    && pass.equals(usuarioRegistrado.getContraseña())) {
                usuarioActual = usuarioRegistrado;
                bienvenidaLabel.setText("Hola, " + usuarioActual.getNombre());
                mailField.setText("");
                passField.setText("");
                cardLayout.show(cards, MENU);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        irRegistroBtn.addActionListener(e -> cardLayout.show(cards, REGISTRO));

        return panel;
    }

    // =========================================================
    //  PANEL: REGISTRO
    // =========================================================
    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = baseConstraints();

        JLabel titulo = new JLabel("Registrarse");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titulo, gbc);
        gbc.gridwidth = 1;

        JTextField mailField = new JTextField(18);
        JTextField nombreField = new JTextField(18);
        JTextField apellidoField = new JTextField(18);
        JPasswordField passField = new JPasswordField(18);

        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("Mail:"), gbc);
        gbc.gridx = 1; panel.add(mailField, gbc);

        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; panel.add(nombreField, gbc);

        gbc.gridy = 3; gbc.gridx = 0; panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1; panel.add(apellidoField, gbc);

        gbc.gridy = 4; gbc.gridx = 0; panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; panel.add(passField, gbc);

        JButton registrarBtn = new JButton("Registrarse");
        gbc.gridy = 5; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(registrarBtn, gbc);

        JButton irLoginBtn = new JButton("¿Ya tienes cuenta? Iniciar Sesión");
        gbc.gridy = 6;
        panel.add(irLoginBtn, gbc);

        registrarBtn.addActionListener(e -> {
            String mail = mailField.getText().trim();
            String nombre = nombreField.getText().trim();
            String apellido = apellidoField.getText().trim();
            String pass = new String(passField.getPassword());

            if (mail.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.",
                        "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            usuarioRegistrado = new Usuario(nombre, apellido, mail, pass);
            JOptionPane.showMessageDialog(this, "Cuenta creada con éxito. Ahora inicia sesión.",
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

            mailField.setText("");
            nombreField.setText("");
            apellidoField.setText("");
            passField.setText("");

            cardLayout.show(cards, LOGIN);
        });

        irLoginBtn.addActionListener(e -> cardLayout.show(cards, LOGIN));

        return panel;
    }

    // =========================================================
    //  PANEL: MENU PRINCIPAL
    // =========================================================
    private JPanel crearPanelMenu() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = baseConstraints();

        bienvenidaLabel = new JLabel("Hola");
        bienvenidaLabel.setFont(bienvenidaLabel.getFont().deriveFont(Font.BOLD, 20f));
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(bienvenidaLabel, gbc);

        JButton verMenuBtn = new JButton("Ver Menú");
        JButton reservarBtn = new JButton("Reservar Mesa");
        JButton giftcardBtn = new JButton("Actualizar GiftCard");
        JButton cerrarSesionBtn = new JButton("Cerrar Sesión");

        Dimension botonSize = new Dimension(220, 35);
        for (JButton b : new JButton[]{verMenuBtn, reservarBtn, giftcardBtn, cerrarSesionBtn}) {
            b.setPreferredSize(botonSize);
        }

        gbc.gridy = 1; panel.add(verMenuBtn, gbc);
        gbc.gridy = 2; panel.add(reservarBtn, gbc);
        gbc.gridy = 3; panel.add(giftcardBtn, gbc);
        gbc.gridy = 4; panel.add(cerrarSesionBtn, gbc);

        verMenuBtn.addActionListener(e -> cardLayout.show(cards, VERMENU));

        reservarBtn.addActionListener(e -> {
            limpiarPanelReserva();
            cardLayout.show(cards, RESERVA);
        });

        giftcardBtn.addActionListener(e -> {
            if (!existeReserva) {
                JOptionPane.showMessageDialog(this, "No tienes una reserva todavía.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            limpiarPanelGiftcard();
            cardLayout.show(cards, GIFTCARD);
        });

        cerrarSesionBtn.addActionListener(e -> {
            usuarioActual = null;
            cardLayout.show(cards, LOGIN);
        });

        return panel;
    }

    // =========================================================
    //  PANEL: VER MENU
    // =========================================================
    private JPanel crearPanelVerMenu() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Menú del Restaurante");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        JPanel comidasPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        comidasPanel.add(crearPanelComida(milanesa, "milanesa.jpg"));
        comidasPanel.add(crearPanelComida(spaghetti, "spaghetti.jpg"));
        comidasPanel.add(crearPanelComida(sushi, "sushi.jpg"));

        JLabel bebidasTitulo = new JLabel("Bebidas");
        bebidasTitulo.setFont(bebidasTitulo.getFont().deriveFont(Font.BOLD, 16f));
        bebidasTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bebidasPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        bebidasPanel.add(crearPanelBebida(coca, "coca.jpg"));
        bebidasPanel.add(crearPanelBebida(sprite, "sprite.jpg"));
        bebidasPanel.add(crearPanelBebida(fernet, "fernet.jpg"));

        JPanel centroPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        centroPanel.add(comidasPanel);
        centroPanel.add(bebidasPanel);

        panel.add(centroPanel, BorderLayout.CENTER);

        JButton volverBtn = new JButton("Volver");
        volverBtn.addActionListener(e -> cardLayout.show(cards, MENU));
        panel.add(volverBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelComida(Platillo platillo, String archivoImagen) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        ImageIcon icono = new ImageIcon(getClass().getResource("/images/" + archivoImagen));
        Image img = icono.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(img));
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imagenLabel, BorderLayout.NORTH);

        String detalles = "Nombre: " + platillo.getNombre() + "\n"
                + "Precio: $" + platillo.getPrecio() + "\n"
                + "Descripción: " + platillo.getDescripcion() + "\n"
                + "Tipo: " + platillo.getTipo();

        JTextArea detallesArea = new JTextArea(detalles);
        detallesArea.setEditable(false);
        detallesArea.setOpaque(false);
        detallesArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        detallesArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(detallesArea, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelBebida(Bebida bebida, String archivoImagen) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        ImageIcon icono = new ImageIcon(getClass().getResource("/images/" + archivoImagen));
        Image img = icono.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(img));
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imagenLabel, BorderLayout.NORTH);

        String detalles = "Nombre: " + bebida.getNombre() + "\n"
                + "Precio: $" + bebida.getPrecio() + "\n"
                + "Descripción: " + bebida.getDescripcion() + "\n"
                + "Marca: " + bebida.getMarca() + "\n"
                + "Mililitros: " + bebida.getLitro();

        JTextArea detallesArea = new JTextArea(detalles);
        detallesArea.setEditable(false);
        detallesArea.setOpaque(false);
        detallesArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        detallesArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(detallesArea, BorderLayout.CENTER);

        return panel;
    }

    // =========================================================
    //  PANEL: RESERVAR MESA
    // =========================================================
    private JTextField titularField;
    private JTextField fechaField;
    private JTextField mesaField;
    private JTextField descGiftReservaField;
    private JTextField invitadoReservaField;
    private JCheckBox cocaCheck;
    private JCheckBox spriteCheck;
    private JCheckBox fernetCheck;

    private JPanel crearPanelReserva() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = baseConstraints();

        JLabel titulo = new JLabel("Reservar Mesa");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titulo, gbc);
        gbc.gridwidth = 1;

        titularField = new JTextField(18);
        fechaField = new JTextField(18);
        mesaField = new JTextField(18);
        descGiftReservaField = new JTextField(18);

        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("Titular de la mesa:"), gbc);
        gbc.gridx = 1; panel.add(titularField, gbc);

        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("Fecha (dd/MM/yyyy HH:mm):"), gbc);
        gbc.gridx = 1; panel.add(fechaField, gbc);

        gbc.gridy = 3; gbc.gridx = 0; panel.add(new JLabel("Número de mesa:"), gbc);
        gbc.gridx = 1; panel.add(mesaField, gbc);

        gbc.gridy = 4; gbc.gridx = 0; panel.add(new JLabel("Descripción GiftCard:"), gbc);
        gbc.gridx = 1; panel.add(descGiftReservaField, gbc);

        // Invitados
        gbc.gridy = 5; gbc.gridx = 0; panel.add(new JLabel("Mail invitado:"), gbc);
        invitadoReservaField = new JTextField(14);
        gbc.gridx = 1; panel.add(invitadoReservaField, gbc);

        JButton agregarInvitadoBtn = new JButton("Agregar invitado");
        gbc.gridy = 6; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(agregarInvitadoBtn, gbc);
        gbc.gridwidth = 1;

        invitadosReservaModel = new DefaultListModel<>();
        JList<String> invitadosList = new JList<>(invitadosReservaModel);
        JScrollPane invitadosScroll = new JScrollPane(invitadosList);
        invitadosScroll.setPreferredSize(new Dimension(300, 70));
        gbc.gridy = 7; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(invitadosScroll, gbc);

        JButton quitarInvitadoBtn = new JButton("Quitar invitado seleccionado");
        gbc.gridy = 8;
        panel.add(quitarInvitadoBtn, gbc);

        agregarInvitadoBtn.addActionListener(e -> {
            String mail = invitadoReservaField.getText().trim();
            if (mail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa un mail válido.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            invitadosReservaModel.addElement(mail);
            invitadoReservaField.setText("");
        });

        quitarInvitadoBtn.addActionListener(e -> {
            int idx = invitadosList.getSelectedIndex();
            if (idx != -1) {
                invitadosReservaModel.remove(idx);
            }
        });

        // Bebidas
        JLabel bebidasLabel = new JLabel("Bebidas de Bienvenida:");
        gbc.gridy = 9; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(bebidasLabel, gbc);
        gbc.gridwidth = 2;

        cocaCheck = new JCheckBox(bebidaCheckText(coca));
        spriteCheck = new JCheckBox(bebidaCheckText(sprite));
        fernetCheck = new JCheckBox(bebidaCheckText(fernet));

        gbc.gridy = 10; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(crearPanelBebidaCheck(cocaCheck, "coca.jpg"), gbc);
        gbc.gridy = 11;
        panel.add(crearPanelBebidaCheck(spriteCheck, "sprite.jpg"), gbc);
        gbc.gridy = 12;
        panel.add(crearPanelBebidaCheck(fernetCheck, "fernet.jpg"), gbc);

        gbc.fill = GridBagConstraints.NONE;
        JButton confirmarBtn = new JButton("Confirmar Reserva");
        JButton volverBtn = new JButton("Volver");

        gbc.gridy = 13; gbc.gridwidth = 1; gbc.gridx = 0;
        panel.add(confirmarBtn, gbc);
        gbc.gridx = 1;
        panel.add(volverBtn, gbc);

        confirmarBtn.addActionListener(e -> confirmarReserva());
        volverBtn.addActionListener(e -> cardLayout.show(cards, MENU));

        return panel;
    }

    private void confirmarReserva() {
        String titular = titularField.getText().trim();
        String fechaTexto = fechaField.getText().trim();
        String mesaTexto = mesaField.getText().trim();
        String desc = descGiftReservaField.getText().trim();

        if (titular.isEmpty() || fechaTexto.isEmpty() || mesaTexto.isEmpty() || desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDateTime fecha;
        try {
            fecha = LocalDateTime.parse(fechaTexto, formatoFecha);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Usa dd/MM/yyyy HH:mm.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numMesa;
        try {
            numMesa = Integer.parseInt(mesaTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El número de mesa debe ser un número entero.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<String> invitados = new ArrayList<>();
        for (int i = 0; i < invitadosReservaModel.size(); i++) {
            invitados.add(invitadosReservaModel.get(i));
        }

        reservaActual = new Reserva(titular, fecha, numMesa, desc, invitados);
        reservaActual.UsuarioReserva(usuarioActual);

        if (cocaCheck.isSelected()) {
            reservaActual.AgregarBebidas(coca);
        }
        if (spriteCheck.isSelected()) {
            reservaActual.AgregarBebidas(sprite);
        }
        if (fernetCheck.isSelected()) {
            reservaActual.AgregarBebidas(fernet);
        }

        existeReserva = true;

        mostrarResumenReserva(titular, fecha, numMesa, desc, invitados,
                cocaCheck.isSelected(), spriteCheck.isSelected(), fernetCheck.isSelected());

        cardLayout.show(cards, MENU);
    }

    /**
     * Muestra un resumen de la reserva con formato (HTML dentro del JOptionPane),
     * en vez de imprimir el toString() crudo del objeto Reserva.
     */
    private void mostrarResumenReserva(String titular, LocalDateTime fecha, int numMesa,
            String desc, ArrayList<String> invitados, boolean tieneCoca, boolean tieneSprite, boolean tieneFernet) {

        DateTimeFormatter formatoLindo = DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm 'hs'");

        StringBuilder invitadosHtml = new StringBuilder();
        if (invitados.isEmpty()) {
            invitadosHtml.append("<i>Sin invitados</i>");
        } else {
            invitadosHtml.append("<ul style='margin-top:0;margin-bottom:0;'>");
            for (String mail : invitados) {
                invitadosHtml.append("<li>").append(mail).append("</li>");
            }
            invitadosHtml.append("</ul>");
        }

        StringBuilder bebidasHtml = new StringBuilder();
        if (!tieneCoca && !tieneSprite && !tieneFernet) {
            bebidasHtml.append("<i>Sin bebidas de bienvenida</i>");
        } else {
            bebidasHtml.append("<ul style='margin-top:0;margin-bottom:0;'>");
            if (tieneCoca) {
                bebidasHtml.append("<li>").append(bebidaCheckText(coca)).append("</li>");
            }
            if (tieneSprite) {
                bebidasHtml.append("<li>").append(bebidaCheckText(sprite)).append("</li>");
            }
            if (tieneFernet) {
                bebidasHtml.append("<li>").append(bebidaCheckText(fernet)).append("</li>");
            }
            bebidasHtml.append("</ul>");
        }

        String html = "<html><body style='width: 320px; font-family: sans-serif;'>"
                + "<h2 style='margin-bottom:2px;'>✅ Reserva Confirmada</h2>"
                + "<hr>"
                + "<p style='margin:4px 0;'><b>Titular:</b> " + titular + "</p>"
                + "<p style='margin:4px 0;'><b>Fecha:</b> " + fecha.format(formatoLindo) + "</p>"
                + "<p style='margin:4px 0;'><b>Mesa N°:</b> " + numMesa + "</p>"
                + "<p style='margin:4px 0;'><b>GiftCard:</b> " + desc + "</p>"
                + "<p style='margin:8px 0 2px 0;'><b>Invitados:</b></p>"
                + invitadosHtml
                + "<p style='margin:8px 0 2px 0;'><b>Bebidas de bienvenida:</b></p>"
                + bebidasHtml
                + "</body></html>";

        JOptionPane.showMessageDialog(this, html, "Reserva confirmada", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarPanelReserva() {
        titularField.setText("");
        fechaField.setText("");
        mesaField.setText("");
        descGiftReservaField.setText("");
        invitadoReservaField.setText("");
        invitadosReservaModel.clear();
        cocaCheck.setSelected(false);
        spriteCheck.setSelected(false);
        fernetCheck.setSelected(false);
    }

    // =========================================================
    //  PANEL: ACTUALIZAR GIFTCARD
    // =========================================================
    private JTextField descGiftcardField;
    private JTextField invitadoGiftcardField;

    private JPanel crearPanelGiftcard() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = baseConstraints();

        JLabel titulo = new JLabel("Actualizar GiftCard");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titulo, gbc);
        gbc.gridwidth = 1;

        descGiftcardField = new JTextField(18);
        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("Descripción GiftCard:"), gbc);
        gbc.gridx = 1; panel.add(descGiftcardField, gbc);

        gbc.gridy = 2; gbc.gridx = 0; panel.add(new JLabel("Mail invitado:"), gbc);
        invitadoGiftcardField = new JTextField(14);
        gbc.gridx = 1; panel.add(invitadoGiftcardField, gbc);

        JButton agregarInvitadoBtn = new JButton("Agregar invitado");
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(agregarInvitadoBtn, gbc);
        gbc.gridwidth = 1;

        invitadosGiftcardModel = new DefaultListModel<>();
        JList<String> invitadosList = new JList<>(invitadosGiftcardModel);
        JScrollPane invitadosScroll = new JScrollPane(invitadosList);
        invitadosScroll.setPreferredSize(new Dimension(300, 90));
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(invitadosScroll, gbc);

        JButton quitarInvitadoBtn = new JButton("Quitar invitado seleccionado");
        gbc.gridy = 5;
        panel.add(quitarInvitadoBtn, gbc);

        agregarInvitadoBtn.addActionListener(e -> {
            String mail = invitadoGiftcardField.getText().trim();
            if (mail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa un mail válido.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            invitadosGiftcardModel.addElement(mail);
            invitadoGiftcardField.setText("");
        });

        quitarInvitadoBtn.addActionListener(e -> {
            int idx = invitadosList.getSelectedIndex();
            if (idx != -1) {
                invitadosGiftcardModel.remove(idx);
            }
        });

        JButton actualizarBtn = new JButton("Actualizar GiftCard");
        JButton volverBtn = new JButton("Volver");

        gbc.gridy = 6; gbc.gridx = 0;
        panel.add(actualizarBtn, gbc);
        gbc.gridx = 1;
        panel.add(volverBtn, gbc);

        actualizarBtn.addActionListener(e -> actualizarGiftcard());
        volverBtn.addActionListener(e -> cardLayout.show(cards, MENU));

        return panel;
    }

    private void actualizarGiftcard() {
        if (!existeReserva || reservaActual == null) {
            JOptionPane.showMessageDialog(this, "No tienes una reserva todavía.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            cardLayout.show(cards, MENU);
            return;
        }

        String desc = descGiftcardField.getText().trim();
        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa una descripción para la GiftCard.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<String> invitados = new ArrayList<>();
        for (int i = 0; i < invitadosGiftcardModel.size(); i++) {
            invitados.add(invitadosGiftcardModel.get(i));
        }

        GiftCard gc = new GiftCard(invitados, desc);
        reservaActual.setInvit(gc);

        JOptionPane.showMessageDialog(this, "GiftCard actualizada con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        cardLayout.show(cards, MENU);
    }

    private void limpiarPanelGiftcard() {
        descGiftcardField.setText("");
        invitadoGiftcardField.setText("");
        invitadosGiftcardModel.clear();
    }

    private JPanel crearPanelBebidaCheck(JCheckBox checkBox, String archivoImagen) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setOpaque(false);

        ImageIcon icono = new ImageIcon(getClass().getResource("/images/" + archivoImagen));
        Image img = icono.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(img));
        panel.add(imagenLabel);
        panel.add(checkBox);

        return panel;
    }

    private String bebidaCheckText(Bebida bebida) {
        return bebida.getNombre() + " - $" + bebida.getPrecio()
                + "  |  " + bebida.getDescripcion()
                + "  |  " + bebida.getMarca() + " " + bebida.getLitro() + "ml";
    }

    // =========================================================
    //  UTILIDADES
    // =========================================================
    private GridBagConstraints baseConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            LeRefletTrinpheGUI frame = new LeRefletTrinpheGUI();
            frame.setVisible(true);
        });
    }
}