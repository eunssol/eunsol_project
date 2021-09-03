<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
		<div class="header">
            <div class="size">
                <div><img src="/pro/img/logo.png"></div>
                <div class="login">
                <c:if test="${empty userInfo }">
                    <a href="/pro/user/login.do?url=${requestScope['javax.servlet.forward.request_uri']}?<%=request.getQueryString()%>">로그인</a> |
                    <a href="/pro/user/join.do">회원가입</a>
                </c:if>    
                <c:if test="${!empty userInfo }">
                    <a href="/pro/user/logout.do">로그아웃</a> |
                    <a href="/pro/user/mypage.do">마이페이지</a>
                </c:if>    
                </div>
            </div>
        </div>
        <div class="menu">
            <ul class="depth1">
                <li><a href="">MENU1</a>
                    <ul class="depth2">
                        <li><a href="">MENU1-1</a></li>
                        <li><a href="">MENU1-2</a></li>
                        <li><a href="">MENU1-3</a></li>
                    </ul>
                </li>
                <li><a href="">MENU2</a>
                    <ul class="depth2">
                        <li><a href="">MENU2-1</a></li>
                        <li><a href="">MENU2-2</a></li>
                        <li><a href="">MENU2-3</a></li>
                    </ul>
                </li>
                <li><a href="">MENU3</a>
                    <ul class="depth2">
                        <li><a href="">MENU3-1</a></li>
                        <li><a href="">MENU3-2</a></li>
                        <li><a href="">MENU3-3</a></li>
                    </ul>
                </li>
                <li><a href="">MENU4</a>
                    <ul class="depth2">
                        <li><a href="">MENU4-1</a></li>
                        <li><a href="">MENU4-2</a></li>
                        <li><a href="">MENU4-3</a></li>
                    </ul>
                </li>
                <li><a href="">게시판</a>
                    <ul class="depth2">
                        <li><a href="/pro/board/index.do">게시판</a></li>
                        <li><a href="/pro/news/index.do">뉴스</a></li>
                        <li><a href="/pro/reply/index.do">답변게시판</a></li>
                    </ul>
                </li>
            </ul>
        </div>