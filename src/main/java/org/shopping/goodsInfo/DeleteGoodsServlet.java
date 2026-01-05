package org.shopping.goodsInfo;

import org.shopping.database.DB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "houtai/goodsInfo/delete", value = "/houtai/goodsInfo/delete")
public class DeleteGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String sql="delete from goodsinfo where goods_id=?";
        DB.executeUpdate(sql,id);
        response.sendRedirect("/houtai/goodsInfo/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String[] ids=request.getParameterValues("id");
        if(ids==null || ids.length==0){
            out.println("<script>alert('please choose options！');history.back();</script>");
            return;
        }else{
            String aid=String.join(",", ids);
            String sql=String.format("delete from goodsinfo where goods_id in(%s)", aid);
            DB.executeUpdate(sql);
            response.sendRedirect("/houtai/goodsInfo/list");
        }
    }
}
