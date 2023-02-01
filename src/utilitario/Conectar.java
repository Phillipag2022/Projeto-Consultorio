package utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conectar {
    
  
    
    private static final String USUARIO = "sa";
    private static final String SENHA = "123456";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=CLINICA_MEDICA";
    
    public static Connection getConectar(){
        
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(URL,USUARIO,SENHA);
           // JOptionPane.showMessageDialog(null,"Conexao realizado com sucesso");
          
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao conectar no banco de dados"+ex.getMessage());
        }
        return con;
    }
     
    
}
