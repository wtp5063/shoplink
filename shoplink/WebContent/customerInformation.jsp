<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報</title>
<jsp:include page="headerTop.jsp"></jsp:include>
<section class="admission">
  <h2>マイページ</h2>
  <p>氏名：${fn:escapeXml(account.getName())}</p>
  <p>email：${fn:escapeXml(account.getEmail())}</p>
  <p>住所：${fn:escapeXml(account.getAddress())}</p>
  <p>電話番号：${fn:escapeXml(account.getTel())}</p>
  <a href="updateAccount.jsp"><input type="button" value="会員情報編集"></a>
  <form action="OrderHistoryServlet" method="post">
  <input type="hidden" name="id" value="${fn:escapeXml(account.id)}">
  <input type="submit" value="購入履歴">
  </form>
  <form action="LogOutServlet" method="post">
    <input type="submit" value="ログアウト">
  </form>
</section>

<jsp:include page="footer.jsp" />