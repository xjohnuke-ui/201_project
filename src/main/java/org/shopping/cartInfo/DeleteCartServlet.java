package org.shopping.cartInfo;

import org.shopping.database.DB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "shopping/cartInfo/delete", value = "/shopping/cartInfo/delete")
public class DeleteCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int cartId = Integer.parseInt(request.getParameter("id"));
        String sql="delete from cartinfo where cart_id=?";
        DB.executeUpdate(sql,cartId);
//        out.println("<script>alert('删除成功！');location;</script>");
        response.sendRedirect("/shopping/cartInfo/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
