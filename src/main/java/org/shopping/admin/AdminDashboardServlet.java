package org.shopping.admin;

import org.shopping.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "admin/dashboard", value = "/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    
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

        // Get statistics
        int totalProducts = getCount("SELECT COUNT(*) FROM goodsinfo");
        int totalOrders = getCount("SELECT COUNT(*) FROM orderinfo");
        int totalCustomers = getCount("SELECT COUNT(*) FROM customers");
        int pendingOrders = getCount("SELECT COUNT(*) FROM orderinfo WHERE status = 'Pending'");

        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("pendingOrders", pendingOrders);

        request.getRequestDispatcher("/jsp/admin/dashboard.jsp").forward(request, response);
    }

    private int getCount(String sql) {
        ResultSet rs = DB.executeQuery(sql);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
