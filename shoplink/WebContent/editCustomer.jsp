<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource var="db" url="jdbc:mysql://localhost:3306/customer" 
  user="root" password="root" driver="com.mysql.cj.jdbc.Driver" />
<sql:query var="rs" dataSource="${db}">
SELECT * FROM customer WHERE id = ?
<sql:param value="${param['id']}" />
</sql:query>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報編集</title>
<jsp:include page="headerMng.jsp" />
<section class="admission">
<h2>顧客情報編集</h2>
<form action="EditCustomer" method="post">
<c:forEach var="row" items="${rs.rows}">
<input type="hidden" name="id" value="${row.id}" />
<input type="text" name="name" value="${row.name}" />
<input type="text" name="address" value="${row.address}" />
<input type="tel" name="tel" value="${row.tel}" />
</c:forEach>
<input type="submit" value="登録" />
</form>
</section>
<jsp:include page="footer.jsp" />
</body>
</html>