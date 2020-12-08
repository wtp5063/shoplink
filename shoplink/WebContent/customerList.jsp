<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客リスト</title>
<jsp:include page="headerMng.jsp" />
<section class="customerList">
<table>
<tr>
<th colspan="7"><h2>顧客リスト</h2>${errors}</th>
</tr>
<c:forEach var="row" items="${list}">
<tr>
<td>${row.name}</td>
<td>${row.email}</td>
<td>${row.address}</td>
<td>${row.tel}</td>
<td>
<form action="OrderHistoryServlet" method="post">
<input type="hidden" name="id" value="${row.id}">
<input type="submit" value="注文履歴">
</form>
</td>
<td>
<form action="BeforeEditServlet" method="post">
<input type="hidden" name="id" value="${row.id}">
<input type="submit" value="編集">
</form>
</td>
<td>
<form action="DeleteCustomer" method="post">
<input type="hidden" name="id" value="${row.id}">
<input type="submit" value="削除">
</form>
</td>
</tr>
</c:forEach>
</table>
</section>
<jsp:include page="footer.jsp" />
</body>
</html>