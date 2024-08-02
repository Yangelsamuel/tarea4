package Tarea4;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    public MainForm(String usuario) {

        setTitle("Bienvenido, " + usuario);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(null);

        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel lblBienvenido = new JLabel("Bienvenido, " + usuario);
        lblBienvenido.setFont(new Font("Arial", Font.BOLD, 20));
        lblBienvenido.setBounds(20, 20, 400, 30);
        getContentPane().add(lblBienvenido);

        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.setBounds(20, 300, 150, 40);
        botonCerrarSesion.setBackground(Color.RED);
        botonCerrarSesion.setForeground(Color.WHITE);
        getContentPane().add(botonCerrarSesion);

        botonCerrarSesion.addActionListener(e -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
            MainForm.this.dispose();
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm frame = new MainForm("A todos");
            frame.setVisible(true);
        });
    }
}
