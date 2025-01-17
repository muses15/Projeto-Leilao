/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    // Método para cadastrar um produto
    public void cadastrarProduto(ProdutosDTO produto) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        conectaDAO dao = new conectaDAO();
        conn = dao.connectDB(); // Conexão com o banco
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, produto.getNome());
        pstmt.setInt(2, produto.getValor());
        pstmt.setString(3, produto.getStatus());
        
        pstmt.executeUpdate(); // Executa o comando SQL
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao fechar conexão: " + erro.getMessage());
        }
    }
}


    // Método para listar os produtos
    public List<ProdutosDTO> listarProdutos() {
    String sql = "SELECT * FROM produtos";
    List<ProdutosDTO> listaProdutos = new ArrayList<>();

    try (Connection conn = new conectaDAO().connectDB()) {
        if (conn == null) {
            throw new SQLException("Conexão com o banco de dados falhou.");
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listaProdutos.add(produto);
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar produtos: " + e.getMessage());
    }

    return listaProdutos;
}

}
