package org.shopping.orderInfo;

import org.shopping.database.DB;
import org.shopping.entity.OrderInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 光的代言人
 */
@WebServlet(name = "shopping/orderInfo/add", value = "/shopping/orderInfo/add")
public class AddOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/orderInfo/add.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        OrderInfo orderInfo = new OrderInfo();
        int goodsId = Integer.parseInt(request.getParameter("id"));
        int customerId = (int) session.getAttribute("customer_id");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        System.out.println("goodsId: " + goodsId);
        BigDecimal quantityBD=new BigDecimal(quantity);
        Map map=orderInfo.getOrderInfoMap(goodsId);
        BigDecimal priceBD=(BigDecimal) map.get("price");
        BigDecimal totalPrice = priceBD.multiply(quantityBD);
        String sql="insert into orderinfo(customer_id,goods_id,quantity,total_amount) values(?,?,?,?)";
        DB.executeUpdate(sql,customerId,goodsId,quantity,totalPrice);
        out.println("<script>alert('购买成功！！');location.href='/shopping/list';</script>");
    }
}
