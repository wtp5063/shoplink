<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細検索画面</title>
<jsp:include page="headerTop.jsp"/>
<div class="box">
<jsp:include page="aside.jsp" />
<main>
<div class="list">
<p>${msg}</p>
<c:forEach var="list" items="${product}">
<div class="product">
<div class="img">
<a href="ProductDetailsServlet?id=${fn:escapeXml(list.id)}">
<img src="${pageContext.request.contextPath}/images/${list.images}">
</a>
</div>
<div class="description">
<p><a href="ProductDetailsServlet?id=${fn:escapeXml(list.id)}">${fn:escapeXml(list.title)}</a></p>
<form action="CategoryServlet" method="post">
<p>${fn:escapeXml(list.artist)}</p>
</form>
<p>${fn:escapeXml(list.price)}円</p>
</div>
</div>
</c:forEach>
</div>
</main>
</div>
<jsp:include page="footer.jsp" />