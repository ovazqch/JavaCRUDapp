<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="stylecss.css">
        <title>products</title>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    </head>

    <body class="login">
        <div>
            <h3>Welcome: ${user.firstName} ${user.lastName}</h3>
            <div>
                <form action="AddNewProduct.jsp">
                    <button type="submit" class="addbtn">Add a new Product</button>
                </form>
            </div>
        </div>
        <div class="background">
            <div class="shape"></div>
            <div class="shape"></div>
        </div>
        <div class="table">
            <table id="products">
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Stock</th>
                    <th>Intl. Ship.</th>
                    <th>Price</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${productDisplay}" var="product">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/images/${product.photo}" width="120">
                        </td>
                        <td>${product.productName}</td>
                        <td>${product.productDescription}</td>
                        <td>${product.availableQty}</td>
                        <td>${product.internationalShipment}</td>
                        <td>${product.price}</td>
                        <td>
                            <form action="EditProduct">
                                <input type="hidden" name="id" value="${product.productID}">
                                <button type="submit" class="updbtn">Edit</button>
                            </form>
                        </td>
                        <td>
                            <form action="DeleteProduct">
                                <input type="hidden" name="id" value="${product.productID}">
                                <button type="submit" class="delbtn">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
