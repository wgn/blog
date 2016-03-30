<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/bootstrap-table-develop/dist/bootstrap-table.css" rel="stylesheet">
<title>编码映射列表</title>
<script type="text/javascript">

$(function () {
	 
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    /* var oButtonInit = new ButtonInit();
    oButtonInit.Init(); */

});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tradeList').bootstrapTable({
            url: '/VenderManager/TradeList',         //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 50,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            strictSearch: true,
            clickToSelect: true,                //是否启用点击选中行
            height: 460,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'id',
                title: '序号'
            }, {
                field: 'liushuiid',
                title: '交易编号'
            }, {
                field: 'orderid',
                title: '订单号'
            }, {
                field: 'receivetime',
                title: '交易时间'
            }, {
                field: 'price',
                title: '金额'
            }, {
                field: 'coin_credit',
                title: '投入硬币'
            },  {
                field: 'bill_credit',
                title: '投入纸币'
            },  {
                field: 'changes',
                title: '找零'
            }, {
                field: 'tradetype',
                title: '交易类型'
            },{
                field: 'goodmachineid',
                title: '货机号'
            },{
                field: 'inneridname',
                title: '货道号'
            },{
                field: 'goodsName',
                title: '商品名称'
            }, {
                field: 'changestatus',
                title: '支付'
            },{
                field: 'sendstatus',
                title: '出货'
            },]
        });
    };

    //得到查询的参数
  oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            sdate: $("#stratTime").val(),
            edate: $("#endTime").val(),
            sellerid: $("#sellerid").val(),
            orderid: $("#orderid").val(),
            CardNumber: $("#CardNumber").val(),
            maxrows: params.limit,
            pageindex:params.pageNumber,
            portid: $("#portid").val(),
            CardNumber: $("#CardNumber").val(),
            tradetype:$('input:radio[name="tradetype"]:checked').val(),
            success:$('input:radio[name="success"]:checked').val(),
        };
        return temp;
    };
    return oTableInit;
};

</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-md-offset-2">
				<form action="${pageContext.request.contextPath }/code/list.action" method="post">
					查询条件：
					<table class="table table-bordered">
						<tr>
							<td>类型：</td>
							<td><select name="codeType">
									<c:forEach items="${codeTypeSet }" var="codeType">
										<option value="${codeType }">${codeType }</option>
									</c:forEach>
							</select></td>
							<td><input type="submit" value="查询" /></td>
							<td><a href="${pageContext.request.contextPath }/code/add.action">新增</a></td>
						</tr>
					</table>
					编码映射列表：
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>类型</th>
								<th>编码</th>
								<th>名称</th>
								<th>上级编码</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${codeList }" var="code">
								<tr>
									<td>${code.id }</td>
									<td>${code.type }</td>
									<td>${code.code}</td>
									<td>${code.name }</td>
									<td>${code.parentId }</td>
									<td><a href="${pageContext.request.contextPath }/code/add.action?id=${code.id}">新增</a> | 
									<a href="${pageContext.request.contextPath }/code/edit.action?id=${code.id}">修改</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/bootstrap-table-develop/dist/bootstrap-table.min.js"></script>
	<script src="${pageContext.request.contextPath }/bootstrap-table-develop/dist/locale/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>