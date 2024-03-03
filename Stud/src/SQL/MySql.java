package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/practica";
    public static final String user = "root";
    public static final String pass = "Symbol99!";

    public static Connection createConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,user,pass);
            conn.setAutoCommit(true);
        }
        catch (SQLException e) {System.out.println(e);}
        return conn;
    }
}
