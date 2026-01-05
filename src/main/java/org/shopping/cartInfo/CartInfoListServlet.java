package org.shopping.cartInfo;

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

@WebServlet(name = "shopping/cartInfo/list", value = "/shopping/cartInfo/list")
public class CartInfoListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        if (session.getAttribute("customer_id") == null) {
            out.println("<script>alert('你还没有登录，请先登录！');location.href='/login';</script>");
            return;
        }
        int customerId = (int) session.getAttribute("customer_id");
//        System.out.println("vbgv"+customerId);
        int cartCount=getCartCount(customerId);
        System.out.println("cccccc:"+cartCount);
        request.setAttribute("cartCount", cartCount);
        List<Map> cartInfoList=getCartInfoList(customerId);
        request.setAttribute("cartInfoList", cartInfoList);
        System.out.println(cartInfoList);
        request.getRequestDispatcher("/jsp/cartInfo/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private List<Map> getCartInfoList(int customerId) {
        List<Map> cartInfoList = new ArrayList<Map>();
        String sql="select * from goods_cart_view where customer_id=?";
        ResultSet rs=DB.executeQuery(sql,customerId);
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

    private int getCartCount(int customerId) {
        List<Map> cartInfoList = new ArrayList<Map>();
        String sql="select count(*) from goods_cart_view where customer_id=?";
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
}
