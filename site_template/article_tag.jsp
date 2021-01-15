<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% String title = request.getParameter("tag"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- lodash 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.20/lodash.min.js"></script>

<!-- vue 불러오기 -->
<script src="https://cdn.jsdelivr.net/npm/vue"></script>

<!-- jquery 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<div class="main con-min-width visible-md-up" id="articleTagList">
	<div class="con">
		<div class="article-list content">
			<ul class="flex">
				<li>번호</li>
				<li>작성일</li>
				<li>작성자</li>
				<li><a>제목</a></li>
				<li>조회수</li>
				<li>댓글수</li>
				<li>추천수</li>
			</ul>
			<ul v-for="article in filtered" class="flex">
				 <li>{{article.id}}</li>
	             <li>{{article.regDate}}</li>
	             <li>{{article.writer}}</li>
	             <li>
	               <a :href="'article_detail_' + article.id + '.html'" target="_blank">{{article.title}}</a>
            	 </li>
            	 <li>{{article.hit}}</li>
	             <li>{{article.commentsCount}}</li>
	             <li>{{article.likesCount}}</li>	             
			</ul>
		</div>
	</div>
</div>

<script src="article_tag.js">
</script>
</body>
</html>