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
<title>商品編集画面</title>
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
<form action="EditProductServlet" method="post">
<p></p>
<input type="hidden" name="id" value="${param.id}">
<input type="text" name="title" value="${fn:escapeXml(list.title)}">
<input type="text" name="artist" value="${fn:escapeXml(list.artist)}">
<input type="text" name="price" value="${fn:escapeXml(list.price)}">
<input type="submit" value="登録">
</form>
<form action="SoldOutServlet" method="post">
<input type="hidden" name="id" value="${list.id}">
<input type="hidden" name="price" value="${list.price}">
<input type="submit" value="売り切れ">
</form>
<form action="DeleteProductServlet" method="post">
<input type="hidden" name="id" value="${list.id}">
<input type="hidden" name="images" value="${list.images}">
<input type="submit" value="削除">
</form>
</div>
</div>
</c:forEach>
</div>
</main>
</div>
<jsp:include page="footer.jsp"></jsp:include>