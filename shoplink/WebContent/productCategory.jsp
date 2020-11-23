<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sql:setDataSource var="db" url="jdbc:mysql://localhost:3306/customer"
  user="root" password="root" driver="com.mysql.cj.jdbc.Driver" />
<sql:query var="rs" dataSource="${db}">
SELECT * FROM products WHERE artist = ?
<sql:param value="${param.category}"/>
</sql:query>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリー別ページ</title>
<jsp:include page="headerTop.jsp"/>
<div class="box">
<jsp:include page="aside.jsp" />
<main>
<div class="list">
<c:forEach var="list" items="${rs.rows}">
<div class="product">
<div class="img">
<a href="productDetails.jsp?id=${fn:escapeXml(list.id)}">
<img src="${pageContext.request.contextPath}/images/${list.images}">
</a>
</div>
<div class="description">
<p><a href="productDetails.jsp?id=${fn:escapeXml(list.id)}">${fn:escapeXml(list.title)}</a></p>
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