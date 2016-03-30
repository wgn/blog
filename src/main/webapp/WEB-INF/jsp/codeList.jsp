<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<title>编码映射列表</title>
</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				<form action="${pageContext.request.contextPath }/code/list.action" method="post">
					查询条件：
					<table class="table table-bordered">
						<tr>
							<td>类型：</td>
							<td><select name="codeType">
									<option value=""> - -！ </option>
									<c:forEach items="${codeTypeList }" var="codeType">
										<option value="${codeType.code }" <c:if test="${selectedCodeType eq codeType.code}">selected="selected"</c:if>)>${codeType.name }</option>
									</c:forEach>
							</select></td>
							<td><input class="btn btn-default" type="submit" value="查询" /></td>
							<td><a href="${pageContext.request.contextPath }/code/add.action">新增</a></td>
						</tr>
					</table>
					编码映射列表：
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>类型</th>
								<th>编码</th>
								<th>名称</th>
								<th>上级编码</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${codeList }" var="code">
								<tr>
									<td>${code.id }</td>
									<td>${code.type }</td>
									<td>${code.code}</td>
									<td>${code.name }</td>
									<td>${code.parentId }</td>
									<td><a href="${pageContext.request.contextPath }/code/add.action?id=${code.id}">新增</a> | 
									<a href="${pageContext.request.contextPath }/code/edit.action?id=${code.id}">修改</a> | 
									<a href="${pageContext.request.contextPath }/code/delete.action?id=${code.id}">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>