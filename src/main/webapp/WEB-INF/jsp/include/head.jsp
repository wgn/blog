<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container">
	<div class="row">
		<div class="col-xs-8 col-md-offset-2">
			<c:if test="${sessionScope.session_login_user.id==1 }">
				<a href="${pageContext.request.contextPath }/index">返回首页</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
			</c:if>
			<a href="${pageContext.request.contextPath }/u/info">个人中心</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
			<a href="${pageContext.request.contextPath }/logout">登出</a>
			<br />
		</div>
	</div>
</div>
<hr />