<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
</head>
<body>
<header class="header">
<h1><a href="/">ShopLink</a></h1>
<c:if test="${account.getAdmin() != 1}">
<a href="shoppingCart.jsp" class="cart">ショッピングカート</a>
</c:if>
<c:if test="${account.getAdmin() == 1}">
<a href="manager.jsp" class="cart">ホームに戻る</a>
</c:if>
<c:if test="${account != null}">
<a href="customerInformation.jsp" class="account">マイページ<br>(${fn:escapeXml(account.getName())}さん)</a>
</c:if>
<c:if test="${account == null}">
<a href="login.jsp" class="account">ログイン</a>
</c:if>
</header>