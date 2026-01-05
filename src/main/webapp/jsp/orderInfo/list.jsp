<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Light Spokesperson
  Date: 2025/12/20
  Time: 19:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Orders - Online Shopping Mall</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartInfo/list.css">
</head>
<body>

<div class="header">
    <div class="header-content">
        <a href="/shopping/list" class="logo">Online Shopping Mall</a>
        <div class="search-box">
            <input type="text" placeholder="Enter product name">
            <button class="search-btn">Search</button>
        </div>
    </div>
</div>

<div class="container">
    <div class="cart-header">
        <h2>My Orders</h2>
        <span class="cart-count">All Items (${orderCount})</span>
    </div>

    <div class="cart-content">
        <div class="cart-table-header">
            <span class="col-checkbox">Order ID</span>
            <span class="col-goods">Product Information</span>
            <span class="col-price">Unit Price</span>
            <span class="col-quantity">Quantity</span>
            <span class="col-total">Total Paid</span>
            <span class="col-total">Payment Time</span>
            <span class="col-action">Action</span>
        </div>

        <div class="cart-items">
            <c:forEach items="${orderInfoList}" var="map">
                <div class="cart-item">
                    <div class="item-checkbox">
                            ${map.order_id}
                    </div>

                    <div class="item-info">
                        <img src="/goodsPic/${map.pic}" alt="Product Image">
                        <div class="item-details">
                            <div class="item-title">${map.goods_name}</div>
                        </div>
                    </div>

                    <div class="item-quantity">¥${map.price}</div>

                    <div class="item-quantity">
                        <input type="text"
                               value="${map.quantity}"
                               class="quantity-input"
                               readonly>
                            <%-- <button class="quantity-btn plus">+</button> --%>
                    </div>

                    <div class="item-total">¥${map.total_amount}</div>
                    <div class="item-title">${map.created_at}</div>

                    <div class="item-action">
                        <a href="/shopping/detail?id=${map.goods_id}" class="delete-btn">
                            Buy Again
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%-- <script src="${pageContext.request.contextPath}/jsp/cartInfo/cart.js"></script> --%>
</body>
</html>
