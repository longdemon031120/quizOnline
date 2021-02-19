<%-- 
    Document   : login
    Created on : Jan 29, 2021, 10:49:19 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./asset/css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <c:set var="ERROR_REGISTER" value="${requestScope.CREATEERROR}"/>
    <c:if test="${not empty ERROR_REGISTER || not empty param.mess}">
        <style>
            .login-box{
                display: none;
            }
            .register-box{
                display: block;
            }
        </style>
    </c:if>
    <c:set var="ERROR_LOGIN" value="${requestScope.LOGINERROR}"/>
    <div class="login-box main-box">
        <h2>Demon Quiz</h2>
        <form action="login" method="POST">
          <div class="user-box">
            <input type="text" name="txtEmail" required="" <c:if test="${not empty ERROR_LOGIN.usernameValid}">value="${ERROR_LOGIN.usernameValid}"</c:if>>
            <label>Email</label>
            <c:if test="${not empty ERROR_LOGIN.usernameNotValid}"><p class="notify-error">${ERROR_LOGIN.usernameNotValid}</p></c:if>
            
          </div>
          <div class="user-box">
            <input type="password" name="txtPassword" required="">
            <label>Password</label>
            <c:if test="${not empty ERROR_LOGIN.passwordNotValid}"><p class="notify-error">${ERROR_LOGIN.passwordNotValid}</p></c:if>
            
          </div>
          <button class="btn">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Sign in
          </button>
          <p class="message">Not registered? <a style="padding: 5px; color: #fff;" href="#">Create an account</a></p>
        </form>
        
    </div>
    <div class="register-box main-box">
        <h2>Register</h2>
        <form action="register" method="POST">
        <div class="user-box">
          <input type="text" name="txtEmail" required="" autocomplete="off">
          <label>Email</label>
          <c:if test="${not empty ERROR_REGISTER.emailErr}"><p class="notify-error">${ERROR_REGISTER.emailErr}</p></c:if>
          <c:if test="${not empty ERROR_REGISTER.emailIsExisted}"><p class="notify-error">${ERROR_REGISTER.emailIsExisted}</p></c:if>
        </div>
        <div class="user-box">
          <input type="text" name="txtName" required="" autocomplete="off">
          <label>Name</label>
          <c:if test="${not empty ERROR_REGISTER.fullnameErr}"><p class="notify-error">${ERROR_REGISTER.fullnameErr}</p></c:if>
        </div>
        <div class="user-box">
          <input type="password" name="txtPassword" required="">
          <label>Password</label>
          <c:if test="${not empty ERROR_REGISTER.passwordLengthErr}"><p class="notify-error">${ERROR_REGISTER.passwordLengthErr}</p></c:if>
        </div>
        <div class="user-box">
          <input type="password" name="txtConfirmPass" required="">
          <label>Confirm Password</label>
          <c:if test="${not empty ERROR_REGISTER.confirmErr}"><p class="notify-error">${ERROR_REGISTER.confirmErr}</p></c:if>
        </div>
        <c:if test="${not empty param.mess}"><p class="notify-success">${param.mess}</p></c:if>
        <button class="btn">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Sign up
        </button>
          
        <p class="message">Already registered? <a style="padding: 5px; color: #fff;" href="#">Sign In</a></p>
      </form>
      
  </div>
  <script src="./asset/js/login.js"></script>
</body>
</html>
