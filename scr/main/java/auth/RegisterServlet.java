package org.shopping;

import org.shopping.database.DB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String idCard = request.getParameter("id_card");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        if (name.length() > 1) {
            if (idCard.length() == 18) {
                if (username.length() > 4) {
                    if (password.length() > 4) {
                        if (phone.length() == 11) {

                            String sql =
                                    "insert into customers(name,id_card,username,password,phone) values(?,?,?,?,?)";
                            DB.executeUpdate(sql, name, idCard, username, password, phone);

                            out.println("<script>alert('Registration successful. Please log in.');location.href='/login';</script>");

                        } else {
                            out.println("<script>alert('Phone number must be 11 digits.');history.back();</script>");
                            return;
                        }
                    } else {
                        out.println("<script>alert('Password must be at least 4 characters long.');history.back();</script>");
                        return;
                    }
                } else {
                    out.println("<script>alert('Username must be at least 4 characters long.');history.back();</script>");
                    return;
                }
            } else {
                out.println("<script>alert('ID card number must be exactly 18 characters long.');history.back();</script>");
                return;
            }
        } else {
            out.println("<script>alert('Name must be at least 2 characters long.');history.back();</script>");
            return;
        }
    }
}
