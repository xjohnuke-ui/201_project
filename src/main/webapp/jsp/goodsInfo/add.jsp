<%--
  Created by IntelliJ IDEA.
  User: Light Spokesperson
  Date: 2025/12/19
  Time: 11:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/goodsInfo/add.css">
</head>
<body>
<div class="all">
    <h1>Add Product</h1>

    <form action="/houtai/goodsInfo/add" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td style="width:100px;">Product Name:</td>
                <td style="width:200px;">
                    <input type="text" name="goods_name">
                </td>
            </tr>

            <tr>
                <td style="width:100px;">Manufacturer:</td>
                <td style="width:200px;">
                    <input type="text" name="manufacturer">
                </td>
            </tr>

            <tr>
                <td style="width:100px;">Price:</td>
                <td style="width:200px;">
                    <input type="text" name="price">
                </td>
            </tr>

            <tr>
                <td style="width:100px;">Product Image:</td>
                <td style="width:200px;">
                    <input type="file" name="pic">
                </td>
            </tr>

            <tr>
                <td style="width:100px;">Category:</td>
                <td style="width:200px;">
                    <select name="type">
                        <option value="Clothing">Clothing</option>
                        <option value="Shoes">Shoes</option>
                        <option value="Fruit">Fruit</option>
                        <option value="Electronics">Electronics</option>
                        <option value="Medicine">Medicine</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td style="width:100px;">Product Description:</td>
                <td style="width:300px;">
                    <textarea name="description"></textarea>
                </td>
            </tr>

            <tr>
                <td class="no">
                    <input type="submit" value="Add" class="bt">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
