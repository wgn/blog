<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp" %>
<title>登陆权限设置</title>
</head>
<body>
<%@include file="./include/head.jsp" %>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			<form name="userLoginAuthForm" action="${pageContext.request.contextPath }/user/db/loginSetting"  method="post" >
				<input type="hidden" readonly="readonly" name="id" value="${loginAuth.id }" />
				<table class="table table-bordered">
					<tr>
						<td>用户ID：</td>
						<td><input class="form-control" type="text" readonly="readonly" name="userId" value="${requestScope.user.id }" /></td>
					</tr>
					<tr>
						<td>昵称：</td>
						<td>${requestScope.user.nickname }</td>
					</tr>
					<tr>
						<td>用户名：</td>
						<td><input class="form-control" type="text" name="username" value="${loginAuth.username }" /></td>
					</tr>
					<c:if test="${loginAuth!=null }">
						<tr>
							<td>当前密码：</td>
							<td>${loginAuth.password }</td>
						</tr>
					</c:if>
					<tr>
						<td>密码：</td>
						<td><input class="form-control" type="password" name="password" value="${loginAuth.password }" /></td>
					</tr>
					<tr>
						<td>状态：</td>
						<td>
							<select class="form-control" name="status">
								<option value="true" <c:if test="${loginAuth.status==true }">selected="selected"</c:if> >有效</option>
								<option value="false" <c:if test="${loginAuth.status==false }">selected="selected"</c:if> >无效</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><input class="btn btn-default" type="button" value="返回" onclick="goBack();"/></td>
						<td style="vertical-align:middle" align="center"><input class="btn btn-default" type="button" value="保存" onclick="save();"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<hr/>
<%@include file="./include/bottomResource.jsp" %>
<script type="text/javascript">

function goBack(){
	window.location.href="${pageContext.request.contextPath }/user/view/list";
}
function save(){
	document.userLoginAuthForm.submit();
}

</script>
</body>
</html>