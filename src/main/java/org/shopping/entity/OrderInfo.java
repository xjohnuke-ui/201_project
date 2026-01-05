package org.shopping.entity;

import org.shopping.database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderInfo {
    private int orderid;
    private int goods_id;
    private int customer_id;
    private int quantity;
    private double total_amount;

    public OrderInfo() {
    }

    public OrderInfo(int orderid, int goods_id, int customer_id, int quantity, double total_amount) {
        this.orderid = orderid;
        this.goods_id = goods_id;
        this.customer_id = customer_id;
        this.quantity = quantity;
        this.total_amount = total_amount;
    }

    /**
     * 获取
     * @return orderid
     */
    public int getOrderid() {
        return orderid;
    }

    /**
     * 设置
     * @param orderid
     */
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    /**
     * 获取
     * @return goods_id
     */
    public int getGoods_id() {
        return goods_id;
    }

    /**
     * 设置
     * @param goods_id
     */
    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    /**
     * 获取
     * @return customer_id
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * 设置
     * @param customer_id
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * 获取
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 设置
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取
     * @return total_amount
     */
    public double getTotal_amount() {
        return total_amount;
    }

    /**
     * 设置
     * @param total_amount
     */
    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
    @Override
    public String toString() {
        return "OrderInfo{orderid = " + orderid + ", goods_id = " + goods_id + ", customer_id = " + customer_id + ", quantity = " + quantity + ", total_amount = " + total_amount + "}";
    }

    public Map getOrderInfoMap(int goods_id) {
        Map map = new HashMap();
        String sql="select price from goodsinfo where goods_id=?";
        ResultSet rs= DB.executeQuery(sql,goods_id);
        try{
            if (rs.next()){
                map.put("price",rs.getBigDecimal("price"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return map;
    }
}
