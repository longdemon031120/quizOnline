<%-- 
    Document   : quiz
    Created on : Feb 1, 2021, 4:47:05 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Demon quiz</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"> <!--set default CSS-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" > <!--load font-->
<link rel="stylesheet" href="./asset/fonts/fontawesome-free-5.15.1/css/all.css">
<link rel="stylesheet" href="./asset/css/base.css"/>
<link rel="stylesheet" href="./asset/css/home.css"/>
<link rel="stylesheet" href="./asset/css/quiz.css"/>
<!--    ///////////////////////////////////-->
<meta name="google-signin-client_id" content="911130984191-u8no05mihn0sra3vd1i05jq6l86t5qcc.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-animate.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-route.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js"></script>
<script src="https://cdn.gitcdn.link/cdn/angular/bower-material/v1.1.0-rc.5/angular-material.js"></script>
<script src="https://rawgit.com/Crawlink/material-angular-paging/master/build/dist.min.js"></script>

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
                        <span id="name-user" class="header-navbar__user__name">Long Demon</span>
                        <ul class="header-user__menu">
                            <li class="user-menu_item"><a href="" class="">Profile</a></li>
                            <li class="user-menu_item"><a href="signOut" class="">Sign out</a></li>
                         </ul>
                    </li>
                    
                </ul>
            </nav>
            <div class="header-with__logo">
                <a href="http://localhost:8084/J3.L.P0014/" class="header-logo">
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
                <c:set var="listAnswerAndQuestion" value="${sessionScope.ANSWER_AND_QUESTION}"/>
                <c:if test="${not empty listAnswerAndQuestion}">
                <div id="start" class="start-button">Start</div>
                <div id="count">45:00</div>               
                
                    <div class="father-of-question-box" id="father-of-question-box">
                        <form action="submit" method="POST">
                            <c:forEach var="answerAndQuestion" items="${listAnswerAndQuestion}" varStatus="counter">
                            <div id="${counter.count}" class="question-container" id="question-container">
                                <div class="question-box"><span class="question-content">${answerAndQuestion.key.questionTitle}</span></div>

                                <div class="answer-box">
                                     <c:forEach var="answer" items="${answerAndQuestion.value}">
                                        <input id="${answer.answerID}" type="radio" name="${answerAndQuestion.key.questionID}" value="${answer.answerID}">
                                        <label for="${answer.answerID}" class="answer">${answer.answerContent}</label>

                                     </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                            <input id="submit-btn" type="submit" class="submit-btn" value="Submit">
                        </form>
                    

                    <div ng-app="DemoApp" flex layout="column" class="paging-box">
                        <div flex ng-controller="MainController" layout="column">
                            <section layout="row" layout-padding="">
                      
                                <cl-paging flex cl-pages="paging.total" , cl-steps="6" , cl-page-changed="paging.onPageChanged()" , cl-align="center center" , cl-current-page="paging.current"></cl-paging>
                      
                            </section>
                      
                        </div>
                    </div>
                </div> 
                </c:if>
                <c:if test="${empty listAnswerAndQuestion}">
                    <h1 style="margin: 45px auto; padding: 30px 0; color: white; font-size: 3rem;">Not enough questions to start the exam in this subject</h1>
                    <form action="home">
                        <input class="start-button" type="submit" value="Back" />
                    </form>
                </c:if>
  
            </div>         
        </div>
    </div>
    <footer class="footer">

    </footer>
    <script src="./asset/js/quiz.js"></script>
</body>
</html>

