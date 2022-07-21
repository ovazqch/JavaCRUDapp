<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="registrationPage.css">
        <title>Registration Page</title>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="background">
            <div class="shape"></div>
            <div class="shape"></div>
        </div>
            <form action="Register" method="post">
                <h3>Registration</h3>
                <label> First Name: </label><input type="text" placeholder="First Name" name="firstName" required id="firstName">
                <label> Last Name: </label><input type="text" placeholder="Last Name" name="lastName" required id="lastName">
                <label> Email: </label><input type="text" placeholder="Email" name="email" required id="email">
                <label> Password : </label><input type="password" placeholder="Password" name="password" required id="password"><P></P>
                <button type="submit" value="Sign Up"> Submit </button>
                <button type="reset" value="Reset"> Reset </button>
                <a href="index.jsp" class="button">Back to Login Page</a>
            </form>
        </div>
    </body>
</html>