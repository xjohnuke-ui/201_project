package org.shopping.goodsInfo;

import org.shopping.database.DB;
import org.shopping.entity.GoodsInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "houtai/goodsInfo/update", value = "/houtai/goodsInfo/update")
@MultipartConfig
public class UpdateGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        GoodsInfo goodsInfo=new GoodsInfo();
        int goods_id=Integer.parseInt(request.getParameter("id"));
        Map goodsSingleInfo=goodsInfo.getSingleGoodsInfo(goods_id);
        request.setAttribute("goodsSingleInfo", goodsSingleInfo);
        request.getRequestDispatcher("/jsp/goodsInfo/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int id= Integer.parseInt(request.getParameter("id"));
        String new_name=request.getParameter("goods_name");
        String new_price=request.getParameter("price");
        String new_description=request.getParameter("description");
        String new_manufacturer=request.getParameter("manufacturer");
        Part new_file = request.getPart("pic");
        String new_pic="";
        if (new_file.getSize()>0){
            new_pic=new_file.getSubmittedFileName();
            new_file.write("E:\\goodsPic\\"+new_pic);
        }else {
            out.println("<script>alert('picture is not null！');history.back();</script>");
            return;
        }
        String sql="update goodsinfo set goods_name=?,price=?,pic=?,description=?,manufacturer=? where goods_id=?";
        DB.executeUpdate(sql,new_name,new_price,new_pic,new_description,new_manufacturer,id);
        response.sendRedirect("/houtai/goodsInfo/list");
    }
}
