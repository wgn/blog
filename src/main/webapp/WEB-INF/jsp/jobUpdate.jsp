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
	</c:choose>作业</title>
</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				<form name="jobForm" action="${pageContext.request.contextPath }/job/${opType }" 
					method="post" enctype="multipart/form-data">
					<table class="table table-bordered">
						<tr>
							<td>ID：</td>
							<td><input type="text" hidden="true" name="jobId" value="${job.jobId }" /></td>
						</tr>
						<tr>
							<td>作业名称：</td>
							<td><input type="text" class="form-control" name="jobName" value="${job.jobName }" /></td>
						</tr>
						<tr>
							<td>复习周期类型：</td>
							<td><input type="text" class="form-control" name="jobCycleType" value="${job.jobCycleType }" /></td>
						</tr>
						<tr>
							<td>详情描述：</td>
							<td><textarea rows="3" class="form-control" name="jobDescription"  placeholder="你可以写一些提示给未来的自己...">${job.jobDescription }</textarea></td>
						</tr>
						<tr>
							<td>外部链接：</td>
							<td><textarea rows="3" class="form-control"  name="jobLink" placeholder="如果你的内容保存在其他地方...">${job.jobLink }</textarea></td>
						</tr>
						<tr>
							<td>原上传文件：</td>
							<td><input type="text" class="form-control" id="oldJobFile" name="oldJobFile" value="${job.oldFilename }" readOnly />
							</td>
						</tr>
						<tr>
							<td>上传文件：</td>
							<td><input type="file" class="form-control" id="jobFile"  name="jobFile"/></td>
						</tr>
						<tr>
							<td><input class="btn btn-default" type="button" onclick="goBack();" value="返回" /></td>
							<td style="vertical-align:middle" align="center"><input class="btn btn-default" type="button" value="保存" onclick="save();"/></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
<%@include file="./include/bottomResource.jsp" %>
<script type="text/javascript">

function goBack(){
	window.location.href="${pageContext.request.contextPath }/job/list";
}
function save(){
	if($("#oldJobFile").val() && $("#jobFile").val()){
		if(confirm("确定替换文件？")){
			document.jobForm.submit();
		}
		return ;
	}
	document.jobForm.submit();
}
</script>
</body>
</html>