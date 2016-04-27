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
				你好！${user.nickname}<br/>
				<a href="code/list">编码设置</a><br/>
				<a href="job/list">复习管理</a><br/>
				<a href="user/view/list">用户管理</a><br/>
			</div>
		</div>
	</div>
	<%@include file="./include/bottomResource.jsp" %>
</body>
</html>