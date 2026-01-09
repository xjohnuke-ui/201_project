package org.shopping;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet(name = "Userupdate", value = "/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {

    // Database connection parameters
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/shopping?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    // Get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    // Handle POST request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int customerId = (int) session.getAttribute("customer_id");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Validate input
        if (username == null || password == null || phone == null
                || username.isEmpty() || password.isEmpty() || phone.isEmpty()) {

            out.print("{\"message\":\"Username, password and phone number cannot be empty.\"}");
            out.flush();
            return;
        }

        // Validate phone number format
        if (!phone.matches("^\\d{11}$")) {
            out.print("{\"message\":\"Invalid phone number format. It must be 11 digits.\"}");
            out.flush();
            return;
        }

        try (Connection conn = getConnection()) {

            // Check if username already exists
            String checkUserQuery = "SELECT * FROM customers WHERE username = ?";
            try (PreparedStatement psCheck = conn.prepareStatement(checkUserQuery)) {
                psCheck.setString(1, username);
                ResultSet rs = psCheck.executeQuery();
                if (rs.next()) {
                    out.print("<script>alert('Username already exists.');history.back();</script>");
                    out.flush();
                    return;
                }
            }

            // Update user information
            String updateQuery =
                    "UPDATE customers SET password = ?, phone = ?, username = ? WHERE customer_id = ?";
            try (PreparedStatement psUpdate = conn.prepareStatement(updateQuery)) {

                psUpdate.setString(1, password);
                psUpdate.setString(2, phone);
                psUpdate.setString(3, username);
                psUpdate.setInt(4, customerId);

                int rowsAffected = psUpdate.executeUpdate();

                if (rowsAffected > 0) {
                    out.print("<script>alert('Update successful.');history.back();</script>");
                } else {
                    out.print("<script>alert('Update failed. Please try again later.');history.back();</script>");
                }
                out.flush();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.print("<script>alert('Internal server error.');history.back();</script>");
            out.flush();
        }
    }
}
