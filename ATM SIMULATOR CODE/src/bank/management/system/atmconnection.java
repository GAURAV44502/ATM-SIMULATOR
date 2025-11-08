package bank.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class atmconnection {
    public static Connection conec;
    public static Connection getdataconnection()
    {
        try {
            if(conec==null)
                conec = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "DAgaurav@44502");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return conec;
    }
}

