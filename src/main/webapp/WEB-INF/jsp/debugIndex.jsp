<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp"%>
<style type="text/css">
img {
	max-width: 100%;
}

.comments {
	width: 100%; /*自动适应父布局宽度*/
	overflow: auto;
	word-break: break-all;
	/*在ie中解决断行问题(防止自动变为在一行显示，主要解决ie兼容问题，ie8中当设宽度为100%时，文本域类容超过一行时， 
 当我们双击文本内容就会自动变为一行显示，所以只能用ie的专有断行属性“word-break或word-wrap”控制其断行)*/
}
</style>

<title>Debug Index</title>

</head>
<body>
	<%@include file="./include/head.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">

				<c:if test="${debugInfoVO != null }">
			系统属性列表：
			<table class="table table-bordered">
						<thead>
							<tr>
								<th>名</th>
								<th>值</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="prop" items="${debugInfoVO.props }">
								<tr>
									<td>${prop.key }</td>
									<td>${prop.value }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<hr />
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">

				<c:if test="${sysProperties != null }">
			sys.properties 配置：
			<pre>
				${sysProperties }
			</pre>
				</c:if>
			</div>
		</div>
	</div>
	<hr />
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
				修改sys.properties属性：
				<form name="updateSysPropForm" action="${pageContext.request.contextPath }/debug/update" method="post">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td><input type="text" class="form-control" id="updatePropertyName" name="propertyName" placeholder="属性名" /></td>
								<td><input type="text" class="form-control" id="updatePropertyValue" name="propertyValue" placeholder="属性值" /></td>
								<td><input type="button"  class="btn btn-default" onclick="update();" value="修改" /></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<hr />
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
				新增sys.properties属性：
				<form name="addSysPropForm" action="${pageContext.request.contextPath }/debug/add" method="post">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td><input type="text" class="form-control" id="addPropertyName" name="propertyName" placeholder="属性名" /></td>
								<td><input type="text" class="form-control" id="addPropertyValue" name="propertyValue" placeholder="属性值" /></td>
							</tr>
							<tr><td colspan="2" rowspan="3">
								<textarea rows="2" cols="3" class="form-control" name="comments" placeholder="增加属性一定要写清楚注释！"></textarea>
							</td><td><input type="button"  class="btn btn-default" onclick="add();" value="新增" /></td></tr>
							
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<hr />
	<%@include file="./include/bottomResource.jsp"%>
	<script type="text/javascript">
	function update() {
		if ($("#updatePropertyName").val() && $("#updatePropertyValue").val()) {
			if (confirm("确定修改属性？")) {
				document.updateSysPropForm.submit();
			}
			return;
		}
	}
	function add() {
		if ($("#addPropertyName").val() && $("#addPropertyValue").val()) {
			if (confirm("确定增加属性？")) {
				document.addSysPropForm.submit();
			}
			return;
		}
	}
	</script>
</body>
</html>