<<<<<<< HEAD
package com.zhuani21.blog.exception;

public class BlogBaseException extends RuntimeException {

	private static final long serialVersionUID = -1239557605090161431L;
	private String message = null;

	public BlogBaseException(){}
	public BlogBaseException(String message){
		this.message = message;
	}
	/*public BlogBaseException(String message, Throwable throwable){
		
	}*/
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String exceptionMsg) {
		this.message = exceptionMsg;
	}
}
=======
package com.zhuani21.blog.exception;

public class BlogBaseException extends RuntimeException {

	private static final long serialVersionUID = -1239557605090161431L;
	private String message = null;

	public BlogBaseException(){}
	public BlogBaseException(String message){
		this.message = message;
	}
	/*public BlogBaseException(String message, Throwable throwable){
		
	}*/
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String exceptionMsg) {
		this.message = exceptionMsg;
	}
}
>>>>>>> f3885674040c9c9bbe2419a1017c37fc9cbbee33
