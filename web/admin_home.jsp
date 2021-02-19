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
<title>Demon Quiz Admin</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"> <!--set default CSS-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" > <!--load font-->
<link rel="stylesheet" href="./asset/fonts/fontawesome-free-5.15.1/css/all.css">
<link rel="stylesheet" href="./asset/css/base.css"/>
<link rel="stylesheet" href="./asset/css/home.css"/>
<link rel="stylesheet" href="./asset/css/home-admin.css"/>

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
                <c:if test="${not empty sessionScope.SUBJECTADMIN}">
                    <div class="main-box">
                    <h2>SUBJECT</h2>
                    
                    <div class="menu-items__list">
                        <c:forEach var="dto" items="${SUBJECTADMIN}">
                            <div style="display: flex; height: 60px">
                                <a href="countNumOfPage?txtSubjectID=${dto.subjectID}" class="menu-item"><p class="item-content">${dto.subjectName}</p></a>
                                <c:if test="${dto.active eq true}"><a href="deleteSubject?txtSubjectID=${dto.subjectID}" style=" color: white; font-size: 2rem; margin: 30px 0;"><i class="fas fa-trash-alt"></i></a> </c:if>
                                <c:if test="${dto.active eq false}"><a href="restoreSubject?txtSubjectID=${dto.subjectID}" style=" color: white; font-size: 2rem; margin: 30px 0;"><i class="fas fa-trash-restore-alt"></i></a> </c:if>
                            </div>
                            
                        </c:forEach>
                            <span id="create-button" class="menu-item"><p class="item-content"><i class="fas fa-plus-circle"></i></span>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
    </div>
    
    
    
    <footer class="footer">

    </footer>
    <div id="myModal" class="modal">
        <div id="contentOut" class="modal-overlay"></div>
        <div class="modal-body">
            <div id="createSubjectBox" class="create-form">
                <form action="AddSubject" method="POST" style="width: 100%">                
                    <div class="create-form__container">
                        <div class="create-form__header">
                            <h3 class="create-form__heading">Create Subject</h3>
                        </div>
        
                        <div class="create-form__form">
                            <div class="create-form__group">
                                <input type="text" class="create-form__input" placeholder="Subject ID" name="txtSubID" value="" autocomplete="off" required>
                                
                            </div>
                            <div class="create-form__group">
                                <input type="text" class="create-form__input" placeholder="Subject Name" name="txtSubName" value="" required>
                                
                            </div>
                        </div>
                        
                        <div class="create-form__controls">
                            <input class="btn btn--primary" type="submit" value="Create" name="btAction" />
                            <button id="back-button" type="button" class="btn create-form__controls__back">TRO LAI</button>
                        </div>
                    </div>
                </form>  
            </div>           
        </div>  
    </div>
    <script src="./asset/js/create.js"></script>
</body>
</html>

