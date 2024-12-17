<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
<title>カンリー</title>
</head>
<body>
<h1 class="b" align="center">カンリーメイン</h1>
<div class="header">
    <div class="login-info">
        <c:out value="${loginUser.name}"/>さん、ログイン中
        <a href="Logout" class="logout-button">ログアウト</a>
    </div>
    <div class="update-container">
        <a href="Main" class="update-button">更新</a>
    </div>
</div>
<form action="Main" method="post" enctype="multipart/form-data">
    <input type="text" name="text" placeholder="つぶやきを入力">
    <input type="file" name="image" accept="image/*">
    <input type="submit" value="つぶやく">
</form>
<c:forEach var="mutter" items="${mutterList}">
<div class="mutter-box">
<p><c:out value="${mutter.userName}" />:
   <c:out value="${mutter.text}" />
   <form action="LikeMutter" method="post" style="display:inline;">
      <input type="hidden" name="mutterId" value="${mutter.id}">
      <input type="submit" class="like-button" value="いいね">
   </form>
   <form action="UnlikeMutter" method="post" style="display:inline;">
      <input type="hidden" name="mutterId" value="${mutter.id}">
      <input type="submit" class="unlike-button" value="いいね解除">
   </form>
   <span class="like-count">${mutter.likeCount} いいね</span>
   <c:if test="${not empty mutter.imageUrl}">
       <img src="${mutter.imageUrl}" alt="Attached Image" class="attached-image">
   </c:if>
   <!-- 削除ボタンを追加 -->
   <form action="DeleteMutter" method="post" style="display:inline;">
      <input type="hidden" name="mutterId" value="${mutter.id}">
      <input type="submit" class="delete-button" value="削除">
   </form>
</p>
    <!-- コメント表示とコメント投稿フォームを追加 -->
    <div class="comment-box">
        <c:forEach var="comment" items="${mutter.comments}">
            <p>-- <c:out value="${comment.userName}" />: <c:out value="${comment.text}" /></p>
        </c:forEach>
        <form action="PostComment" method="post">
            <input type="hidden" name="mutterId" value="${mutter.id}">
            <input type="text" name="commentText" class="comment-input" placeholder="コメントを追加">
            <input type="submit" class="comment-button" value="コメント">
        </form>
    </div>
</div>
</c:forEach>
<c:if test="${not empty errorMsg}">
    <p style="color: red;">${errorMsg}</p>
</c:if>
</body>
</html>
