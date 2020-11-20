<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ページ</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/reset.css">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
</head>
<body>
<header class="header">
<h1><a href="index.jsp">ShopLink</a></h1>
<a href="ShoppingCart.jsp" class="cart">ショッピングカート</a>
</header>
<h2>管理者ページ</h2>
<jsp:include page="footer.jsp" />