<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Light Spokesperson
  Date: 2025/12/17
  Time: 20:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Details - Online Shopping Mall</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">
</head>
<body>

<div class="header">
    <div class="header-content">
        <a href="/shopping/list" class="logo">Online Shopping Mall</a>

        <form class="search-box" action="/shopping/list" method="get">
            <input type="text"
                   class="search-input"
                   placeholder="Enter product name"
                   name="searchName">
            <button class="search-btn">Search</button>
        </form>

        <ul class="clearfix user-actions">
            <li>
                <a href="/shopping/cartInfo/list">Cart</a>
            </li>
            <li>
                <a href="/shopping/orderInfo/list">My Orders</a>
            </li>
            <li>
                <a href="javascript:void(0)">Account
                    <ul class="downlist clearfix">
                        <li>
                            <a href="/jsp/update.jsp">Edit Profile</a>
                        </li>
                        <li>
                            <a href="/login">Logout</a>
                        </li>
                    </ul>
                </a>
            </li>
        </ul>
    </div>
</div>

<div class="container">
    <div class="product-gallery">
        <img src="/goodsPic/${goodsSingleInfo.pic}" alt="" class="main-image">
    </div>

    <form method="post" id="myForm">
        <div class="product-info">
            <h1 class="product-title">
                <span class="promotion-tag">Best Seller</span>
                ${goodsSingleInfo.goods_name}
            </h1>

            <div class="price-section">
                <div class="price-label">Price</div>
                <div class="price" name="price">RM${goodsSingleInfo.price}</div>
            </div>

            <div class="product-details">
                <div class="detail-item">
                    <div class="detail-label">Product Name:</div>
                    <div class="detail-content">${goodsSingleInfo.goods_name}</div>
                </div>

                <div class="detail-item">
                    <div class="detail-label">Manufacturer:</div>
                    <div class="detail-content">${goodsSingleInfo.manufacturer}</div>
                </div>

                <div class="detail-item">
                    <div class="detail-label">Product Description:</div>
                    <div class="detail-content">
                        <div class="product-description">
                            ${goodsSingleInfo.description}
                        </div>
                    </div>
                </div>

                <div class="detail-item">
                    <div class="detail-label">Quantity:</div>
                    <div class="detail-content" style="display: flex; align-items: center;">
                        <div class="quantity-controls">
                            <span class="quantity-btn" onclick="updateQuantity(-1)">-</span>
                            <input type="text"
                                   class="quantity-input"
                                   name="quantity"
                                   value="1"
                                   readonly>
                            <span class="quantity-btn" onclick="updateQuantity(1)">+</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="action-buttons">
                <%--                <button class="buy-now">Buy Now</button>--%>
                <%--                <a href="/shopping/orderInfo/add?id=${goodsSingleInfo.goods_id}" class="buy-now">Buy Now</a>--%>
                <button class="buy-now">Buy Now</button>
                <button class="add-to-cart">Add to Cart</button>
                <input type="hidden"
                       name="goods_id"
                       value="${goodsSingleInfo.goods_id}"
                       class="goods_id">
            </div>
        </div>
    </form>
</div>

<script src="/jsp/ss.js"></script>
</body>
</html>
