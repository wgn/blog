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

<title>SQLite Table List</title>

</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div id="result" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			查询条件：
				<table class="table table-bordered">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td align="center"><a href="${pageContext.request.contextPath }/dbaction/createTableView">新增表</a></td>
					</tr>
				</table>
					
			<c:if test="${tableList != null }">
			数据库表：
			<table class="table table-bordered">
				<thead><tr><th>表名</th></tr></thead>
				<tbody>
				<c:forEach var="table" items="${tableList }">
					<tr><td><a href="${pageContext.request.contextPath }/dbaction/loadData/${table }">${table }</a></td></tr>
				</c:forEach>
				</tbody>
			</table>
			</c:if>
			</div>
		</div>
	</div>
	<hr />
<%@include file="./include/bottomResource.jsp" %>
</body>
</html>