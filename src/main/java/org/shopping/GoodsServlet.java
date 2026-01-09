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
    private static final int DEFAULT_PAGE_SIZE = 12;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String pageParam = request.getParameter("page");
        String keyword = request.getParameter("keyword");
        String type = request.getParameter("type");
        
        // 分页参数处理
        int page = 1;
        try {
            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            }
        } catch (NumberFormatException e) {
            page = 1;
        }
        
        // 处理详情页
        if ("detail".equals(action)) {
            showGoodsDetail(request, response);
            return;
        }
        
        // 处理列表页（带搜索分页）
        showGoodsList(request, response, keyword, type, page, DEFAULT_PAGE_SIZE);
    }
    
    private void showGoodsList(HttpServletRequest request, HttpServletResponse response,
                              String keyword, String type, int page, int pageSize) 
            throws ServletException, IOException {
        
        List<GoodsInfo> goodsList;
        int totalCount;
        
        // 判断是否有搜索条件
        boolean hasSearchCondition = (keyword != null && !keyword.trim().isEmpty()) || 
                                    (type != null && !type.trim().isEmpty());
        
        if (hasSearchCondition) {
            // 带条件搜索
            goodsList = goodsService.searchGoodsWithPaging(keyword, type, page, pageSize);
            totalCount = goodsService.getSearchResultCount(keyword, type);
            request.setAttribute("searchKeyword", keyword);
            request.setAttribute("searchType", type);
        } else {
            // 普通分页查询
            goodsList = goodsService.getGoodsByPage(page, pageSize);
            totalCount = goodsService.getTotalGoodsCount();
        }
        
        // 计算分页信息
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        if (totalPages == 0) totalPages = 1;
        
        // 获取所有分类（用于下拉菜单）
        List<String> categories = goodsService.getAllCategories();
        
        // 设置请求属性
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("categories", categories);
        request.setAttribute("pageSize", pageSize);
        
        // 转发到JSP
        request.getRequestDispatcher("/jsp/goodsInfo/list.jsp").forward(request, response);
    }
    
    private void showGoodsDetail(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int goodsId = Integer.parseInt(request.getParameter("id"));
            GoodsInfo goods = goodsService.getGoodsById(goodsId);
            
            if (goods != null) {
                // 修复图片路径
                String imageUrl = goodsService.getSafeImageUrl(goods);
                request.setAttribute("imageUrl", imageUrl);
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
