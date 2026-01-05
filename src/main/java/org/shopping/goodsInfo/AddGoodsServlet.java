package org.shopping.goodsInfo;

import org.shopping.database.DB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 光的代言人
 */
@WebServlet(name = "houtai/goodsInfo/add", value = "/houtai/goodsInfo/add")
@MultipartConfig
public class AddGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/goodsInfo/add.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String goods_name = request.getParameter("goods_name");
        String manufacturer = request.getParameter("manufacturer");
        String price = request.getParameter("price");
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        Part file=request.getPart("pic");
        String picName="";
        if (file.getSize()>0){
            picName = file.getSubmittedFileName();
            file.write("E:\\goodsPic\\"+picName);
        }else{
            out.println("<script>alert('picture is cannot be null！');</script>");
            return;
        }
        String sql="insert into goodsInfo(goods_name,pic,manufacturer,price,type,description) values(?,?,?,?,?,?)";
        DB.executeUpdate(sql,goods_name,picName,manufacturer,price,type,description);
        response.sendRedirect("/shopping/list");
    }
}
