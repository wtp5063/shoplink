<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside>
  <section>
    <h2>キーワード検索</h2>
    <form action="SearchProductServlet" method="post">
      <input type="text" name="search" size="25" value="${param.search}">
      <input type="submit" value="検索">
    </form>
  </section>
  <section>
    <h2>カテゴリー検索</h2>
    <form action="ProductCategoryServlet" method="post">
      <select name="category">
        <c:choose>
          <c:when test="${empty param.category}">
            <option value='' disabled selected>選択してください</option>
          </c:when>
          <c:otherwise>
            <option value='' disabled>選択してください</option>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${param.category == 'Led Zeppelin'}">
            <option value="Led Zeppelin" selected>LedZeppelin</option>
          </c:when>
          <c:otherwise>
            <option value="Led Zeppelin">LedZeppelin</option>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${param.category == 'Black Sabbath'}">
            <option value="Black Sabbath" selected>BlackSabbath</option>
          </c:when>
          <c:otherwise>
            <option value="Black Sabbath">BlackSabbath</option>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${param.category == 'Deep Purple'}">
            <option value="Deep Purple" selected>DeepPurple</option>
          </c:when>
          <c:otherwise>
            <option value="Deep Purple">DeepPurple</option>
          </c:otherwise>
        </c:choose>

      </select> <input type="submit" value="検索">
    </form>
  </section>
</aside>