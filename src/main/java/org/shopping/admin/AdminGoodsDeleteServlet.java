package org.shopping.admin;

import org.shopping.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "admin/goods/delete", value = "/admin/goods/delete")
public class AdminGoodsDeleteServlet extends HttpServlet {
    
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

        String id = request.getParameter("id");
        String sql = "DELETE FROM goodsinfo WHERE goods_id = ?";
        DB.executeUpdate(sql, id);
        
        response.sendRedirect("/admin/goods/list");
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

        String[] ids = request.getParameterValues("id");
        if (ids == null || ids.length == 0) {
            out.println("<script>alert('Please select items to delete!');history.back();</script>");
            return;
        }

        for (String id : ids) {
            String sql = "DELETE FROM goodsinfo WHERE goods_id = ?";
            DB.executeUpdate(sql, id);
        }
        
        response.sendRedirect("/admin/goods/list");
    }
}
