package org.shopping.admin;

import org.shopping.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "admin/order/list", value = "/admin/order/list")
public class AdminOrderListServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        if (session.getAttribute("admin_id") == null) {
            out.println("<script>alert('Please login first!');location.href='/admin/login';</script>");
            return;
        }

        String searchSql = "";
        List<Object> params = new ArrayList<>();
        
        String searchOrderId = request.getParameter("searchOrderId");
        String searchStatus = request.getParameter("searchStatus");
        
        if (searchOrderId != null && !searchOrderId.trim().isEmpty()) {
            searchSql += " AND order_id = ?";
            params.add(searchOrderId);
            request.setAttribute("searchOrderId", searchOrderId);
        }
        if (searchStatus != null && !searchStatus.trim().isEmpty()) {
            searchSql += " AND status = ?";
            params.add(searchStatus);
            request.setAttribute("searchStatus", searchStatus);
        }

        List<Map> orderList = getOrderList(searchSql, params.toArray());
        request.setAttribute("orderList", orderList);

        request.getRequestDispatcher("/jsp/admin/order/list.jsp").forward(request, response);
    }

    private List<Map> getOrderList(String searchSql, Object... params) {
        List<Map> orderList = new ArrayList<>();
        String sql = "SELECT * FROM v_order_detail WHERE 1=1" + searchSql + " ORDER BY created_at DESC";
        ResultSet rs = DB.executeQuery(sql, params);
        try {
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("order_id", rs.getInt("order_id"));
                map.put("customer_id", rs.getInt("customer_id"));
                map.put("customer_name", rs.getString("customer_name"));
                map.put("customer_phone", rs.getString("customer_phone"));
                map.put("goods_id", rs.getInt("goods_id"));
                map.put("goods_name", rs.getString("goods_name"));
                map.put("pic", rs.getString("pic"));
                map.put("price", rs.getBigDecimal("price"));
                map.put("quantity", rs.getInt("quantity"));
                map.put("total_amount", rs.getBigDecimal("total_amount"));
                map.put("status", rs.getString("status"));
                map.put("created_at", rs.getString("created_at"));
                orderList.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
