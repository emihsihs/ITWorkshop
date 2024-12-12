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
<h1 class="b" align="center">会員登録に失敗しました</h1>
<a href="<%=request.getContextPath() %>/Register">再試行</a> <!-- サーブレット経由でリダイレクト -->
</body>
</html>

