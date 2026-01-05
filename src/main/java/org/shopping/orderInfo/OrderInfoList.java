package org.shopping.orderInfo;

import org.shopping.database.DB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 光的代言人
 */
@WebServlet(name = "shopping/orderInfo/list", value = "/shopping/orderInfo/list")
public class OrderInfoList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        if (session.getAttribute("customer_id") == null) {
            out.println("<script>alert('you not login,please login！');location.href='/login';</script>");
            return;
        }
        int customerId = (int) session.getAttribute("customer_id");
        List<Map> orderInfoList = getOrderInfoList(customerId);
        request.setAttribute("orderInfoList", orderInfoList);
        int orderCount=getOrderCount(customerId);
        request.setAttribute("orderCount", orderCount);
        request.getRequestDispatcher("/jsp/orderInfo/list.jsp").forward(request, response);
    }

    private List<Map> getOrderInfoList(int customerId) {
        List<Map> orderInfoList = new ArrayList<Map>();
        String sql="select * from v_order_goods_customer where customer_id=?";
        ResultSet rs= DB.executeQuery(sql,customerId);
        try{
            while(rs.next()){
                Map map = new HashMap();
                map.put("order_id",rs.getInt("order_id"));
//                map.put("customer_id",rs.getInt("customer_id"));
                map.put("goods_id",rs.getInt("goods_id"));
                map.put("goods_name",rs.getString("goods_name"));
                map.put("price",rs.getBigDecimal("price"));
                map.put("pic",rs.getString("pic"));
                map.put("quantity",rs.getInt("quantity"));
                map.put("total_amount",rs.getBigDecimal("total_amount"));
                map.put("created_at",rs.getString("created_at"));
                orderInfoList.add(map);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orderInfoList;
    }

    private int getOrderCount(int customerId) {
        List<Map> orderInfoList = new ArrayList<Map>();
        String sql="select count(*) from v_order_goods_customer where customer_id=?";
        ResultSet rs=DB.executeQuery(sql,customerId);
        int count=0;
        try{
            if(rs.next()){
                count=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
