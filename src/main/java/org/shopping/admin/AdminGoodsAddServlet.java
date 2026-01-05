package org.shopping.admin;

import org.shopping.database.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "admin/goods/add", value = "/admin/goods/add")
@MultipartConfig
public class AdminGoodsAddServlet extends HttpServlet {
    
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

        request.getRequestDispatcher("/jsp/admin/goods/add.jsp").forward(request, response);
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

        String goodsName = request.getParameter("goods_name");
        String manufacturer = request.getParameter("manufacturer");
        String price = request.getParameter("price");
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        
        Part file = request.getPart("pic");
        String picName = "";
        
        if (file != null && file.getSize() > 0) {
            picName = file.getSubmittedFileName();
            file.write("E:\\goodsPic\\" + picName);
        } else {
            out.println("<script>alert('Product image is required!');history.back();</script>");
            return;
        }

        String sql = "INSERT INTO goodsinfo(goods_name, pic, manufacturer, price, type, description) VALUES(?,?,?,?,?,?)";
        DB.executeUpdate(sql, goodsName, picName, manufacturer, price, type, description);
        
        out.println("<script>alert('Product added successfully!');location.href='/admin/goods/list';</script>");
    }
}
