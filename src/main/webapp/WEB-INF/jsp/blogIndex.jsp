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
 .bot_msg {
    color: #CF4F4F;
    font-weight: bold;
    text-align: center;
    font-size: large;
    padding: 11px;
    word-break: break-word;
    margin: 0;
    display: none;
    -webkit-margin-before: 1em;
    -webkit-margin-after: 1em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
 }
 pre{
	font-size:1em;
	background-color: rgba(15, 144, 29, 0.15);
	white-space: pre-wrap;       
	white-space: -moz-pre-wrap;  
	white-space: -pre-wrap;      
	white-space: -o-pre-wrap;    
	word-wrap: break-word;       
 }
 </style> 

<title>日小记</title>

</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container-fluid">
		<c:if test="${admin==true }">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			<form>
				<!-- <table class="table table-bordered">
					<tr>
						<td style="vertical-align:middle" align="center" ><input class="btn btn-default" type="button" value="超链接"/></td>
						<td style="vertical-align:middle" align="center" ><input class="btn btn-default" type="button" value="图片"/></td>
						<td style="vertical-align:middle" align="center" ><input class="btn btn-default" type="button" value="代码"/></td>
					</tr>
				</table> -->
				<table class="table table-bordered">
					<tr>
						<td style="vertical-align:middle" align="center" ><textarea id="blog_content" rows="12" class="comments" style="height:expression((this.scrollHeight>150)?'150px':(this.scrollHeight+5)+'px');overflow:auto;"  name="content" placeholder="添加内容。。。"></textarea></td>
					</tr>
					<tr>
						<td style="vertical-align:middle" align="center" ><input class="btn btn-default" type="button" onclick="save();" value="保存"/></td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		</c:if>
		<div class="row">
			<div id="content" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			<c:if test="${blogList!=null }">
				<c:forEach var="blog" items="${blogList }">
				<pre>${blog.content }</pre>
				</c:forEach>
			</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			<c:if test="${blogList!=null }">
				<pre id="loading" class="bot_msg">loading</pre>
				<pre id="noResult" class="bot_msg">No more results</pre>
			</c:if>
			</div>
		</div>
	</div>
	<hr />
<%@include file="./include/bottomResource.jsp" %>
<script type="text/javascript">
var initPageIndex = ${pageIndex };
var initContextPath = "${pageContext.request.contextPath }";
$(document).ready(function() { 
	pageIndex = initPageIndex;
	contextPath = initContextPath;
}); 
</script>
</body>
</html>