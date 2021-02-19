<%-- 
    Document   : answer
    Created on : Feb 10, 2021, 9:27:33 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Demon Quiz History</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"> <!--set default CSS-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" > <!--load font-->
<link rel="stylesheet" href="./asset/fonts/fontawesome-free-5.15.1/css/all.css">
<link rel="stylesheet" href="./asset/css/base.css"/>
<link rel="stylesheet" href="./asset/css/home.css"/>
<link rel="stylesheet" href="./asset/css/history.css"/>
<link rel="stylesheet" href="./asset/css/home-admin.css"/>
<meta name="google-signin-client_id" content="911130984191-u8no05mihn0sra3vd1i05jq6l86t5qcc.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
                        <span id="name-user" class="header-navbar__user__name">Long Demon</span>
                        <ul class="header-user__menu">
                            <li class="user-menu_item"><a href="" class="">Profile</a></li>
                            <li class="user-menu_item"><a href="signOut" class="">Sign out</a></li>
                         </ul>
                    </li>
                    
                </ul>
            </nav>
            <div class="header-with__logo">
                <a href="http://localhost:8084/J3.L.P0014/home" class="header-logo">
                    <i class="fas fa-dragon"></i>
                    <span class="header-logo__text">Demon quiz</span>
                </a>
            </div>
        </div>
    </header>
    <div class="separator"></div>
    <div class="container">
        <c:set var="listExam" value="${sessionScope.LISTEXAM}"/>
        <div class="grid">
            <div class="grid__row" style="flex-direction: column;">
                <div class="select__subject">
                    <span class="select__subject-label">Subject</span>
                    <i class="fas fa-chevron-down"></i>
                    <ul class="filter__menu">
                        <li class="filter-choice"><a class="filter-link" href="countPageForHistory" class=""> All </a></li>
                        <li class="filter-choice"><a class="filter-link" href="countPageWithFilter?subject=N5" class=""> N5 </a></li>
                        <li class="filter-choice"><a class="filter-link" href="countPageWithFilter?subject=N4" class=""> N4 </a></li>

                    </ul>
                </div>
                <c:if test="${not empty listExam}">
                    <div class="question-box" style="">
                        <h2>HISTORY</h2>
                        <div class="menu-items__list">
                            <c:forEach var="exam" items="${listExam}">
                                <div style="display: flex;">
                                    <a href="detailExam?examID=${exam.examID}" class="menu-item" style="width: 400px">
                                        <p class="menu-content">${exam.subjectID}</p>
                                        <p class="menu-content" style="line-height: 20px"><fmt:formatDate value="${exam.examDate}" pattern="dd-MM-yyyy"/></p>
                                        <p class="menu-content">${exam.scores} points</p>
                                    </a>
                                        
                                </div>
                            </c:forEach>
                            <div class="paging">
                                
                                <c:set var="numOfPage" value="${sessionScope.NUMOFPAGEEXAM}"/>
                                <c:set var="currentPage" value="${requestScope.CURRENTPAGE}"/>
                                <a href=""
                                   class="paging-button <c:if test="${(currentPage - 1) le 0}">disable-link</c:if>">&laquo;</a>

                                <c:if test="${not empty numOfPage}">

                                        <c:forEach begin="1" end="${numOfPage}" var="val">
                                            <a href="" 
                                            class="paging-button <c:if test="${currentPage eq val}">paging-button--active</c:if>" >${val}</a>
                                        </c:forEach>

                                </c:if>
                                <a href="" 
                                   class="paging-button <c:if test="${(currentPage + 1) gt numOfPage}">disable-link</c:if>">&raquo;</a>
                            </div>
                            <button class="ok-btn" style="margin: 30px 160px 0px;"><a class="ok-link" href="home">Ok</a></button>    
<!--                            <span id="create-button" class="menu-item" style="width: 395px"><p class="item-content"><i class="fas fa-plus-circle"></i></span>-->
                        </div>
                    </div>
                </c:if>
<%--                <div class="paging">
                    <c:set var="numOfPage" value="${sessionScope.NUMOFPAGE}"/>
                    <c:set var="currentPage" value="${requestScope.CURRENTPAGE}"/>
                    <a href="loadQuestionByAdmin?page=${currentPage - 1}&txtSubjectID=${param.txtSubjectID}"
                       class="paging-button <c:if test="${(currentPage - 1) le 0}">disable-link</c:if>">&laquo;</a>

                    <c:if test="${not empty numOfPage}">

                            <c:forEach begin="1" end="${numOfPage}" var="val">
                                <a href="loadQuestionByAdmin?page=${val}&txtSubjectID=${param.txtSubjectID}" 
                                class="paging-button <c:if test="${currentPage eq val}">paging-button--active</c:if>" >${val}</a>
                            </c:forEach>

                    </c:if>
                    <a href="loadQuestionByAdmin?page=${currentPage + 1}&txtSubjectID=${param.txtSubjectID}" 
                       class="paging-button <c:if test="${(currentPage + 1) gt numOfPage}">disable-link</c:if>">&raquo;</a>
                </div> --%>
            </div>
        </div>
    </div>
    <footer class="footer">

    </footer>
    <script src="./asset/js/create.js"></script>
</body>
</html>
