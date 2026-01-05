package org.shopping.entity;

import org.shopping.database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private int customer_id;
    private String name;
    private String id_card;
    private String username;
    private String password;
    private String phone;

    public Customer() {
    }

    public Customer(int customer_id, String name, String id_card, String username, String password, String phone) {
        this.customer_id = customer_id;
        this.name = name;
        this.id_card = id_card;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    /**
     * 获取
     *
     * @return customer_id
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * 设置
     *
     * @param customer_id
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return id_card
     */
    public String getId_card() {
        return id_card;
    }

    /**
     * 设置
     *
     * @param id_card
     */
    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    /**
     * 获取
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{customer_id = " + customer_id + ", name = " + name + ", id_card = " + id_card + ", username = " + username + ", password = " + password + ", phone = " + phone + "}";
    }

    public int getCustomerID(String username) {
        String sql = "select customer_id from customers where username=?";
        ResultSet rs = DB.executeQuery(sql, username);
        int customer_id = 0;
        try {
            if (rs.next()) {
                customer_id = rs.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer_id;
    }
}
