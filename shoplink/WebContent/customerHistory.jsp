<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sql:setDataSource var="db" url="jdbc:mysql://localhost:3306/customer"
  user="root" password="root" driver="com.mysql.cj.jdbc.Driver"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
<jsp:include page="headerTop.jsp"/>
<div class="box">
<jsp:include page="aside.jsp" />
<main>
<div class="list">
<c:forEach var="history_list" items="${requestScope.history}">
<div class="order_detail">
<p>注文番号：${history_list.id}
<p>購入日時：${history_list.datetime}</p>
<p>合計金額：${history_list.totalAmount}</p>
</div>
<div class="product_detail">
<sql:query var="rs" dataSource="${db}">
SELECT * FROM details WHERE order_id = ?
<sql:param value="${history_list.id}" />
</sql:query>
<c:forEach var="row" items="${rs.rows}">
<p>商品ID：${row.product_id}</p>
<p>値段：${row.price}</p>
<p>個数：${row.quantity}</p>
</c:forEach>
</div>
</c:forEach>
</div>
</main>
</div>
<jsp:include page="footer.jsp" />