<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css"> 
 .comments { 
 width:100%;/*自动适应父布局宽度*/ 
overflow:auto; 
 word-break:break-all; 
 /*在ie中解决断行问题(防止自动变为在一行显示，主要解决ie兼容问题，ie8中当设宽度为100%时，文本域类容超过一行时， 
 当我们双击文本内容就会自动变为一行显示，所以只能用ie的专有断行属性“word-break或word-wrap”控制其断行)*/ 
 } 
 </style> 

<<<<<<< HEAD
<title>...name...</title>
=======
<title>日小记</title>
>>>>>>> f3885674040c9c9bbe2419a1017c37fc9cbbee33
</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container">
		<c:if test="${admin==true }">
		<div class="row">
			<div class="col-md-8 col-md-offset-2 col-sm-12">
			<form action="${pageContext.request.contextPath }/blog/add.action" method="post">
				<table class="table table-bordered">
					<tr>
						<td style="vertical-align:middle" align="center" ><input class="btn btn-default" type="button" value="超链接"/></td>
						<td style="vertical-align:middle" align="center" ><input class="btn btn-default" type="button" value="图片"/></td>
						<td style="vertical-align:middle" align="center" ><input class="btn btn-default" type="button" value="图片"/></td>
					</tr>
				</table>
				<table class="table table-bordered">
					<tr>
						<td style="vertical-align:middle" align="center"  width="70%"><textarea rows="12" class="comments" style="height:expression((this.scrollHeight>150)?'150px':(this.scrollHeight+5)+'px');overflow:auto;" name="content" placeholder="添加内容。。。"></textarea></td>
						<td style="vertical-align:middle" align="center"  width="30%"><input class="btn btn-default" type="submit" value="保存"/></td>
						
					</tr>
				</table>
			</form>
			</div>
		</div>
		</c:if>
		<div class="row">
			<div class="col-md-8 col-md-offset-2 col-sm-12">
			<c:if test="${blogList!=null }">
				<c:forEach var="blog" items="${blogList }">
				<pre>${blog }</pre>
				</c:forEach>
			</c:if>
			</div>
		</div>
	</div>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>