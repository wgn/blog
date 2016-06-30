<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp" %>
<title>作业中心</title>
</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				<form action="${pageContext.request.contextPath }/job/list" method="post">
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
							<td><a href="${pageContext.request.contextPath }/job/add">新增</a></td>
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
								<tr id="tr_${job.jobId }">
									<td hidden="hidden">${job.jobId }</td>
									<td>${job.jobName }</td>
									<td>${job.jobCycleType}</td>
									<td>${job.jobDescription }</td>
									<td><c:if test="${job.jobLink!=null }"><a target="_blank" href="${job.jobLink }">OPEN</a></c:if></td>
									<td>${job.jobStatus }</td>
									<td><c:if test="${job.filepath!=null }"><a target="_blank" href="/job/download/${job.filepath }">DOWNLOAD</a></c:if></td>
									<td><fmt:formatDate value="${job.currentPlanDate }" pattern="yyyy-MM-dd" /></td>
									<td><a href="${pageContext.request.contextPath }/job/add/${job.jobId}">新增</a> | 
									<a href="${pageContext.request.contextPath }/job/edit/${job.jobId}">修改</a> | 
									<a href="javascript:void(0)" onclick="deleteJob(${job.jobId})">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
<%@include file="./include/bottomResource.jsp" %>
<script type="text/javascript">
var initContextPath = "${pageContext.request.contextPath }";
var contextPath = "/";

function deleteJob(jobId){
	if (confirm("确定删除？")) {
		if(jobId){
			 $.ajax({
			 	type: "POST",
			 	url:contextPath + '/job/delete/'+jobId,
			 	contentType:"application/json;charset=utf-8", 
			    error: function(request) {
			        alert(" 发生未知错误 ");
			    },
			    success: function(data) {
			    	if(data){
			    		console.log(data);
			    		if(data.result){
			    			alert("删除成功");
			    			var deleteTr = document.getElementById("tr_" + jobId);
			    			if(deleteTr){
			    				deleteTr.parentNode.removeChild(deleteTr);
			    			}
			    		}else{
			    			alert("发生了未知错误，请联系管理员");
			    		}
					}
			    }
			}); 
		}
	}
}
$(document).ready(function() { 
	contextPath = initContextPath;
}); 
</script>
</body>
</html>