package dao;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

public class Basedao {

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase?characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";

    private static final String USER = "eHVqaW5nbGluZw==";

    private static final String PASSWORD = "MTIzNDU2";

    private static Basedao instance;
    private Connection connection;
    public static Basedao getInstance(){
        if (instance == null){
            instance = new Basedao();
        }
        return instance;
    }

    public Basedao() {
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
}
