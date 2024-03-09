/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public int cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().getConexao();

        int status1;
        String sql = "insert into produtos (nome, valor, status) values(?,?,?)";

        try {
            prep = this.conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            status1 = prep.executeUpdate();
            return status1; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar0" + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    //Método para listar produtos cadastrados
    public ArrayList<ProdutosDTO> listarProdutos() {

        conn = new conectaDAO().getConexao();

        // Limpando aa lista antes de adicionar novos itens
        listagem.clear();

        String sql = "SELECT * FROM produtos";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos: " + ex.getMessage());
        } finally {
            // Fechando a conexão com o sql depois de usar
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return listagem;
    }

    // Método para vender um produto (atualizar o status para "Vendido")
    public void venderProduto(int idProduto) {

        conn = new conectaDAO().getConexao();

        // Consulta SQL para atualizar o status para "Vendido"
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        // Configuração da conexão com o banco de dados
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Substituindo o marcador de posição (?) pelo ID do produto
            stmt.setInt(1, idProduto);

            // Executando a atualização
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Tratamento de exceção em caso de erro de banco de dados
            e.printStackTrace(); // ou use um logger para lidar com o erro de maneira adequada
        } finally {
            // Fechando a conexão no bloco finally para garantir que seja fechada, independentemente de exceções
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // ou use um logger para lidar com o erro de fechamento de conexão
                }
            }
        }
    }
    
    //Lista os produtos vendidos |||(FALTA IMPLEMENTAR A TELA DE VENDAS)|||
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        conn = new conectaDAO().getConexao();

        // Limpando a lista antes de adicionar novos itens
        listagem.clear();

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos: " + ex.getMessage());
        } finally {
            // Fechando a conexão com o SQL depois de usar
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return listagem;
    }
}
