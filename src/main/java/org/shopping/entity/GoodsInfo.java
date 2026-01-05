package org.shopping.entity;

import org.shopping.database.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsInfo {
    private String goods_id;
    private String goods_name;
    private String pic;
    private String manufacturer;
    private String price;
    private String description;

    public GoodsInfo() {
    }

    public GoodsInfo(String goods_id, String goods_name, String pic, String manufacturer, String price, String description) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.pic = pic;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
    }

    /**
     * 获取
     *
     * @return goods_id
     */
    public String getGoods_id() {
        return goods_id;
    }

    /**
     * 设置
     *
     * @param goods_id
     */
    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    /**
     * 获取
     *
     * @return goods_name
     */
    public String getGoods_name() {
        return goods_name;
    }

    /**
     * 设置
     *
     * @param goods_name
     */
    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    /**
     * 获取
     *
     * @return pic
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置
     *
     * @param pic
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取
     *
     * @return manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 设置
     *
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * 获取
     *
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置
     *
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 获取
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GoodsInfo{goods_id = " + goods_id + ", goods_name = " + goods_name + ", pic = " + pic + ", manufacturer = " + manufacturer + ", price = " + price + ", description = " + description + "}";
    }

    public List<Map> getGoodsInfoList(String sql) {
        return getGoodsInfoList(sql, new Object[0]);
    }

    public List<Map> getGoodsInfoList(String sql, Object... params) {
//        String sql = "select * from goodsinfo order by goods_id asc";
        List<Map> goodsInfoList = new ArrayList<Map>();
        ResultSet rs = DB.executeQuery(sql, params);
        try {
            while (rs.next()) {
                Map map = new HashMap();
                map.put("goods_id", rs.getInt("goods_id"));
                map.put("goods_name", rs.getString("goods_name"));
                map.put("pic", rs.getString("pic"));
                map.put("manufacturer", rs.getString("manufacturer"));
                map.put("price", rs.getBigDecimal("price"));
                map.put("type", rs.getString("type"));
                map.put("description", rs.getString("description"));
                goodsInfoList.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsInfoList;
    }

    public  Map getSingleGoodsInfo(int goods_id) {
        Map map = new HashMap();
        String sql="select * from goodsinfo where goods_id = ?";
        ResultSet rs = DB.executeQuery(sql,goods_id);
        try {
            if (rs.next()) {
                map.put("goods_id",goods_id);
                map.put("goods_name", rs.getString("goods_name"));
                map.put("pic", rs.getString("pic"));
                map.put("manufacturer", rs.getString("manufacturer"));
                map.put("price", rs.getBigDecimal("price"));
                map.put("type", rs.getString("type"));
                map.put("description", rs.getString("description"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        Map goodsSingleInfo=new HashMap<>();
//        goodsSingleInfo=getSingleGoodsInfo(1001L);
        System.out.println(goodsSingleInfo);
    }
}
