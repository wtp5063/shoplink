<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品追加画面</title>

<jsp:include page="headerMng.jsp" />

<section class="admission">
<h2>新規商品追加</h2>
<p class="errors">${requestScope['msg']}</p>
<form action="AddProduct" method="post" enctype="multipart/form-data">
<input type="file" name="images" required>
<input type="text" name="title" placeholder="タイトル" required>
<input type="text" name="artist" placeholder="アーティスト" required>
<input type="text" name="price" placeholder="プライス" required>
<input type="submit" value="登録">
</form>
</section>

<jsp:include page="footer.jsp" />