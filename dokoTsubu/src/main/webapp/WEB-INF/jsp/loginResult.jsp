<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User"%>
    <%
    //セッションスコープからユーザー情報を取得
    User loginUser = (User)session.getAttribute("loginUser");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css">
<title>カンリー</title>
</head>
<body>
<h1 class="b" align="center">カンリーログイン</h1>
<% if(loginUser != null) { %>
<p align="center">ログインに成功しました</p>
<p align="center">ようこそ<%= loginUser.getName() %>さん</p>
<a href="Main">カンリー投稿・閲覧へ</a>
<% } else { %>
<p>ログインに失敗しました</p>
<a href="index.jsp">TOPへ</a>
<% } %>
</body>
</html>