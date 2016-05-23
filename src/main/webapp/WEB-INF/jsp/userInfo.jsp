<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp" %>
<title>个人中心</title>
</head>
<body>
<%@include file="./include/head.jsp" %>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			<form name="userForm" action="${pageContext.request.contextPath }/u/updateInfo"  method="post" >
				<br/>
				<div><label>用户信息</label></div>
				<br/>
				<table class="table table-bordered">
					<tr>
						<td>ID：</td>
						<td>${requestScope.user.id }</td>
					</tr>
					<tr>
						<td>昵称：</td>
						<td><input class="form-control" type="text" name="nickname" value="${requestScope.user.nickname }" /></td>
					</tr>
					<tr>
						<td>性别：</td>
						<td><input class="form-control" type="text" name="sex" value="${requestScope.user.sex }" /></td>
					</tr>
					<tr>
						<td>生日：</td>
						<td><input class="form-control" type="text" name="birthday" value='<fmt:formatDate value="${requestScope.user.birthday}" pattern="yyyy-MM-dd" />' /></td>
					</tr>
					<tr>
						<td>地址：</td>
						<td><textarea class="form-control" name="address" >${requestScope.user.address }</textarea></td>
					</tr>
					<tr>
						<td>Email：</td>
						<td><input class="form-control" type="text" name="email" value="${requestScope.user.email }" /></td>
					</tr>
					<tr>
						<td>电话：</td>
						<td><input class="form-control" type="text" name="mobile" value="${requestScope.user.mobile }" /></td>
					</tr>
					<tr>
						<td><input class="btn btn-default" type="button" value="返回" onclick="goBack();"/></td>
						<td style="vertical-align:middle" align="center"><input class="btn btn-default" type="button" value="保存" onclick="save();"/></td>
					</tr>
				</table>
				<input type="text" hidden="true" name="id" value="${requestScope.user.id }" />
			</form>
		</div>
	</div>
	<hr/>
	<div class="row">
		<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			<form name="userMimiForm" action="${pageContext.request.contextPath }/u/updateMimiInfo"  method="post" >
				<br/>
				<div><label>登陆信息</label></div>
				<br/>
				<table class="table table-bordered">
					
					<tr>
						<td>用户名：</td>
						<td><input class="form-control" type="text" id="username" name="username" value="${requestScope.userMimi.username }" /></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><span hidden="true">${requestScope.userMimi.password }</span></td>
					</tr>
					<tr>
						<td>新密码：</td>
						<td><input class="form-control" type="text" id="password" name="password" value="" /></td>
					</tr>
					<tr>
						<td><input class="btn btn-default" type="button" value="返回" onclick="goBack();"/></td>
						<td style="vertical-align:middle" align="center"><input class="btn btn-default" type="button" value="保存" onclick="saveMimi();"/></td>
					</tr>
				</table>
				<input type="text" hidden="true" name="userId" value="${requestScope.userMimi.userId }" />
				<input type="text" hidden="true" name="id" value="${requestScope.userMimi.id }" />
			</form>
		</div>
	</div>
</div>
<hr/>
<%@include file="./include/bottomResource.jsp" %>
<script type="text/javascript">
var msg="${msg }";
if(""!=msg){
	alert(msg);
}

function goBack(){
	window.location.href="${pageContext.request.contextPath }/blog/index";
}
function save(){
	document.userForm.submit();
}
function saveMimi(){
	if(!document.getElementById("username").value || !document.getElementById("password").value){
		alert("用户名密码不能为空哇");
		return ;
	}
	document.userMimiForm.submit();
}

</script>
</body>
</html>