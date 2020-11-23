<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート</title>
<jsp:include page="headerTop.jsp"/>
<div class="box">
<jsp:include page="aside.jsp" />
<main>
<div class="list">
<p class="errors">${requestScope.msg}</p>
<c:forEach var="item" items="${sessionScope.cart.product}">
<p>${item.images}</p>
<p>${item.title}</p>
<p>${item.artist}</p>
<p>値段：${item.price}</p>
<p>個数：${item.quantity}</p>
</c:forEach>
</div>
</main>
</div>
<jsp:include page="footer.jsp" />