<%--
  Created by IntelliJ IDEA.
  User: Light Spokesperson
  Date: 2025/12/22
  Time: 11:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Information -- Confirm Payment</title>
    <style>
        .all{
            width: 1200px;
            margin: 100px auto;
            text-align: center;
        }
        h1{
            color: #ff0036;
            text-align: center;
        }
        .btn {
            display: block;
            width: 100px;
            height: 40px;
            background-color: #ff0036;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            margin: 20px auto;
            transition: background-color 0.3s;
        }
        img{
            width: 200px;
            object-fit: cover;
        }
    </style>
</head>
<body>
<div class="all">
    <h1>Please Complete the Payment</h1>
    <img src="/images/code.png" alt="">
    <form action="/shopping/orderInfo/add" method="post">
        <input type="submit" value="Payment Completed" class="btn">
    </form>
</div>
</body>
</html>
