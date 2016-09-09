<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp"%>
<link href="${pageContext.request.contextPath }/libs/bootstrap_datepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<title>作业</title>
</head>
<body>
	<%@include file="./include/head.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				<form name="jobForm" action="${pageContext.request.contextPath }/job/progress" method="post"
					enctype="multipart/form-data">
					<table class="table table-bordered">
						<tr>
							<td>ID：</td>
							<td><input type="text" hidden="true" name="jobId" value="${job.jobId }" />${job.jobId }</td>
						</tr>
						<tr>
							<td>作业名称：</td>
							<td>${job.jobName }</td>
						</tr>
						<tr>
							<td>详情描述：</td>
							<td><textarea rows="3" readOnly="readonly" class="form-control">${job.jobDescription }</textarea></td>
						</tr>
						<tr>
							<td>作业链接：</td>
							<td><a target="_blank" href="${job.jobLink }">打开链接</a></td>
						</tr>
						<tr>
							<td>复习类型：</td>
							<td><c:choose>
									<c:when test="${jobTrace.jobCycleType=='review' }">周期复习</c:when>
									<c:when test="${jobTrace.jobCycleType=='reread' }">远期重读</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>进度：</td>
							<td>${job.jobStatus }</td>
						</tr>
						<tr>
							<td>本次时间：</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${jobTrace.planTime}" /></td>
						</tr>
						<tr>
							<td>本次完成时间：</td>
							<td><input id="datetimepicker" name="finishTime" type="text"/></td>
						</tr>
						<tr>
							<td>本次注释：</td>
							<td><textarea rows="3" class="form-control" name="comment" placeholder="你可以写一些提示给未来的自己..."></textarea></td>
						</tr>
						
						<tr>
							<td><input class="btn btn-default" type="button" onclick="goBack();" value="返回" /></td>
							<td style="vertical-align: middle" align="center">
							<input class="btn btn-default" type="button" value="完成本次" onclick="progress('complete');" /> &nbsp;&nbsp;&nbsp;
							<input class="btn btn-default" type="button" value="重复本次" onclick="progress('redo');" />	&nbsp;&nbsp;&nbsp;
							<input class="btn btn-default" type="button" value="重新开始" onclick="progress('restart');" />
							</td>
						</tr>
					</table>
					<input type="hidden" id="progressStatus" name="progressStatus" value=""/>
					<input type="hidden" id="jobTraceId" name="jobTraceId" value="${jobTrace.id }"/>
				</form>
			</div>
		</div>
	</div>
	<%@include file="./include/bottomResource.jsp"%>
	<script src="${pageContext.request.contextPath }/libs/bootstrap_datepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath }/libs/bootstrap_datepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript">
	$("#datetimepicker").datetimepicker({
		format: 'yyyy-mm-dd hh:ii:ss',
		language: "zh-CN",
		weekStart: 1,
		todayBtn: 1,
		autoclose: 1,
        todayHighlight: 1
	});
		
		function goBack() {
			window.location.href = "${pageContext.request.contextPath }/job/list";
		}
		function progress(action) {
			$("#progressStatus").attr("value",action);
			document.jobForm.submit();
		}
	</script>
</body>
</html>