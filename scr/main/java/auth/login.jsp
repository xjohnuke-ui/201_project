<%--
  Created by IntelliJ IDEA.
  User: Light Spokesperson
  Date: 2025/12/17
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="header" style="text-align: center; margin-bottom: 30px;">
    <a href="#" class="logo" style="font-family: 'STXingkai', '华文行楷', cursive; font-size: 36px;">
        Online Shopping Mall
    </a>
</div>

<div class="container">
    <div class="login-content">
        <div class="login-image">
            <img src="../images/shopping.png" alt="shopping"
                 style="border-radius: 8px; object-fit: cover; height: 400px;">
        </div>
        <form action="/login" method="post" class="login-form">
            <div class="login-title">Account Login</div>
            <div class="form-item">
                <input type="text" name="username" placeholder="Enter your username">
            </div>
            <div class="form-item">
                <input type="password" name="password" placeholder="Enter your password">
            </div>
            <button class="login-btn">Login</button>
            <div class="login-links">
                <a href="/register">Register now</a>
                <span style="margin: 0 10px; color: #ddd;">|</span>
                <a href="/admin/login">Admin Login</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
