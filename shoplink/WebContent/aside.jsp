<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside>
<section>
<h2>キーワード検索</h2>
<form action="searchProduct.jsp" method="get">
<input type="text" name="search" size="25">
<input type="submit" value="検索">
</form>
</section>
<section>
<h2>カテゴリー検索</h2>
<form action="productCategory.jsp" method="get">
<select name="category">
<option value="Led Zeppelin">LedZeppelim</option>
<option value="Black Sabbath">BlackSabbath</option>
<option value="Deep Purple">DeepPurple</option>
</select>
<input type="submit" value="検索">
</form>
</section>
</aside>