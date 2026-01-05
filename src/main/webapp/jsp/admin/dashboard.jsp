<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Admin Panel</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin.css">
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
                    <a href="/admin/dashboard" class="active">
                        <span>Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/goods/list">
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
                <h1>Dashboard</h1>
                <div class="user-info">
                    <span>Welcome, ${sessionScope.admin_name}</span>
                    <a href="/admin/logout" class="logout-btn">Logout</a>
                </div>
            </div>

            <!-- Statistics Cards -->
            <div class="dashboard-cards">
                <div class="card stat-card">
                    <div class="icon blue">P</div>
                    <div class="info">
                        <h3>${totalProducts}</h3>
                        <p>Total Products</p>
                    </div>
                </div>
                <div class="card stat-card">
                    <div class="icon green">O</div>
                    <div class="info">
                        <h3>${totalOrders}</h3>
                        <p>Total Orders</p>
                    </div>
                </div>
                <div class="card stat-card">
                    <div class="icon orange">C</div>
                    <div class="info">
                        <h3>${totalCustomers}</h3>
                        <p>Total Customers</p>
                    </div>
                </div>
                <div class="card stat-card">
                    <div class="icon red">!</div>
                    <div class="info">
                        <h3>${pendingOrders}</h3>
                        <p>Pending Orders</p>
                    </div>
                </div>
            </div>

            <!-- Quick Actions -->
            <div class="card">
                <h3 style="margin-bottom: 20px; color: #333;">Quick Actions</h3>
                <div style="display: flex; gap: 15px; flex-wrap: wrap;">
                    <a href="/admin/goods/add" class="btn btn-primary">Add New Product</a>
                    <a href="/admin/goods/list" class="btn btn-success">Manage Products</a>
                    <a href="/admin/order/list" class="btn btn-warning">View Orders</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
