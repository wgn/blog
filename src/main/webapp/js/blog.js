var pageIndex = 0;
var contextPath = "/";
var haveMore = true;
var running = false;

/* $(document).ready(function(){
	loadMore();
}); */ 
$(window).scroll(function(){  
	 //当滚动到最底部以上100像素时， 加载新内容  
	if ($(document).height() - $(this).scrollTop() - $(this).height()<50){
		if(!haveMore || running){
			return;
		}
		$("#loading").show();
		loadMore();
	}
}); 
function loadMore() {
	running = true;
	var content = document.getElementById("content");
	$.ajax({
		type:"post",
		url:contextPath +"/blog/loadMore.action",
		contentType:"application/json;charset=utf-8;", 
		data:'{"pageIndex":' + pageIndex + '}',
		error: function(request) {
			$("#loading").hide();
	        alert(" 发生未知错误 ");
	        running = false;
	    },
		success : function(data){
			if(null!=data && data.length>0){
				for(var i=0;i<data.length;i++){
					var newPre = document.createElement("pre");
					newPre.innerHTML = data[i].content;
					content.appendChild(newPre);
				}
			}else{
				haveMore = false;
				$("#noResult").show();
			}
			$("#loading").hide();
			running = false;
			pageIndex = pageIndex + data.length;
		}
	});
}
function save(){
	var content = document.getElementById("content");
	var blog_content = $('#blog_content').val();
	var jquery_content = $.toJSON(blog_content);
	//http://img2.imgtn.bdimg.com/it/u=2969439243,1924542195&fm=21&gp=0.jpg
	if(blog_content){
		 $.ajax({
		 	type: "POST",
		 	url:contextPath + '/blog/save.action',
		 	contentType:"application/json;charset=utf-8", 
			data:'{"content":' + jquery_content + '}',
		   /* data:'content=' +blog_content+ '',*/// 你的formid
		    error: function(request) {
		        alert(" 发生未知错误 ");
		    },
		    success: function(data) {
		    	if(data){
		    		var newPre = document.createElement("pre");
					newPre.innerHTML = data.content;
					$("#content").prepend(newPre);
				}
				pageIndex = pageIndex + 1;
				$('#blog_content').val("");
		    }
		}); 
	}
}
