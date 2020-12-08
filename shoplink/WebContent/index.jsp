<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShopLink:トップページ</title>
<jsp:include page="headerTop.jsp"/>
<div class="box">
<jsp:include page="aside.jsp" />
<main>
<div class="list">
<p>${errors}</p>
<p>${msg}</p>
<c:forEach var="list" items="${rs}">
<div class="product">
<div class="img">
<a href="ProductDetailsServlet?id=${fn:escapeXml(list.id)}">
<img src="${pageContext.request.contextPath}/images/${list.images}">
</a>
</div>
<div class="description">
<p><a href="ProductDetailsServlet?id=${fn:escapeXml(list.id)}">${fn:escapeXml(list.title)}</a></p>
<p>${fn:escapeXml(list.artist)}</p>
<p>${fn:escapeXml(list.price)}円</p>
</div>
</div>
</c:forEach>
</div>
</main>
</div>
<jsp:include page="footer.jsp" />