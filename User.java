package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe User que gerencia a autenticação de usuários no sistema.
 */
public class User {

    // Variáveis públicas para armazenar o nome do usuário autenticado e o status da autenticação.
    public String nome = "";
    public boolean result = false;

    /**
     * (1) Realiza a conexão com o banco de dados.
     * @return Connection - Objeto que representa a conexão com o banco.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // (2) Inicializa o driver JDBC do MySQL.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // (3) Configura os dados necessários para a conexão.
            String url = "jdbc:mysql://127.0.0.1:3306/test";
            String user = "lopes";
            String password = "123";

            // (4) Tenta estabelecer a conexão com o banco.
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            // (5) Exibe uma mensagem de erro caso ocorra um problema na conexão.
            System.err.println("Falha ao estabelecer conexão com o banco: " + e.getMessage());
        }
        // (6) Retorna a conexão (ou null em caso de falha).
        return conn;
    }

    /**
     * (7) Confere se o usuário e senha fornecidos são válidos.
     * @param login String - Nome de usuário fornecido.
     * @param senha String - Senha fornecida.
     * @return boolean - true se as credenciais forem válidas e false caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        // (8) Declara o comando SQL com parâmetros para evitar vulnerabilidades.
        String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";

        try (
            // (9) Obtém uma conexão com o banco de dados.
            Connection conn = conectarBD();

            // (10) Prepara a consulta para execução segura.
            PreparedStatement ps = conn.prepareStatement(sql)) {

            // (11) Insere os valores do login e senha nos placeholders da consulta.
            ps.setString(1, login);
            ps.setString(2, senha);

            // (12) Executa a consulta e armazena o resultado.
            try (ResultSet rs = ps.executeQuery()) {
                // (13) Se houver resultados, armazena os dados do usuário autenticado.
                if (rs.next()) {
                    result = true;
                    nome = rs.getString("nome");
                }
            }
        } catch (Exception e) {
            // (14) Exibe uma mensagem de erro caso algo dê errado durante a verificação.
            System.err.println("Erro durante a validação do usuário: " + e.getMessage());
        }
        // (15) Retorna o status da autenticação.
        return result;
    }
}


