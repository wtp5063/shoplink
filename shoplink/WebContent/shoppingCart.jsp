<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート</title>
<jsp:include page="headerTop.jsp"/>
<div class="box">
<jsp:include page="aside.jsp" />
<main>
<div class="list">
<p class="errors">${requestScope.msg}</p>
<c:forEach var="item" items="${sessionScope.cart.product}">
<p>商品ID：${item.images}</p>
<p>タイトル：${item.title}</p>
<p>アーティスト：${item.artist}</p>
<p>値段：${item.price}</p>
<form action="EditQuantityServlet" method="post">
<label for="quantity">個数：</label>
<input type="hidden" name="id" value="${item.id}">
<input type="number" name="quantity" value="${item.quantity}" min="1" id="quantity">
<input type="submit" value="数量変更">
</form>
<form action="DeleteCartServlet" method="post">
<input type="hidden" name="id" value="${item.id}">
<input type="hidden" name="price" value="${item.price}">
<input type="hidden" name="quantity" value="${item.quantity}">
<input type="submit" value="削除">
</form>
</c:forEach>
<div class="cart_total">
<p>小計：${fn: escapeXml(sessionScope.cart.subTotal)}</p>
<p>消費税：${fn: escapeXml(sessionScope.cart.taxAmount)}</p>
<p>送料：${fn: escapeXml(sessionScope.cart.shipping)}</p>
<p>合計金額：${fn: escapeXml(sessionScope.cart.total)}</p>
</div>
</div>
</main>
</div>
<jsp:include page="footer.jsp" />