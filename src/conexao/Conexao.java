package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_cadastros";
    private static final String USER = "root";
    private static final String SENHA = "";
    
    public Connection conectaBanco()
    {
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, SENHA);
          
        }catch(ClassNotFoundException e)
        {
            throw new RuntimeException("Driver não encontrado", e);
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Fonte de Dados (DB) não encotrado", e);
        }
           
    }
    
}
