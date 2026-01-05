package org.shopping.admin;

import org.shopping.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "admin/order/update", value = "/admin/order/update")
public class AdminOrderUpdateServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        if (session.getAttribute("admin_id") == null) {
            out.println("<script>alert('Please login first!');location.href='/admin/login';</script>");
            return;
        }

        String orderId = request.getParameter("id");
        String status = request.getParameter("status");

        if (orderId == null || status == null) {
            out.println("<script>alert('Invalid parameters!');history.back();</script>");
            return;
        }

        String sql = "UPDATE orderinfo SET status = ? WHERE order_id = ?";
        DB.executeUpdate(sql, status, orderId);

        out.println("<script>alert('Order status updated successfully!');location.href='/admin/order/list';</script>");
    }
}
