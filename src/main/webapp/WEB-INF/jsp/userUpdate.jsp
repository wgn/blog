<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp" %>
<title><c:choose>
		<c:when test="${opType=='add' }">新增</c:when>
		<c:when test="${opType=='edit' }">修改</c:when>
	</c:choose>用户</title>
</head>
<body>
<%@include file="./include/head.jsp" %>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			<form name="userForm" action="${pageContext.request.contextPath }/user/db/${opType }"  method="post" >
				<table class="table table-bordered">
					<tr>
						<td>ID：</td>
						<td><input type="text" hidden="true" name="id" value="${requestScope.user.id }" /></td>
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
	document.userForm.submit();
}

</script>
</body>
</html>