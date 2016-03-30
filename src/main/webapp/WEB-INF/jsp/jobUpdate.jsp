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
		<c:when test="${opType=='add' }">新增</c:when>
		<c:when test="${opType=='edit' }">修改</c:when>
	</c:choose>作业</title>
<script type="text/javascript">

function goBack(){
	window.location.href="${pageContext.request.contextPath }/job/list.action";
}
function save(){
	document.jobForm.submit();
}

</script>
</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				<form name="jobForm" action="${pageContext.request.contextPath }/job/${opType }.action" 
					method="post" enctype="multipart/form-data">
					<table class="table table-bordered">
						<tr>
							<td>ID：</td>
							<td><input type="text" hidden="true" name="jobId" value="${job.jobId }" /></td>
						</tr>
						<tr>
							<td>作业名称：</td>
							<td><input type="text" name="jobName" value="${job.jobName }" /></td>
						</tr>
						<tr>
							<td>复习周期类型：</td>
							<td><input type="text" name="jobCycleType" value="${job.jobCycleType }" /></td>
						</tr>
						<tr>
							<td>详情描述：</td>
							<td><textarea rows="3" name="jobDescription"  placeholder="你可以写一些提示给未来的自己...">${job.jobDescription }</textarea></td>
						</tr>
						<tr>
							<td>外部链接：</td>
							<td><textarea rows="3"  name="jobLink" placeholder="如果你的内容保存在其他地方...">${job.jobLink }</textarea></td>
						</tr>
						<tr>
							<td>原上传文件：</td>
							<td><input type="text" value="${job.oldFilename }" readOnly />
							</td>
						</tr>
						<tr>
							<td>上传文件：</td>
							<td><input type="file"  name="jobFile"/></td>
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