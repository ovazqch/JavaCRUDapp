<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="EditProductCSS.css">
        <title>Edit Product Page</title>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="background">
            <div class="shape"></div>
            <div class="shape"></div>
        </div>
        <div class="form">
            <form action="UpdateExProduct" method="post" class="login" enctype="multipart/form-data">
                <label> Product ID : </label><input type="text" readonly name="productID" required id="txt" value="${Product.productID}">
                <label> Product Name : </label><input type="text" name="productName" required id="txt" value="${Product.productName}">
                <label> Product Description : </label><input type="text" name="productDescription" required id="txt" value="${Product.productDescription}">
                <label> Available Quantity : </label><input type="number" name="availableQty" required id="txt" value="${Product.availableQty}">
                <label> Price : </label><input type="number" name="price" required id="txt" value="${Product.price}">
                <label> International Shipping : </label><input type="text" name="internationalShipment" required id="txt" value="${Product.internationalShipment}">
                <label> Photo :</label><P></P> <input type="file" name="photo"  id="txt"  >

                <button type="submit" value="Submit"> Submit </button>
                <button type="button" name="Cancel" value="DisplayAllProducts" onclick="history.back()"> Cancel </button>
            </form>
        </div>
    </body>
</html>
