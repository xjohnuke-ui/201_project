<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 100%;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
            display: block;
        }
        input[type="text"], input[type="password"], input[type="tel"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #ff0036;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #ff1a1a;
        }
        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
        .success-message {
            color: green;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Edit Profile</h2>

    <!-- JSP Form -->
    <form id="updateForm" action="/UserUpdateServlet" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text"
                   id="username"
                   name="username"
                   placeholder="Enter new username"
                   required>
        </div>

        <div class="form-group">
            <label for="password">New Password:</label>
            <input type="password"
                   id="password"
                   name="password"
                   placeholder="Enter new password"
                   required>
        </div>

        <div class="form-group">
            <label for="phone">Phone Number:</label>
            <input type="tel"
                   id="phone"
                   name="phone"
                   placeholder="Enter new phone number"
                   required
                   pattern="^\\d{11}$">
            <small>Please enter a valid 11-digit phone number</small>
        </div>

        <button type="submit">Save Changes</button>
    </form>
</div>

</body>
</html>
