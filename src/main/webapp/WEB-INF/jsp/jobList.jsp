<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<title>作业中心</title>
</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				<form action="${pageContext.request.contextPath }/job/list.action" method="post">
					查询条件：
					<table class="table table-bordered">
						<tr>
							<td>类型：</td>
							<td><select name="codeType">
									<c:forEach items="${jobTypeSet }" var="jobType">
										<option value="${jobType }">${jobType }</option>
									</c:forEach>
							</select></td>
							<td><input type="submit" value="查询" /></td>
							<td><a href="${pageContext.request.contextPath }/job/add.action">新增</a></td>
						</tr>
					</table>
					作业列表：
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th hidden="hidden">ID</th>
								<th >作业名称</th>
								<th >复习周期类型</th>
								<th >详情描述</th>
								<th >外部链接</th>
								<th >进度</th>
								<th >下载作业</th>
								<th >当前进度日期</th>
								<th ></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${jobList }" var="job">
								<tr>
									<td hidden="hidden">${job.jobId }</td>
									<td>${job.jobName }</td>
									<td>${job.jobCycleType}</td>
									<td>${job.jobDescription }</td>
									<td><c:if test="${job.jobLink!=null }"><a target="_blank" href="${job.jobLink }">GOGOGO</a></c:if></td>
									<td>${job.jobStatus }</td>
									<td><c:if test="${job.filepath!=null }"><a target="_blank" href="/filedir/${job.filepath }">DOWNLOAD</a></c:if></td>
									<td></td>
									<td><a href="${pageContext.request.contextPath }/job/add.action?id=${job.jobId}">新增</a> | 
									<a href="${pageContext.request.contextPath }/job/edit.action?id=${job.jobId}">修改</a> | 
									<a href="${pageContext.request.contextPath }/job/delete.action?id=${job.jobId}">修改</a></td>
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