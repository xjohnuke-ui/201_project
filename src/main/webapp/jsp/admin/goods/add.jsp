<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product - Admin Panel</title>
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
                <h1>Add New Product</h1>
                <div class="user-info">
                    <span>Welcome, ${sessionScope.admin_name}</span>
                    <a href="/admin/logout" class="logout-btn">Logout</a>
                </div>
            </div>

            <div class="form-container">
                <h2>Product Information</h2>
                <form action="/admin/goods/add" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="goods_name">Product Name *</label>
                        <input type="text" id="goods_name" name="goods_name" required placeholder="Enter product name">
                    </div>

                    <div class="form-group">
                        <label for="manufacturer">Manufacturer *</label>
                        <input type="text" id="manufacturer" name="manufacturer" required placeholder="Enter manufacturer name">
                    </div>

                    <div class="form-group">
                        <label for="price">Price *</label>
                        <input type="text" id="price" name="price" required placeholder="Enter price (e.g., 99.99)">
                    </div>

                    <div class="form-group">
                        <label for="type">Category *</label>
                        <select id="type" name="type" required>
                            <option value="">Select a category</option>
                            <option value="Clothing">Clothing</option>
                            <option value="Shoes">Shoes</option>
                            <option value="Fruit">Fruit</option>
                            <option value="Electronics">Electronics</option>
                            <option value="Medicine">Medicine</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="pic">Product Image *</label>
                        <input type="file" id="pic" name="pic" required accept="image/*">
                    </div>

                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea id="description" name="description" placeholder="Enter product description"></textarea>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-success">Add Product</button>
                        <a href="/admin/goods/list" class="btn btn-info">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
