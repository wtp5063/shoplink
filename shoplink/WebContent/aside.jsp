<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside>
<section>
<h2>キーワード検索</h2>
<form action="SerchServlet" method="get">
<input type="text" name="search" size="25">
<input type="submit">
</form>
</section>
<section>
<h2>カテゴリー</h2>
<form action="CategoryServlet" method="get">
<select name="category" onchange="submit(this.form)">
<option value="Led Zeppelin">LedZeppelim</option>
<option value="Black Sabbath">BlackSabbath</option>
<option value="Deep Purple">DeepPurple</option>
</select>
</form>
</section>
</aside>