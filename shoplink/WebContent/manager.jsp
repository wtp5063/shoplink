<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ページ</title>
<jsp:include page="headerMng.jsp" />
<div class="box">
<jsp:include page="aside.jsp" />
<main>
<div class="list">
<p>${msg}</p>
<c:forEach var="row" items="${list}">
<div class="product">
<div class="img">
<a href="ProduceDetailsServlet?id=${fn:escapeXml(row.id)}">
<img src="${pageContext.request.contextPath}/images/${row.images}">
</a>
</div>
<div class="description">
<p><a href="ProductDetailsServlet?id=${fn:escapeXml(row.id)}">${fn:escapeXml(row.title)}</a></p>
<form action="CategoryServlet" method="post">
<p>${fn:escapeXml(row.artist)}</p>
</form>
<p>${fn:escapeXml(row.price)}円</p>
</div>
</div>
</c:forEach>
</div>
</main>
</div>
<jsp:include page="footer.jsp" />