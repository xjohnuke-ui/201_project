package org.shopping;

import org.shopping.entity.GoodsInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "shopping/list", value = "/shopping/list")
public class ShoppingListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String searchSql = "";
        List<Object> params = new ArrayList<>();

//        int customerId = (int) session.getAttribute("customer_id");
//        System.out.println("customerId顾客号:"+customerId);
        String searchName = request.getParameter("searchName");
        String searchType = request.getParameter("searchType");
        if (searchName != null && !searchName.equals("")) {
            searchSql += "and goods_name like ?";
            params.add("%" + searchName + "%");
            request.setAttribute("searchName", searchName);
        }
        if (searchType != null && !searchType.equals("")) {
            searchSql += "and type=?";
            params.add(searchType);
            request.setAttribute("searchType", searchType);
        }
        if (!"".equals(searchSql)) {
            searchSql = " where " + searchSql.substring(3);
        }
        GoodsInfo goodsInfo = new GoodsInfo();
        List<Map> goodsInfoList = new ArrayList<>();
        String sql = "select * from goodsinfo" + searchSql + " order by goods_id asc";
        goodsInfoList = goodsInfo.getGoodsInfoList(sql, params.toArray());
        request.setAttribute("goodsInfoList", goodsInfoList);
        request.getRequestDispatcher("/jsp/shop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
