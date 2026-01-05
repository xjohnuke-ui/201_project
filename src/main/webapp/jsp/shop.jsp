<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Light Spokesperson
  Date: 2025/12/17
  Time: 20:18
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online Shopping Mall</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shop.css">
</head>

<body>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const categoryItems = document.querySelectorAll('.category-item');
        categoryItems.forEach(item => {
            item.addEventListener('click', function () {
                categoryItems.forEach(item => {
                    item.classList.remove('active');
                    const link = item.querySelector('a');
                    link.style.color = '';
                });
                this.classList.add('active');
                const link = this.querySelector('a');
                link.style.color = '#ff0036';
                event.stopPropagation();
            });
        });
    })
</script>

<div class="header">
    <div class="header-content">
        <a href="/shopping/list" class="logo">Online Shopping Mall</a>

        <form class="search-box" action="/shopping/list" method="get">
            <input type="text"
                   class="search-input"
                   placeholder="Enter product name"
                   name="searchName"
                   value="${searchName}">
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

<div class="category-nav">
    <ul class="category-list">
        <li class="category-item active">
            <a href="/shopping/list">All Products</a>
        </li>
        <li class="category-item">
            <a href="/shopping/list?searchType=Clothing">Clothing</a>
        </li>
        <li class="category-item">
            <a href="/shopping/list?searchType=Shoes">Shoes</a>
        </li>
        <li class="category-item">
            <a href="/shopping/list?searchType=Fruit">Fruit</a>
        </li>
        <li class="category-item">
            <a href="/shopping/list?searchType=Electronics">Electronics</a>
        </li>
        <li class="category-item">
            <a href="/shopping/list?searchType=Medicine">Medicine</a>
        </li>
    </ul>
</div>

<div class="main-content">
    <div class="product-grid">
        <!-- Product List -->
        <c:forEach items="${goodsInfoList}" var="map">
            <a href="/shopping/detail?id=${map.goods_id}">
                <div class="product-card">
                    <img src="/goodsPic/${map.pic}" alt="" class="product-image">
                    <div class="product-title">${map.goods_name}</div>
                    <div class="product-price">${map.price}</div>
                </div>
            </a>
        </c:forEach>
    </div>
</div>

</body>
</html>
