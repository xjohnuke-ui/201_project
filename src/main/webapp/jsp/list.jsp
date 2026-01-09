<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Light Spokesperson
  Date: 2025/12/20
  Time: 0:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
</head>
<body>
<div class="container">
    <h2 class="page-title">Product Management</h2>

    <form action="/houtai/goodsInfo/delete" method="post">
        <table class="product-table">
            <tr>
                <th style="width:50px;">Select</th>
                <th style="width:120px;">Product ID</th>
                <th style="width:150px;">Product Name</th>
                <th style="width:100px;">Image</th>
                <th style="width:150px;">Manufacturer</th>
                <th style="width:100px;">Price</th>
                <th style="width:100px;">Category</th>
                <th>Description</th>
                <th style="width:150px;">Action</th>
            </tr>

            <c:forEach items="${goodsInfoList}" var="map">
                <tr>
                    <td>
                        <input type="checkbox"
                               class="checkbox"
                               name="id"
                               value="${map.goods_id}">
                    </td>
                    <td>${map.goods_id}</td>
                    <td>${map.goods_name}</td>
                    <td>
                        <img src="/goodsPic/${map.pic}"
                             alt=""
                             style="width: 80px; object-fit: cover;">
                    </td>
                    <td>${map.manufacturer}</td>
                    <td class="price">¥${map.price}</td>
                    <td>${map.type}</td>
                    <td>${map.description}</td>
                    <td class="operation-cell">
                        <a href="/houtai/goodsInfo/update?id=${map.goods_id}"
                           class="btn btn-edit">Edit</a>
                        <a href="/houtai/goodsInfo/delete?id=${map.goods_id}"
                           class="btn btn-delete">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="toolbar">
            <div class="left-tools">
                <%-- <a href="/houtai/goodsInfo/delete" class="btn btn-batch-delete">Batch Delete</a> --%>
                <button class="btn btn-batch-delete">Batch Delete</button>
            </div>

            <div class="right-tools">
                <a href="/houtai/goodsInfo/add" class="btn btn-add">Add Product</a>
                <%-- <button class="btn btn-add">Add Product</button> --%>
            </div>
        </div>
    </form>
</div>
</body>
</html>
