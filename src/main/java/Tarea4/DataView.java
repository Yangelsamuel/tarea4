package Tarea4;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class DataView extends JFrame {

    private JPanel contentPane;
    private JTable tableData;

    private DatabaseConnection conectar = new DatabaseConnection(); // Actualiza el nombre de la clase de conexión
    private Connection connect = conectar.connect(); // Usa el método correcto para conectar

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DataView frame = new DataView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public DataView() {
        setFont(new Font("Dialog", Font.BOLD, 12));
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 414, 239);
        contentPane.add(panel);

        tableData = new JTable();
        panel.add(tableData);
        tableData.setVisible(true);

        // Call method to display data
        showData();
    }

    private void showData() {
        DefaultTableModel tblData = new DefaultTableModel();
        tblData.addColumn("idUsuario");
        tblData.addColumn("Nombre");
        tblData.addColumn("Apellido");
        tblData.addColumn("Usuario");
        tblData.addColumn("Email");
        tableData.setModel(tblData);

        String sql = "SELECT * FROM mostrardatos";

        String[] data = new String[5];

        try (Statement stmt = connect.createStatement();
             ResultSet result = stmt.executeQuery(sql)) {

            while (result.next()) {
                data[0] = result.getString("idUsuario");
                data[1] = result.getString("Nombre");
                data[2] = result.getString("Apellido");
                data[3] = result.getString("Usuario");
                data[4] = result.getString("Email");
                tblData.addRow(data);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
