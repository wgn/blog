<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<title><c:choose>
		<c:when test="${opType=='add' }">增加</c:when>
		<c:when test="${opType=='edit' }">修改</c:when>
	</c:choose>编码映射</title>
<script type="text/javascript">

function goBack(){
	//window.history.back(-1);
	window.location.href="${pageContext.request.contextPath }/code/list.action";
	//window.navigate("b.html");
	//self.location=’b.html’;
	//top.location=’b.html’;
}
function save(){
	document.codeForm.submit();
}

</script>
</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container">
		<c:if test="${validationErrors != null }">
			<div class="row">
				<div class="col-xs-8 col-md-offset-2">
					<c:forEach items="${validationErrors}" var="error">
						<font color="red">${error.defaultMessage }</font>
					</c:forEach>
				</div>
			</div>
			<hr/>
		</c:if>
		<c:if test="${baseCodeTypeError !=null }">
			<div class="row">
				<div class="col-xs-8 col-md-offset-2">
					<font color="red">${baseCodeTypeError }</font>
				</div>
			</div>
			<hr/>
		</c:if>
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				<form name="codeForm" action="${pageContext.request.contextPath }/code/${opType }.action" method="post">
					<table class="table table-bordered">
						<tr>
							<td>ID：</td>
							<td><input type="text" hidden="true" name="id" value="${code.id }" /></td>
						</tr>
						<tr>
							<td>类型：</td>
							<td>							
								<input type="text" name="type" value="${code.type }" />
							</td>
						</tr>
						<tr>
							<td>编码：</td>
							<td><input type="text" name="code" value="${code.code }" /></td>
						</tr>
						<tr>
							<td>名称：</td>
							<td><input type="text" name="name" value="${code.name }" /></td>
						</tr>
						<tr>
							<td>上级编码：</td>
							<td><input type="text" name="parentId" value="${code.parentId }" /></td>
						</tr>
						<tr>
							<td><input class="btn btn-default" type="button" value="保存" onclick="save();"/></td>
							<td><input class="btn btn-default" type="button" onclick="goBack();" value="返回" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>