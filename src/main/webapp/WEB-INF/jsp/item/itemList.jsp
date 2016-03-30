<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<title>查询商品列表</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				<form action="${pageContext.request.contextPath }/item/queryItem.action" method="post">
					查询条件：
					<table class="table table-bordered">
						<tr>
							<td><input type="submit" value="查询" /></td>
						</tr>
					</table>
					商品列表：
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>商品名称</th>
								<th>商品价格</th>
								<th>生产日期</th>
								<th>商品描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${itemsList }" var="item">
								<tr>
									<td>${item.name }</td>
									<td>${item.price }</td>
									<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${item.detail }</td>
									<td><a href="${pageContext.request.contextPath }/item/editItem.action?id=${item.id}">修改</a></td>
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