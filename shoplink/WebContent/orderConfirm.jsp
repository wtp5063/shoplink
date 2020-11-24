<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文確認画面</title>
<jsp:include page="headerTop.jsp"/>
<div class="box">
<jsp:include page="aside.jsp" />
<main>
<div class="list">
<div class="customer_information">
<p>氏名：${sessionScope.account.name}</p>
<p>Eメール：${sessionScope.account.email}</p>
<p>住所：${sessionScope.account.address}</p>
<p>電話番号：${sessionScope.account.tel}</p>
</div>
<c:forEach var="item" items="${sessionScope.cart.product}">
<p>商品ID：${item.images}</p>
<p>タイトル：${item.title}</p>
<p>アーティスト：${item.artist}</p>
<p>値段：${item.price}</p>
</c:forEach>
<div class="cart_total">
<p>小計：${fn: escapeXml(sessionScope.totalAmount.subTotal)}</p>
<p>消費税：${fn: escapeXml(sessionScope.totalAmount.taxAmount)}</p>
<p>送料：${fn: escapeXml(sessionScope.totalAmount.shipping)}</p>
<p>合計金額：${fn: escapeXml(sessionScope.totalAmount.totalAmount)}</p>
<form action="OrderServlet" method="post">
<input type="submit" value="注文">
</form>
</div>
</div>
</main>
</div>
<jsp:include page="footer.jsp" />