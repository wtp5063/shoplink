<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報</title>
</head>
<body>
<section>
<h2>マイページ</h2>
<p><small>氏名：</small>${fn:escapeXml(account.getName())}</p>
<p><small>メールアドレス：</small>${fn:escapeXml(account.getEmail())}</p>
<p><small>住所：</small>${fn:escapeXml(account.getAddress())}</p>
<p><small>電話番号：</small>${fn:escapeXml(account.getTel())}</p>
<a href="updateAcount.jsp"><input type="button" value="会員情報編集"></a>
<form action="LogOutServlet" method="post">
<input type="submit" value="ログアウト">
</form>
</section>

</body>
</html>