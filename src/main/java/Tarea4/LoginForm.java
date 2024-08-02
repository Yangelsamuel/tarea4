package Tarea4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        getContentPane().setLayout(null);


        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 50, 100, 30);
        getContentPane().add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 50, 200, 30);
        getContentPane().add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 100, 100, 30);
        getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        getContentPane().add(passwordField);

        JButton botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setBounds(50, 200, 150, 40);
        botonLogin.setBackground(Color.BLUE);
        botonLogin.setForeground(Color.WHITE);
        getContentPane().add(botonLogin);

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.setBounds(220, 200, 150, 40);
        botonRegistrar.setBackground(Color.GREEN);
        botonRegistrar.setForeground(Color.WHITE);
        getContentPane().add(botonRegistrar);

        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String password = new String(passwordField.getPassword());

                if (usuario.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Por favor, ingrese usuario y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (verificarCredenciales(usuario, password)) {
                    MainForm mainForm = new MainForm(usuario);
                    mainForm.setVisible(true);
                    LoginForm.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private boolean verificarCredenciales(String usuario, String password) {
                boolean esValido = false;
                Connection conexion = null;
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrousuarios", "root", "mi_contraseña");

                    // Crear una consulta SQL
                    String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
                    ps = conexion.prepareStatement(sql);
                    ps.setString(1, usuario);
                    ps.setString(2, password); // Considera encriptar la contraseña en la base de datos

                    rs = ps.executeQuery();
                    if (rs.next()) {
                        esValido = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Cerrar recursos
                    try {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                        if (conexion != null) conexion.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return esValido;
            }
        });

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
                registerForm.setVisible(true);
                LoginForm.this.dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginForm frame = new LoginForm();
            frame.setVisible(true);
        });
    }
}
