package createconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connectionwala {
    public static Connection con;
    public static Connection getdataconnection()
    {
        try {
            if(con==null)
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "DAgaurav@44502");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return con;
    }
}

