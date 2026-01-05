<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management - Admin Panel</title>
    <link rel="stylesheet" href="../../../css/admin/admin.css">
</head>
<body>
    <div class="admin-wrapper">
        <!-- Sidebar -->
        <div class="sidebar">
            <div class="sidebar-header">
                <h3>Admin Panel</h3>
                <p>Online Shopping Mall</p>
            </div>
            <ul class="sidebar-menu">
                <li>
                    <a href="/admin/dashboard">
                        <span>Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/goods/list" class="active">
                        <span>Products</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/order/list">
                        <span>Orders</span>
                    </a>
                </li>
                <li>
                    <a href="/shopping/list">
                        <span>View Store</span>
                    </a>
                </li>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <div class="admin-header">
                <h1>Product Management</h1>
                <div class="user-info">
                    <span>Welcome, ${sessionScope.admin_name}</span>
                    <a href="/admin/logout" class="logout-btn">Logout</a>
                </div>
            </div>

            <div class="table-container">
                <div class="table-header">
                    <h2>All Products</h2>
                    <form class="search-form" action="/admin/goods/list" method="get">
                        <input type="text" name="searchName" placeholder="Product name..." value="${searchName}">
                        <select name="searchType">
                            <option value="">All Categories</option>
                            <option value="Clothing" ${searchType == 'Clothing' ? 'selected' : ''}>Clothing</option>
                            <option value="Shoes" ${searchType == 'Shoes' ? 'selected' : ''}>Shoes</option>
                            <option value="Fruit" ${searchType == 'Fruit' ? 'selected' : ''}>Fruit</option>
                            <option value="Electronics" ${searchType == 'Electronics' ? 'selected' : ''}>Electronics</option>
                            <option value="Medicine" ${searchType == 'Medicine' ? 'selected' : ''}>Medicine</option>
                        </select>
                        <button type="submit" class="btn btn-primary">Search</button>
                        <a href="/admin/goods/list" class="btn btn-info">Reset</a>
                    </form>
                </div>

                <form action="/admin/goods/delete" method="post" id="batchForm">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th><input type="checkbox" id="selectAll"></th>
                                <th>ID</th>
                                <th>Image</th>
                                <th>Product Name</th>
                                <th>Manufacturer</th>
                                <th>Price</th>
                                <th>Category</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${goodsInfoList}" var="item">
                                <tr>
                                    <td><input type="checkbox" name="id" value="${item.goods_id}"></td>
                                    <td>${item.goods_id}</td>
                                    <td><img src="/goodsPic/${item.pic}" alt="${item.goods_name}"></td>
                                    <td>${item.goods_name}</td>
                                    <td>${item.manufacturer}</td>
                                    <td class="price">$${item.price}</td>
                                    <td>${item.type}</td>
                                    <td class="actions">
                                        <a href="/admin/goods/update?id=${item.goods_id}" class="btn btn-warning btn-sm">Edit</a>
                                        <a href="/admin/goods/delete?id=${item.goods_id}" class="btn btn-danger btn-sm" 
                                           onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="table-footer">
                        <div>
                            <button type="submit" class="btn btn-danger" 
                                    onclick="return confirm('Are you sure you want to delete selected products?')">
                                Delete Selected
                            </button>
                        </div>
                        <div>
                            <a href="/admin/goods/add" class="btn btn-success">Add New Product</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        document.getElementById('selectAll').addEventListener('change', function() {
            var checkboxes = document.querySelectorAll('input[name="id"]');
            checkboxes.forEach(function(checkbox) {
                checkbox.checked = this.checked;
            }, this);
        });
    </script>
</body>
</html>
