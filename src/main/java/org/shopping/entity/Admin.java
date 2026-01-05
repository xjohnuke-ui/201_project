package org.shopping.entity;

import org.shopping.database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    private int adminId;
    private String username;
    private String password;
    private String name;

    public Admin() {
    }

    public Admin(int adminId, String username, String password, String name) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Admin login(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        ResultSet rs = DB.executeQuery(sql, username, password);
        try {
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setName(rs.getString("name"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
