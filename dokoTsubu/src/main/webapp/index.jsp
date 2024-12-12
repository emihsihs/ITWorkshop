<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css">
<title>カンリー</title>
</head>
<body>
<h1 class="b" align="center">カンリーへようこそ</h1>
<div class="main">
<form  action="Login" method="post">
<p class="sign" align="center">Login</p>
<input type="text" name="name" class="un " type="text"  placeholder="Username"><br>
<input type="password" name="pass" class="pass " type="password"  placeholder="Password"><br>
<input type="submit" id="submit" value="ログイン" class="submit" >
</form>
<p align="center"><a href="Register">新規会員登録はこちら</a></p>
</div>
</body>
</html>