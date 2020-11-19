<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報入力画面</title>
</head>
<body>
<section class="admisson">
<h1>会員情報入力</h1>
<p class="error">${param['errors']}</p>
<form action="InsertCustomer" method="POST">
<table>
<tr>
<th align="right">名前：</th>
<td><input type="text" name="name" size="35" maxlength="15"></td>
</tr>
<tr>
<th align="right">メールアドレス：</th>
<td><input type="email" name="email" size="35" maxlength="35"></td>
</tr>
<tr>
<th align="right">パスワード<small>(８文字以上)</small>：</th>
<td><input type="password" name="password" size="35" minlength="8" maxlength="15"></td>
</tr>
<tr>
<th align="right">住所：</th>
<td><input type="text" name="address" size="35" maxlength="25"></td>
</tr>
<tr>
<th align="right">電話番号：</th>
<td><input type="tel" name="tel" size="35" maxlength="15"></td>
</tr>
<tr>
<td><input type="submit" value="登録"></td>
</tr>
</table>
</form>
</section>
</body>
</html>