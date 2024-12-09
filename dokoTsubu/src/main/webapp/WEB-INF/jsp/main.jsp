<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css">
<title>カンリー</title>
</head>
<body>
<h1 class="b" align="center">カンリーメイン</h1>
<p>
<c:out value="${loginUser.name}" />さん、ログイン中
<a href="Logout">ログアウト</a>
</p>
<p><a href="Main">更新</a></p>
<form action="Main" method="post">
    <input type="text" name="text">
    <select name="tag">
        <option value=""></option>
        <option value="質問">質問</option>
        <option value="回答">回答</option>
        <option value="重要なお知らせ">重要なお知らせ</option>
    </select>
    <input type="submit" value="つぶやく">
</form>
<c:forEach var="mutter" items="${mutterList}">
<p><c:out value="${mutter.userName}" />:
   <c:out value="${mutter.text}" />
   (<c:out value="${mutter.tag}" />)</p>
</c:forEach>
</body>
</html>