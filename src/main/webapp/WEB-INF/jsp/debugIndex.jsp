<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./include/headResource.jsp" %>
<style type="text/css"> 
img{
	max-width:100%;
}
 .comments { 
 width:100%;/*自动适应父布局宽度*/ 
overflow:auto; 
 word-break:break-all; 
 /*在ie中解决断行问题(防止自动变为在一行显示，主要解决ie兼容问题，ie8中当设宽度为100%时，文本域类容超过一行时， 
 当我们双击文本内容就会自动变为一行显示，所以只能用ie的专有断行属性“word-break或word-wrap”控制其断行)*/ 
 }

 </style> 

<title>Debug Index</title>

</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div id="result" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
					
			<c:if test="${debugInfoVO != null }">
			系统属性列表：
			<table class="table table-bordered">
				<thead><tr><th>名</th><th>值</th></tr></thead>
				<tbody>
				<c:forEach var="prop" items="${debugInfoVO.props }">
					<tr><td>${prop.key }</td><td>${prop.value }</td></tr>
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
			<div id="result" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
					
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
<%@include file="./include/bottomResource.jsp" %>
</body>
</html>