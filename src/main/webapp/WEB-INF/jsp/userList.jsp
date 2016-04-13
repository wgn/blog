<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp" %>
<style type="text/css">
.th1{width: 3%}
.th2{width: 10%}
.th_sex{width: 5%}
.th4{width: 10%}
.th_address{width: 20%;}
.th6{width: 10%}
.th7{width: 10%}
.th8{width: 10%}
.th9{width: 10%}
</style>
<title>用户列表</title>
</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
				<form action="${pageContext.request.contextPath }/user/view/list" method="post">
					查询条件：
					<table class="table table-bordered">
						<tr>
							<td>类型：</td>
							<td><select name="userType">
									<option value=""> - -！ </option>
									<c:forEach items="${codeTypeList }" var="codeType">
										<option value="${codeType.code }" <c:if test="${selectedCodeType eq codeType.code}">selected="selected"</c:if>)>${codeType.name }</option>
									</c:forEach>
							</select></td>
							<td><input class="btn btn-default" type="submit" value="查询" /></td>
							<td><a href="${pageContext.request.contextPath }/user/view/add">新增</a></td>
						</tr>
					</table>
					用户列表：
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th class="th1">ID</th>
								<th class="th2">昵称</th>
								<th class="th_sex">性别</th>
								<th class="th4">生日</th>
								<th class="th_address">地址</th>
								<th class="th6">Email</th>
								<th class="th7">电话</th>
								<th class="th8">登陆</th>
								<th class="th9">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList }" var="user">
								<tr>
									<td>${user.id }</td>
									<td>${user.nickname }</td>
									<td>${user.sex}</td>
									<td><fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/> </td>
									<td>${user.address }</td>
									<td>${user.email }</td>
									<td>${user.mobile }</td>
									<td><a href="${pageContext.request.contextPath }/user/view/loginSetting/${user.id}">登陆设置</a></td>
									<td><a href="${pageContext.request.contextPath }/user/view/copy/${user.id}">新增</a> | 
									<a href="${pageContext.request.contextPath }/user/view/edit/${user.id}">修改</a> | 
									<a href="${pageContext.request.contextPath }/user/db/delete/${user.id}">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<hr />
</body>
</html>