<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css">
<title>新規会員登録 - カンリー</title>
</head>
<body>
<h1 class="b" align="center">新規会員登録 - カンリー</h1>
<div class="main">
<form action="Register" method="post">
<p class="sign" align="center">ユーザー名、パスワードの設定</p>
<input type="text" name="name" class="un" placeholder="Username(6∼14桁)" minlength="6" maxlength="14" required><br>
<input type="password" name="pass" class="pass" placeholder="Password(半角数字4桁)" pattern="\d{4}" required><br>
<input type="submit" id="submit" value="登録" class="submit">
</form>
<p align="center"><a href="index.jsp" class="back-button">戻る</a></p>
</div>
</body>
</html>



