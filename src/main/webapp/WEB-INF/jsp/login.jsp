<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp"%>
<style type="text/css">
.loginDiv {
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -150px 0 0 -200px;
	width: 400px;
	height: 300px;
}
.form-control{
font-size: large;
}
</style>
<title>登陆</title>
</head>
<body>
	<div class="container loginDiv">
		<div class="row">
			<div class="col-md-8 col-md-offset-2 col-sm-12 ">
				<form name="loginForm" action="${pageContext.request.contextPath }/login" method="post">
					<table class="table table-bordered">
						<tr>
							<td><input class="form-control"  placeholder="Username" type="text" id="username" name="username" value="${username }" /></td>
						</tr>
						<tr>
							<td><input class="form-control" placeholder="Password" type="password" id="password" name="password" /></td>
						</tr>
						<tr>
							<td align="center"><input class="btn btn-default" type="button" onclick="login();" value="登陆"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-md-offset-2 col-sm-12" align="right">
				<c:if test="${loginErrorMsg!=null}">
					<font color="red">${loginErrorMsg }</font>
				</c:if>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	function login(){
		if(!document.getElementById("username").value || !document.getElementById("password").value){
			alert("用户名密码不能为空哇");
			return ;
		}
		document.loginForm.submit();
	}
	</script>
</body>
</html>