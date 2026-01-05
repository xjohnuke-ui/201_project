package org.shopping.goodsInfo;

import org.shopping.entity.GoodsInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "houtai/goodsInfo/list", value = "/houtai/goodsInfo/list")
public class GoodsInfoList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        GoodsInfo goodsInfo = new GoodsInfo();
        String sql="select * from goodsInfo order by goods_id asc";
        List<Map> goodsInfoList = goodsInfo.getGoodsInfoList(sql);
        request.setAttribute("goodsInfoList", goodsInfoList);
        request.getRequestDispatcher("/jsp/goodsInfo/list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
