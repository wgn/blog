<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp" %>
<title>登陆成功</title>
</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				你好！${sessionScope.session_login_user.nickname}<br/><hr/><br/>
				<table class="table table-bordered">
					<tr><td><a href="code/list">编码设置</a></td></tr>
					<tr><td><a href="job/list">复习管理</a></td></tr>
					<tr><td><a href="user/view/list">用户管理</a></td></tr>
					<tr><td><a href="blog/index">日小记</a></td></tr>
					<tr><td><a href="monitoring">监控</a></td></tr>
					<tr><td><a href="dbaction/tableList">SQLite</a><br/>
					<tr><td><a href="debug/index">Debug Index</a><br/>
				</tr>
				</table>
			</div>
		</div>
	</div>
	<%@include file="./include/bottomResource.jsp" %>
</body>
</html>