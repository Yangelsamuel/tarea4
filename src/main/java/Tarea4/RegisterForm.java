package Tarea4;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
public class RegisterForm extends JFrame implements ActionListener {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtUsuario;
    private JTextField txtEmail;
    private JPasswordField passwordField;
    private JPasswordField passwordFieldConfirm;
    private JButton btnGuardar;
    private JTextField txtId;

    private DatabaseConnection conectar = new DatabaseConnection(); // Actualiza el nombre de la clase de conexión
    private Connection connect = conectar.connect(); // Usa el método correcto para conectar

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegisterForm frame = new RegisterForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RegisterForm() {
        setTitle("Registrar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 299, 351);
        getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(29, 55, 64, 22);
        getContentPane().add(lblNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(29, 88, 64, 22);
        getContentPane().add(lblApellido);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(29, 121, 64, 22);
        getContentPane().add(lblUsuario);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(29, 154, 64, 22);
        getContentPane().add(lblEmail);

        JLabel lblClave = new JLabel("Clave:");
        lblClave.setBounds(29, 187, 64, 22);
        getContentPane().add(lblClave);

        JLabel lblConfirmarClave = new JLabel("Confirmar Clave:");
        lblConfirmarClave.setBounds(10, 220, 100, 22);
        getContentPane().add(lblConfirmarClave);

        txtNombre = new JTextField();
        txtNombre.setBounds(103, 56, 133, 20);
        getContentPane().add(txtNombre);
        txtNombre.setColumns(10);

        txtApellido = new JTextField();
        txtApellido.setColumns(10);
        txtApellido.setBounds(103, 89, 133, 20);
        getContentPane().add(txtApellido);

        txtUsuario = new JTextField();
        txtUsuario.setColumns(10);
        txtUsuario.setBounds(103, 122, 133, 20);
        getContentPane().add(txtUsuario);

        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(103, 155, 133, 20);
        getContentPane().add(txtEmail);

        passwordField = new JPasswordField();
        passwordField.setBounds(103, 188, 133, 20);
        getContentPane().add(passwordField);

        passwordFieldConfirm = new JPasswordField();
        passwordFieldConfirm.setBounds(103, 221, 133, 20);
        getContentPane().add(passwordFieldConfirm);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        btnGuardar.setBounds(103, 253, 89, 23);
        getContentPane().add(btnGuardar);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(47, 30, 46, 14);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(103, 25, 133, 20);
        getContentPane().add(txtId);
        txtId.setColumns(10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            handleBtnGuardarActionPerformed(e);
        }
    }

    private void handleBtnGuardarActionPerformed(ActionEvent e) {
        try {
            String sql = "INSERT INTO mostrardatos (idUsuario, Nombre, Apellido, Usuario, Email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            pst.setString(2, txtNombre.getText());
            pst.setString(3, txtApellido.getText());
            pst.setString(4, txtUsuario.getText());
            pst.setString(5, txtEmail.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro agregado con éxito.");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtUsuario.setText("");
        txtEmail.setText("");
        passwordField.setText("");
        passwordFieldConfirm.setText("");
        txtId.requestFocus();
    }
}
