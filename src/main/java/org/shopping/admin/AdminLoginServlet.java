package org.shopping.admin;

import org.shopping.entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "admin/login", value = "/admin/login")
public class AdminLoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()) {
            out.println("<script>alert('Username cannot be empty!');history.back();</script>");
            return;
        }
        if (password == null || password.trim().isEmpty()) {
            out.println("<script>alert('Password cannot be empty!');history.back();</script>");
            return;
        }

        Admin admin = Admin.login(username, password);
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin_id", admin.getAdminId());
            session.setAttribute("admin_name", admin.getName());
            response.sendRedirect("/admin/dashboard");
        } else {
            out.println("<script>alert('Invalid username or password!');history.back();</script>");
        }
    }
}
