package dao;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Base64;

public class BaseDao {

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase?characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";

    private static final String USER = "eHVqaW5nbGluZw==";

    private static final String PASSWORD = "MTIzNDU2";

    private static BaseDao instance;
    private Connection connection;
    public static BaseDao getInstance(){
        if (instance == null){
            instance = new BaseDao();
        }
        return instance;
    }

    public BaseDao() {
        try {
            Class.forName(DRIVER_CLASS_NAME);
            byte[] decode = Base64.getDecoder().decode(PASSWORD);
            String DB_PASSWORD = new String(Base64.getDecoder().decode(PASSWORD), StandardCharsets.UTF_8);
            String DB_USER = new String(Base64.getDecoder().decode(USER), StandardCharsets.UTF_8);
            connection = DriverManager.getConnection(URL,DB_USER,DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static void main(String[] args) {
        System.out.println("SQL连接测试");
        Connection connection = getInstance().connection;
        System.out.println(connection.toString());
    }
}
