package org.shopping.servlet;

import org.shopping.entity.GoodsInfo;
import org.shopping.service.GoodsService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/goods")
public class GoodsServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("detail".equals(action)) {
            // Show product details
            showGoodsDetail(request, response);
        } else {
            // Show product list (default)
            showGoodsList(request, response);
        }
    }
    
    private void showGoodsList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String type = request.getParameter("type");
        
        List<GoodsInfo> goodsList;
        if (keyword != null && !keyword.trim().isEmpty()) {
            // Search products
            goodsList = goodsService.searchGoodsByName(keyword);
        } else if (type != null && !type.trim().isEmpty()) {
            // Filter by category
            goodsList = goodsService.getGoodsByType(type);
        } else {
            // Get all products
            goodsList = goodsService.getAllGoods();
        }
        
        request.setAttribute("goodsList", goodsList);
        request.getRequestDispatcher("/jsp/goodsInfo/list.jsp").forward(request, response);
    }
    
    private void showGoodsDetail(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int goodsId = Integer.parseInt(request.getParameter("id"));
            GoodsInfo goods = goodsService.getGoodsById(goodsId);
            
            if (goods != null) {
                request.setAttribute("goods", goods);
                request.getRequestDispatcher("/jsp/goodsInfo/detail.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
        }
    }
}
