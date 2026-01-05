<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Management - Admin Panel</title>
    <link rel="stylesheet" href="../../../css/admin/admin.css">
    <style>
        .status-select {
            padding: 5px 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 12px;
        }
        .update-status-btn {
            padding: 5px 10px;
            font-size: 12px;
        }
    </style>
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
                    <a href="/admin/goods/list">
                        <span>Products</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/order/list" class="active">
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
                <h1>Order Management</h1>
                <div class="user-info">
                    <span>Welcome, ${sessionScope.admin_name}</span>
                    <a href="/admin/logout" class="logout-btn">Logout</a>
                </div>
            </div>

            <div class="table-container">
                <div class="table-header">
                    <h2>All Orders</h2>
                    <form class="search-form" action="/admin/order/list" method="get">
                        <input type="text" name="searchOrderId" placeholder="Order ID..." value="${searchOrderId}">
                        <select name="searchStatus">
                            <option value="">All Status</option>
                            <option value="Pending" ${searchStatus == 'Pending' ? 'selected' : ''}>Pending</option>
                            <option value="Processing" ${searchStatus == 'Processing' ? 'selected' : ''}>Processing</option>
                            <option value="Shipped" ${searchStatus == 'Shipped' ? 'selected' : ''}>Shipped</option>
                            <option value="Completed" ${searchStatus == 'Completed' ? 'selected' : ''}>Completed</option>
                            <option value="Cancelled" ${searchStatus == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                        </select>
                        <button type="submit" class="btn btn-primary">Search</button>
                        <a href="/admin/order/list" class="btn btn-info">Reset</a>
                    </form>
                </div>

                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Product</th>
                            <th>Customer</th>
                            <th>Phone</th>
                            <th>Qty</th>
                            <th>Total</th>
                            <th>Status</th>
                            <th>Order Time</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orderList}" var="order">
                            <tr>
                                <td>#${order.order_id}</td>
                                <td>
                                    <div style="display: flex; align-items: center; gap: 10px;">
                                        <img src="/goodsPic/${order.pic}" alt="${order.goods_name}" 
                                             style="width: 50px; height: 50px; object-fit: cover; border-radius: 5px;">
                                        <span>${order.goods_name}</span>
                                    </div>
                                </td>
                                <td>${order.customer_name}</td>
                                <td>${order.customer_phone}</td>
                                <td>${order.quantity}</td>
                                <td class="price">$${order.total_amount}</td>
                                <td>
                                    <span class="status-badge status-${order.status.toLowerCase()}">${order.status}</span>
                                </td>
                                <td>${order.created_at}</td>
                                <td>
                                    <form action="/admin/order/update" method="post" style="display: flex; gap: 5px; align-items: center;">
                                        <input type="hidden" name="id" value="${order.order_id}">
                                        <select name="status" class="status-select">
                                            <option value="Pending" ${order.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                            <option value="Processing" ${order.status == 'Processing' ? 'selected' : ''}>Processing</option>
                                            <option value="Shipped" ${order.status == 'Shipped' ? 'selected' : ''}>Shipped</option>
                                            <option value="Completed" ${order.status == 'Completed' ? 'selected' : ''}>Completed</option>
                                            <option value="Cancelled" ${order.status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                                        </select>
                                        <button type="submit" class="btn btn-primary update-status-btn">Update</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <c:if test="${empty orderList}">
                    <div style="text-align: center; padding: 50px; color: #888;">
                        <p>No orders found.</p>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
