<%-- 
    Document   : home
    Created on : Jan 30, 2021, 10:24:50 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Demon Quiz</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"> <!--set default CSS-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" > <!--load font-->
<link rel="stylesheet" href="./asset/fonts/fontawesome-free-5.15.1/css/all.css">
<link rel="stylesheet" href="./asset/css/base.css"/>
<link rel="stylesheet" href="./asset/css/home.css"/>
<!--    ///////////////////////////////////-->
<meta name="google-signin-client_id" content="911130984191-u8no05mihn0sra3vd1i05jq6l86t5qcc.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--   ////////////////////////////////////-->
</head>
<body>
<div class="app">
    <header class="header">
        <div class="grid">
            <nav class="header-navbar">
                <ul class="header-navbar__list">
                    <li class="header-navbar__item">
                        <span class="header-navbar__item--nonePointer">Ket noi</span>
                        <a href="https://www.facebook.com/ame.ryu0311/" target= "_blank" class="header-navbar__icon">
                            <i class="fab fa-facebook"></i>
                        </a>
                        <a href="https://www.instagram.com/long.demon/" target= "_blank" class="header-navbar__icon">
                            <i class="fab fa-instagram"></i>
                        </a>
                    </li>
                </ul>
                <ul class="header-navbar__list">  
                    <li class="header-navbar__item header-navbar__item--has-notify">
                        <i class="fas fa-history"></i>
                        <a href="countPageForHistory" class="headar-navbar__item__link" style="color: white">
                            Lich su
                        </a>
                     </li>
                    <li id="labelUser" class="header-navbar__item header-navbar__user">
                        <img src="https://cf.shopee.vn/file/703b68de307972ac59159c22ba1ab738_tn" alt="" class="header-navbar__user__avatar">
                        <span id="name-user" class="header-navbar__user__name">${sessionScope.NAMEUSER}</span>
                        <ul class="header-user__menu">
                            <li class="user-menu_item"><a href="" class="">Profile</a></li>
                            <li class="user-menu_item"><a href="signOut" class="">Sign out</a></li>
                         </ul>
                    </li>
                    
                </ul>
            </nav>
            <div class="header-with__logo">
                <a href="home" class="header-logo">
                    <i class="fas fa-dragon"></i>
                    <span class="header-logo__text">Demon quiz</span>
                </a>
            </div>
        </div>
    </header>
    <div class="separator"></div>
    <div class="container">
        <div class="grid">
            <div class="grid__row">
                <c:if test="${not empty SUBJECT}">
                    <div class="main-box">
                    <h2>SUBJECT</h2>
                    <div class="menu-items__list">
                        <c:forEach var="dto" items="${SUBJECT}">
                            <a href="loadQuestion?txtSubjectID=${dto.subjectID}" class="menu-item" onclick="return confirm('You want to start quiz right now ???');"><p class="item-content">${dto.subjectName}</p></a>
                        </c:forEach>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
    </div>
    <footer class="footer">

    </footer>

</body>
</html>

