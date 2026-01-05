package org.shopping.entity;

import org.shopping.database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartInfo {
    private int cart_id;
    private int goods_id;
    private int customer_id;
    private int quantity;
    private double allPrice;

    public CartInfo() {
    }

    public CartInfo(int cart_id, int goods_id, int customer_id, int quantity, double allPrice) {
        this.cart_id = cart_id;
        this.goods_id = goods_id;
        this.customer_id = customer_id;
        this.quantity = quantity;
        this.allPrice = allPrice;
    }

    /**
     * 获取
     * @return cart_id
     */
    public int getCart_id() {
        return cart_id;
    }

    /**
     * 设置
     * @param cart_id
     */
    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
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
     * @return allPrice
     */
    public double getAllPrice() {
        return allPrice;
    }

    /**
     * 设置
     * @param allPrice
     */
    public void setAllPrice(double allPrice) {
        this.allPrice = allPrice;
    }
    @Override
    public String toString() {
        return "cartInfo{cart_id = " + cart_id + ", goods_id = " + goods_id + ", customer_id = " + customer_id + ", quantity = " + quantity + ", allPrice = " + allPrice + "}";
    }

    public static List<Map> getCartInfoList(int customerId) {
        List<Map> cartInfoList = new ArrayList<Map>();
        String sql="select * from goods_cart_view where customer_id=?";
        ResultSet rs= DB.executeQuery(sql,customerId);
        try{
            while(rs.next()){
                Map map = new HashMap();
                map.put("cart_id",rs.getInt("cart_id"));
//                map.put("customer_id",rs.getInt("customer_id"));

                map.put("goods_id",rs.getInt("goods_id"));
                map.put("goods_name",rs.getString("goods_name"));
                map.put("price",rs.getBigDecimal("price"));
                map.put("pic",rs.getString("pic"));
                map.put("quantity",rs.getInt("quantity"));
                map.put("allPrice",rs.getBigDecimal("allPrice"));
                cartInfoList.add(map);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cartInfoList;
    }

    public static void main(String[] args) {
//        List<Map> cartInfoList = getCartInfoList(1);
//        System.out.println(cartInfoList);
    }
}
