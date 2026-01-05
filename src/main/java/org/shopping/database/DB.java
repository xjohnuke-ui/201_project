package org.shopping.database;

import java.sql.*;

public class DB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/shopping?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "040407";
    static Connection conn;
    static PreparedStatement ps;

    static Connection conn(){
        if (conn==null){
            try {
                System.out.println(">>> USING DB.java from: " + DB.class.getProtectionDomain().getCodeSource().getLocation());
                System.out.println(">>> DB_URL = " + DB_URL);
                System.out.println(">>> DB_USER = " + USER + "  PASS = " + PASS);

                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();   // 打印详细错误
                throw new RuntimeException(e);
            }
        }
        return conn;
    }


    public static void executeUpdate(String sql,Object...args){
        try {
            ps = conn().prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//            DB.close();
        }
    }

    public static ResultSet executeQuery(String sql, Object...args){
        ResultSet rs=null;
        try {
            ps=conn().prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < args.length; i++) {
            try {
                ps.setObject(i+1,args[i]);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            rs=ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public static void close(){
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ps=null;
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            conn=null;
        }
    }
}
