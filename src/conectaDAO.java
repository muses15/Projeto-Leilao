
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    public Connection connectDB() {
        Connection conn = null;

        try {
            // URL de conexão
            String url = "jdbc:mysql://localhost:3306/uc11";
            String user = "root";
            String password = "frota";

            // Estabelecendo a conexão
            conn = DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null, "Conexão com o banco de dados bem-sucedida!");

        } catch (SQLException error) {
            // Mostra o erro no JOptionPane
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + error.getMessage());
        }
        return conn;
    }
}

