<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報編集</title>
<jsp:include page="headerMng.jsp" />
<section class="admission">
<h2>顧客情報編集</h2>
<form action="EditCustomer" method="post">
<input type="hidden" name="id" value="${list.id}" />
<input type="text" name="name" value="${list.name}" />
<input type="text" name="address" value="${list.address}" />
<input type="tel" name="tel" value="${list.tel}" />
<input type="submit" value="登録" />
</form>
</section>
<jsp:include page="footer.jsp" />
</body>
</html>