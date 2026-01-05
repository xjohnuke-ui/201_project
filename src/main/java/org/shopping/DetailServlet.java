package org.shopping;

import org.shopping.database.DB;
import org.shopping.entity.GoodsInfo;

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
@WebServlet(name = "shopping/detail", value = "/shopping/detail")
public class DetailServlet extends HttpServlet {
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
        int goods_id= Integer.valueOf(request.getParameter("id"));
        GoodsInfo goodsInfo=new GoodsInfo();
        Map goodsSingleInfo;
        goodsSingleInfo=goodsInfo.getSingleGoodsInfo(goods_id);
        System.out.println("信息："+goodsSingleInfo.get("goods_name"));
        request.setAttribute("goodsSingleInfo", goodsSingleInfo);
        request.getRequestDispatcher("/jsp/goods_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        int customerId=(int)session.getAttribute("customer_id");
        int goods_id= Integer.valueOf(request.getParameter("goods_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        HttpSession session1=request.getSession();
//        session1.setAttribute("quantity", quantity);
        GoodsInfo goodsInfo=new GoodsInfo();
        Map goodsSingleInfo;
        goodsSingleInfo=goodsInfo.getSingleGoodsInfo(goods_id);
        BigDecimal price = (BigDecimal) goodsSingleInfo.get("price");
        BigDecimal quantityBD = new BigDecimal(quantity);
        BigDecimal allPrice = price.multiply(quantityBD);
//        BigDecimal allPrice=goodsSingleInfo.get("price")*quantity;
        String sql="insert into cartinfo(customer_id,goods_id,quantity,allPrice) values(?,?,?,?)";
        DB.executeUpdate(sql,customerId,goods_id,quantity,allPrice);
        out.println("<script>alert('in cart!');history.back();</script>");
    }
}
