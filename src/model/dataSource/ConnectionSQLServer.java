package model.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQLServer {

    public Connection con;
    ///jdbc:sqlserver://localhost:1433;databaseName=dentalClinicP
    public String url = "jdbc:sqlserver://localhost:1433;databaseName=dentalClinicP1;trustServerCertificate=true";
    public String user = "sa";
    public String password = "12345";

    //com.microsoft.sqlserver.jdbc.SQLServerDriver
    public Connection connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa con la base de datos");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Conexión fallida: " + e.toString());
        }
        return con;
    }

    public void disconnect() {
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
