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
    <title>Shopping Cart - Online Shopping Mall</title>
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
        <h2>Shopping Cart</h2>
        <span class="cart-count">All Items (${cartCount})</span>
    </div>

    <div class="cart-content">
        <div class="cart-table-header">
            <span class="col-checkbox">Select</span>
            <span class="col-goods">Product Information</span>
            <span class="col-price">Unit Price</span>
            <span class="col-quantity">Quantity</span>
            <span class="col-total">Subtotal</span>
            <span class="col-action">Action</span>
        </div>

        <div class="cart-items">
            <c:forEach items="${cartInfoList}" var="map">
                <div class="cart-item">
                    <div class="item-checkbox">
                        <input type="checkbox" name="cart_id">
                    </div>

                    <div class="item-info">
                        <img src="/goodsPic/${map.pic}" alt="Product Image">
                        <div class="item-details">
                            <div class="item-title">${map.goods_name}</div>
                        </div>
                    </div>

                    <div class="item-price">¥${map.price}</div>

                    <div class="item-quantity">
                        <button class="quantity-btn minus">-</button>
                        <input type="text" value="${map.quantity}" class="quantity-input">
                        <button class="quantity-btn plus">+</button>
                    </div>

                    <div class="item-total">¥${map.allPrice}</div>

                    <div class="item-action">
                        <a href="/shopping/cartInfo/delete?id=${map.cart_id}" class="delete-btn">
                            Remove
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="cart-footer">
        <div class="footer-left">
            <label class="checkbox-wrapper">
                <input type="checkbox" class="select-all">
                <span>Select All</span>
            </label>
        </div>

        <div class="footer-right">
            <div class="total-info">
                <span>Selected <em>0</em> items</span>
                <span class="total-price">Total: <em>¥0</em></span>
            </div>
            <button class="checkout-btn">Checkout</button>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/jsp/cartInfo/cart.js"></script>
</body>
</html>
