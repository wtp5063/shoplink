<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報入力画面</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/reset.css">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
</head>
<body>
<section class="admission">
<h1>会員情報入力</h1>
${errors}
<form action="LogIn" method="POST">
<input type="email" name="email" size="35" maxlength="35" placeholder="メールアドレス">
<input type="password" name="password" size="35" minlength="8" maxlength="15" placeholder="パスワード">
<input type="submit" value="ログイン" class="btn">
</form>
<a href="admissionCustomer.jsp"><input type="button" value="新規会員登録はこちら"></a>
</section>
</body>
</html>