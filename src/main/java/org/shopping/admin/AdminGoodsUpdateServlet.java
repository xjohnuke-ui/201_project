package org.shopping.admin;

import org.shopping.database.DB;
import org.shopping.entity.GoodsInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "admin/goods/update", value = "/admin/goods/update")
@MultipartConfig
public class AdminGoodsUpdateServlet extends HttpServlet {
    
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

        int goodsId = Integer.parseInt(request.getParameter("id"));
        GoodsInfo goodsInfo = new GoodsInfo();
        Map goodsSingleInfo = goodsInfo.getSingleGoodsInfo(goodsId);
        request.setAttribute("goodsSingleInfo", goodsSingleInfo);

        request.getRequestDispatcher("/jsp/admin/goods/update.jsp").forward(request, response);
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

        int id = Integer.parseInt(request.getParameter("id"));
        String goodsName = request.getParameter("goods_name");
        String manufacturer = request.getParameter("manufacturer");
        String price = request.getParameter("price");
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        
        Part file = request.getPart("pic");
        
        if (file != null && file.getSize() > 0) {
            String picName = file.getSubmittedFileName();
            file.write("E:\\goodsPic\\" + picName);
            String sql = "UPDATE goodsinfo SET goods_name=?, manufacturer=?, price=?, type=?, description=?, pic=? WHERE goods_id=?";
            DB.executeUpdate(sql, goodsName, manufacturer, price, type, description, picName, id);
        } else {
            String sql = "UPDATE goodsinfo SET goods_name=?, manufacturer=?, price=?, type=?, description=? WHERE goods_id=?";
            DB.executeUpdate(sql, goodsName, manufacturer, price, type, description, id);
        }

        out.println("<script>alert('Product updated successfully!');location.href='/admin/goods/list';</script>");
    }
}
