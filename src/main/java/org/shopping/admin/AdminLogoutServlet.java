package org.shopping.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "admin/logout", value = "/admin/logout")
public class AdminLogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("admin_id");
            session.removeAttribute("admin_name");
            session.invalidate();
        }
        response.sendRedirect("/admin/login");
    }
}
