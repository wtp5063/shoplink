<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource var="db" dataSource="jdbc/customer" />
<sql:query var="rs" dataSource="${db}">
SELECT * FROM customer
</sql:query>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客リスト</title>
<jsp:include page="headerTop.jsp" />
<table border="1">
<tr>
<th colspan="7">顧客リスト</th>
</tr>
<c:forEach var="row" items="${rs.rows}">
<tr>
<td>${row.name}</td>
<td>${row.email}</td>
<td>${row.address}</td>
<td>${row.tel}</td>
<td>
<form action="CustomerHistory" method="post">
<input type="hidden" name="name" value="${row.id}">
<input type="submit" value="注文履歴">
</form>
</td>
<td>
<form action="EditCustomer" method="post">
<input type="hidden" name="name" value="${row.name}">
<input type="submit" value="編集">
</form>
</td>
<td>
<form action="DeleteCustomer" method="post">
<input type="hidden" name="name" value="${row.name}">
<input type="submit" value="削除">
</form>
</td>
</tr>
</c:forEach>
</table>
<jsp:include page="footer.jsp" />
</body>
</html>