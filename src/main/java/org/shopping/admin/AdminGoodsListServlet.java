package org.shopping.admin;

import org.shopping.entity.GoodsInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "admin/goods/list", value = "/admin/goods/list")
public class AdminGoodsListServlet extends HttpServlet {
    
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

        String searchSql = "";
        List<Object> params = new ArrayList<>();
        
        String searchName = request.getParameter("searchName");
        String searchType = request.getParameter("searchType");
        
        if (searchName != null && !searchName.trim().isEmpty()) {
            searchSql += " AND goods_name LIKE ?";
            params.add("%" + searchName + "%");
            request.setAttribute("searchName", searchName);
        }
        if (searchType != null && !searchType.trim().isEmpty()) {
            searchSql += " AND type = ?";
            params.add(searchType);
            request.setAttribute("searchType", searchType);
        }

        String sql = "SELECT * FROM goodsinfo WHERE 1=1" + searchSql + " ORDER BY goods_id ASC";
        
        GoodsInfo goodsInfo = new GoodsInfo();
        List<Map> goodsInfoList = goodsInfo.getGoodsInfoList(sql, params.toArray());
        request.setAttribute("goodsInfoList", goodsInfoList);

        request.getRequestDispatcher("/jsp/admin/goods/list.jsp").forward(request, response);
    }
}
