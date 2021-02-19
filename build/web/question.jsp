<%-- 
    Document   : answer
    Created on : Feb 10, 2021, 9:27:33 PM
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
<meta name="google-signin-client_id" content="911130984191-u8no05mihn0sra3vd1i05jq6l86t5qcc.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<div class="app">
    <c:set var="numOfPage" value="${sessionScope.NUMOFPAGE}"/>
    <c:set var="currentPage" value="${requestScope.CURRENTPAGE}"/>
    <header class="header">
        <div class="grid">
            <button class="back-btn"><a style="color: white" href="admin_home">Back</a></button>
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
                <a href="admin_home" class="header-logo">
                    <i class="fas fa-dragon"></i>
                    <span class="header-logo__text">Demon quiz</span>
                </a>
            </div>
            <div class="header-with__search">
                <form action="searchQuestion" style="width: 100%">
                    <div class="header-search">
                        <input type="text" class="header-search__input" placeholder="Search" name="txtSearchValue" value="${param.txtSearchValue}">
                        <input type="hidden" name="txtSubjectID" value="${param.txtSubjectID}" />
                        <button type="submit" class="header-search__btn" name="btAction" value="Search">
                            <i class="header-search__btn__icon fas fa-search"></i>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </header>
    <div class="separator"></div>
    <div class="container">
        <c:set var="listQuestion" value="${requestScope.LISTQUESTIONADMIN}"/>
        <div class="grid">
            <div class="grid__row">
                <c:if test="${not empty listQuestion}">
                    <div class="question-box" style="">
                        <h2>QUESTION</h2>
                        <div class="menu-items__list">
                            <c:forEach var="question" items="${listQuestion}">
                                <c:if test="${not empty requestScope.IDFORSHOW}">
                                    <c:if test="${question.questionID eq requestScope.IDFORSHOW}">
                                        <c:set var="id" value="${question.questionID}"/>
                                        <c:set var="title" value="${question.questionTitle}"/>
                                    </c:if>
                                </c:if>
                                
                                <div style="display: flex;">
                                    <a href="loadDetailQuestion?txtSubjectID=${param.txtSubjectID}&currentPage=${currentPage}&txtQuestionID=${question.questionID}" class="menu-item" style="width: 400px">
                                        <p style="margin: auto 5px;">${question.questionID}</p>
                                        <p class="menu-content" style="line-height: 20px">${question.questionTitle}</p>
                                    </a>
                                    <c:if test="${question.status eq true}">
                                        <a href="deleteQuestion?txtQuestionID=${question.questionID}&txtSubjectID=${param.txtSubjectID}" style="color: white; font-size: 2rem; margin: 30px 0;"><i class="fas fa-trash-alt"></i></a>
                                    </c:if>
                                    <c:if test="${question.status eq false}">
                                        <a href="restoreQuestion?txtQuestionID=${question.questionID}&txtSubjectID=${param.txtSubjectID}" style="color: white; font-size: 2rem; margin: 30px 0;"><i class="fas fa-trash-restore-alt"></i></a>
                                    </c:if> 
                                </div>
                            </c:forEach>
                            <span id="create-button" class="menu-item" style="width: 395px"><p class="item-content"><i class="fas fa-plus-circle"></i></span>
                        </div>
                    </div>
                
                    <div class="paging">
                        
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
                    </div>
                </c:if>
                <c:if test="${empty listQuestion}">
                    <div class="question-box" style="">
                        <h2>QUESTION</h2>
                        <div class="menu-items__list">  
                            <span id="create-button" class="menu-item" style="width: 395px"><p class="item-content"><i class="fas fa-plus-circle"></i></span>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <footer class="footer">

    </footer>
        <c:if test="${not empty requestScope.CREATEERROR}">
            <style>
                .modal{
                    display: flex;
                }
            </style>
        </c:if>
    <div id="myModal" class="modal">
        <div id="contentOut" class="modal-overlay"></div>
        <div class="modal-body">
            <div id="createSubjectBox" class="create-form">
                <form action="AddQuestion" method="POST" style="width: 100%">                
                    <div class="create-form__container">
                        <div class="create-form__header">
                            <h3 class="create-form__heading">Create Question</h3>
                        </div>
        
                        <div class="create-form__form">
                            <div class="create-form__group">
                                <input type="text" class="create-form__input" placeholder="Question ID" name="txtQuestionID" value="" autocomplete="off" required>
                                
                            </div>
                            <div class="create-form__group">
                                <input type="text" class="create-form__input" placeholder="Question Title" name="txtQuestionTitle" value="" required>
                            </div>
                            <div class="create-form__group" style="display: flex; flex-wrap: wrap; justify-content: space-between;">
                                <input type="hidden" name="txtSubjectID" value="${param.txtSubjectID}" />
                                <input type="radio" name="chkAnswer" value="txtAnswerA" required>
                                <input style="width: 45%" type="text" class="create-form__input" placeholder="Answer A" name="txtAnswerA" value="" required>
                                <input type="radio" name="chkAnswer" value="txtAnswerB">
                                <input style="width: 45%" type="text" class="create-form__input" placeholder="Answer B" name="txtAnswerB" value="" required>
                                <input type="radio" name="chkAnswer" value="txtAnswerC">
                                <input style="width: 45%" type="text" class="create-form__input" placeholder="Answer C" name="txtAnswerC" value="" required>
                                <input type="radio" name="chkAnswer" value="txtAnswerD">
                                <input style="width: 45%" type="text" class="create-form__input" placeholder="Answer D" name="txtAnswerD" value="" required>
                            </div>
                        </div>
                                <div><p style="color: red; font-size: 1.6rem; text-align: center;">${requestScope.CREATEERROR}</p></div>
                        <div class="create-form__controls">
                            <input class="btn btn--primary" type="submit" value="Create" name="btAction" />
                            <button id="back-button" type="button" class="btn create-form__controls__back">TRO LAI</button>
                        </div>
                    </div>
                </form>  
            </div>           
        </div>  
    </div>
        <c:if test="${not empty requestScope.DETAILQUESTION}">
        <style>
            .modal2{
                display: flex;
            }
        </style>
    </c:if>                    
    <div id="myModal2" class="modal2 modal">
        <div id="contentOut" class="modal-overlay"></div>
        <div class="modal-body">
            <div id="createSubjectBox" class="create-form">
                <form action="saveUpdate" method="POST" style="width: 100%">                
                    <div class="create-form__container">
                        <div class="create-form__header">
                            <h3 class="create-form__heading">Update Question</h3>
                        </div>
        
                        <div class="create-form__form">
                            <div class="create-form__group">
                                <input type="text" class="create-form__input" placeholder="Question ID" value="${id}" autocomplete="off" required disabled>
                                <input type="hidden" name="txtQuestionID" value="${id}" />
                                
                            </div>
                            <div class="create-form__group">
                                <input type="text" class="create-form__input" placeholder="Question Title" name="txtQuestionTitle" value="${title}" required>
                            </div>
                            <div class="create-form__group" style="display: flex; flex-wrap: wrap; justify-content: space-between;">
                                <input type="hidden" name="txtSubjectID" value="${param.txtSubjectID}" />
                                <c:if test="${not empty requestScope.DETAILQUESTION}">
                                    <c:forEach var="answer" items="${requestScope.DETAILQUESTION}" varStatus="counter">
                                        <input type="radio" name="chkAnswer" value="${answer.answerID}" required 
                                               <c:if test="${answer.answerCorrect}">
                                                    checked="checked"
                                                </c:if>>
                                        <input style="width: 45%" type="text" class="create-form__input" placeholder="Answer A" name="${answer.answerID}" value="${answer.answerContent}" required>
                                        <input type="hidden" name="txtAnswer${counter.count}" value="${answer.answerID}" />
                                    </c:forEach>
                                </c:if>
                                
                            </div>
                        </div>
                                <div><p style="color: red; font-size: 1.6rem; text-align: center;">${requestScope.CREATEERROR}</p></div>
                        <div class="create-form__controls">
                            <input class="btn btn--primary" type="submit" value="Save" name="btAction" />
                            <button id="back-button2" type="button" class="btn create-form__controls__back">TRO LAI</button>
                        </div>
                    </div>
                </form>  
            </div>           
        </div>  
    </div>
    <script src="./asset/js/create.js"></script>
</body>
</html>
