<%--
  Created by IntelliJ IDEA.
  User: Light Spokesperson
  Date: 2025/12/17
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
<div class="header">
    <a href="#" class="logo">Online Shopping Mall</a>
</div>

<div class="container">
    <div class="register-image">
        <img src="../images/register.png" alt="">
    </div>

    <div class="register-form">
        <div class="register-title">Account Registration</div>
        <form action="/register" method="post">
            <div class="form-group">
                <div class="form-item">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" required>
                </div>
            </div>

            <div class="form-group">
                <div class="form-item">
                    <label for="idCard">ID Card Number</label>
                    <input type="text" id="idCard" name="id_card" required>
                </div>
            </div>

            <div class="form-group">
                <div class="form-item">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
            </div>

            <div class="form-group">
                <div class="form-item">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
            </div>

            <div class="form-group">
                <div class="form-item">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>
            </div>

            <button type="submit" class="register-btn">Register</button>
        </form>

        <div class="login-link">
            <a href="/login">Already have an account? Login now</a>
        </div>
    </div>
</div>

</body>
</html>
