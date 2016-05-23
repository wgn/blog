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

<title>SQLite 建表页面</title>

</head>
<body>
<%@include file="./include/head.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			<form>
				<table class="table table-bordered">
					<tr>
						<td style="vertical-align:middle" align="center" colspan="2"><textarea id="sql_content" rows="12" class="comments" style="height:expression((this.scrollHeight>150)?'150px':(this.scrollHeight+5)+'px');overflow:auto;"  name="content" placeholder="建表语句。。。"></textarea></td>
					</tr>
					<tr>
						<td style="vertical-align:middle" align="center" ><input class="btn btn-default" type="button" onclick="goBack();" value="返回" /></td>
						<td style="vertical-align:middle" align="center" ><input class="btn btn-default" type="button" onclick="save();" value="保存"/></td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		<div class="row">
			<div id="result" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
			</div>
		</div>
	</div>
	<hr />
<%@include file="./include/bottomResource.jsp" %>
<script type="text/javascript">
var contextPath = "/";
var initContextPath = "${pageContext.request.contextPath }";
function save(){
	var sql_content = $('#sql_content').val();
	var jquery_content = $.toJSON(sql_content);
	if(sql_content){
		 $.ajax({
		 	type: "POST",
		 	url:contextPath + '/dbaction/createTable',
		 	contentType:"application/json;charset=utf-8", 
			data:'{"createTableString":' + jquery_content + '}',
		   /* data:'content=' +blog_content+ '',*/// 你的formid
		    error: function(request) {
		        alert(" 发生未知错误 ");
		    },
		    success: function(data) {
		    	if(data){
		    		var newPre = document.createElement("pre");
					newPre.innerHTML = "<font color='red'>" + data.createTableResult + "</font>" + "<br/><hr/><br/>" + data.createTableString ;
					$("#result").prepend(newPre);
				}
				$('#sql_content').val("");
		    }
		}); 
	}
} 
$(document).ready(function() { 
	contextPath = initContextPath;
});
function goBack(){
	window.location.href="${pageContext.request.contextPath }/dbaction/tableList";
}
</script>
</body>
</html>