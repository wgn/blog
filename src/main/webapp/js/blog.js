var pageIndex = 0;
var contextPath = "/";

/* $(document).ready(function(){
	loadMore();
});  
$(window).scroll(function(){  
	// 当滚动到最底部以上100像素时， 加载新内容  
	if ($(document).height() - $(this).scrollTop() - $(this).height()<100){
		loadMore();
	} 
}); */
function loadMore() {
	var content = document.getElementById("content");
	$.ajax({
		type:"post",
		url:contextPath +"/blog/loadMore.action",
		contentType:"application/json;charset=urf-8", 
		data:'{"pageIndex":' + pageIndex + '}',
		success : function(data){
			if(null!=data && data.length>0){
				for(var i=0;i<data.length;i++){
					var newPre = document.createElement("pre");
					newPre.innerHTML = data[i].content;
					content.appendChild(newPre);
				}
			}
			pageIndex = pageIndex + data.length;
		}
	});
}
function save(){
	var content = document.getElementById("content");
	var blog_content = $('#blog_content').val();
	if(blog_content){
		 $.ajax({
		 	type: "POST",
		 	url:contextPath + '/blog/save.action',
		    data:'content=' +blog_content+ '',// 你的formid
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
