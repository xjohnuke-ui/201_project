<%--
  Created by IntelliJ IDEA.
  User: Light Spokesperson
  Date: 2025/12/20
  Time: 10:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product Information</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/goodsInfo/add.css">
</head>
<body>
<div class="all">
    <h1>Edit Product Information</h1>

    <form action="/houtai/goodsInfo/update" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td style="width:100px;">Product Name:</td>
                <td style="width:200px;">
                    <input type="text"
                           name="goods_name"
                           value="${goodsSingleInfo.goods_name}">
                </td>
            </tr>

            <tr>
                <td style="width:100px;">Manufacturer:</td>
                <td style="width:200px;">
                    <input type="text"
                           name="manufacturer"
                           value="${goodsSingleInfo.manufacturer}">
                </td>
            </tr>

            <tr>
                <td style="width:100px;">Price:</td>
                <td style="width:200px;">
                    <input type="text"
                           name="price"
                           value="${goodsSingleInfo.price}">
                </td>
            </tr>

            <tr>
                <td style="width:100px;">Product Image:</td>
                <td style="width:200px;">
                    <input type="file"
                           name="pic"
                           value="${goodsSingleInfo.pic}">
                </td>
            </tr>

            <%-- Product Type section intentionally kept commented (logic unchanged) --%>
            <%--
            <tr>
                <td style="width:100px;">Product Type:</td>
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
            --%>

            <tr>
                <td style="width:100px;">Product Description:</td>
                <td style="width:300px;">
                    <textarea name="description">${goodsSingleInfo.description}</textarea>
                </td>
            </tr>

            <tr>
                <td class="no">
                    <input type="submit" value="Update" class="bt">
                </td>
            </tr>

            <input type="hidden"
                   name="id"
                   value="${goodsSingleInfo.goods_id}">
        </table>
    </form>
</div>
</body>
</html>
