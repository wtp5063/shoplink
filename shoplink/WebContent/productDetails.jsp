<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource var="db" url="jdbc:mysql://localhost:3306/customer"
  user="root" password="root" driver="com.mysql.cj.jdbc.Driver"/>
<sql:query var="rs" dataSource="${db}">
SELECT * FROM products WHERE id = ?
<sql:param value="${param.id}" />
</sql:query>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細画面</title>
<jsp:include page="headerTop.jsp"></jsp:include>
<div class="box">
<jsp:include page="aside.jsp"></jsp:include>
<main>
<div class="list">
<c:forEach var="list" items="${rs.rows}">
<div class="product">
<div class="img">
<img src="${pageContext.request.contextPath}/images/${list.images}">
</div>
<div class="description">
<p>${fn:escapeXml(list.title)}</p>
<p>${fn:escapeXml(list.artist)}</p>
<c:if test="${fn:escapeXml(list.price) != 0}">
<p>${fn:escapeXml(list.price)}円</p>
<form action="CartServlet" method="post">
<input type="hidden" name="id" value="${fn:escapeXml(list.id)}">
<input type="hidden" name="images" value="${fn:escapeXml(list.images)}">
<input type="hidden" name="title" value="${fn:escapeXml(list.title)}">
<input type="hidden" name="artist" value="${fn:escapeXml(list.artist)}">
<input type="hidden" name="price" value="${fn:escapeXml(list.price)}">
<input type="number" name="quantity" value="1" min="1">
<input type="submit" value="購入">
</form>
</c:if>
<c:if test="${fn:escapeXml(list.price) == 0}">
<p>売り切れ</p>
</c:if>
<c:if test="${sessionScope.account.getAdmin() == 1}">
<form action="editProduct.jsp" method="post">
<input type="hidden" name="id" value="${param.id}">
<input type="submit" value="編集">
</form>
</c:if>
</div>
</div>
</c:forEach>
</div>
</main>
</div>
<jsp:include page="footer.jsp"></jsp:include>