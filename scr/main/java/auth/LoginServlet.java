package org.shopping;

import org.shopping.database.DB;
import org.shopping.entity.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        Customer customer=new Customer();
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        if (username==null||"".equals(username)){
            out.println("<script>alert('username is null！');history.back();</script>");
            return;
        }
        if (password==null||"".equals(password)){
            out.println("<script>alert('pwd is null！');history.back();</script>");
            return;
        }
        String sql="select password from customers where username=?";
        ResultSet rs= DB.executeQuery(sql,username);
        try{
            if (rs.next()){
                if (password.equals(rs.getString("password"))){
                    HttpSession session=request.getSession();
                    session.setAttribute("customer_id",customer.getCustomerID(username));
                    response.sendRedirect("/shopping/list");

                }else{
                    out.println("<script>alert('password is incorret！');history.back();</script>");
                    return;
                }
            }else{
                out.println("<script>alert('username is not exist！');history.back();</script>");
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
