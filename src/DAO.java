import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
    private Connection connection = null;
	
    public DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost/meus_dados", "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {e.printStackTrace();}
    }
    
}
