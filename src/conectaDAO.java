
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
public class conectaDAO {

    public Connection getConexao() {

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/leilao_uc11", // linha de conexao
                    "root", // usuario do mysql
                    "Gab20202020."// senha do mysql
            );
            return conn;

        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }

    }

}

