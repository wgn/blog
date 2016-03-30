package lxg;

//定义外部类
public class OuterClass {
	// 外部类静态成员变量
	private static String outer_static_string = "outer_static_string";
	// 外部类非静态成员变量
	private String outer_string = "outer_string";

	// 定义静态嵌套类
	private static class inner_static_class {
		// 静态嵌套类的静态成员变量
		private static String inner_static_string = "inner_static_string";
		// 静态嵌套类的非静态成员变量
		private String inner_string = "inner_string";

		public void display() {
			System.out.println(outer_static_string); // 静态嵌套类可以直接访问外部类的静态成员变量
			// System.out.println(outer_string); //静态嵌套类不能直接访问外部类的非静态成员变量
			System.out.println(inner_static_string); // 可以访问自己的静态成员变量
			System.out.println(inner_string); // 可以访问自己的非静态成员变量
		}
	}

	public void printInnerStatic() {
		inner_static_class innStatic_class = new inner_static_class();
		innStatic_class.display();
		// System.out.println( inner_static_string ); //外部类不能直接访问静态嵌套类的成员变量
		// System.out.println( inner_string ); //外部类不能直接访问静态嵌套类的成员变量
		System.out.println(innStatic_class.inner_static_string); // 外部类可以通过类对象来访问静态嵌套类的成员变量
		System.out.println(innStatic_class.inner_string); // 外部类可以通过嵌套类的类对象来直接访问静态嵌套类的私有成员变量
	}

	// 外部类直接定义内部类
	private class inner_class {
		private String inner_string = "inner_string";

		public void display() {
			System.out.println(outer_static_string); // 内部类可以直接访问外部类的静态成员变量
			System.out.println(outer_string); // 内部类可以直接访问外部类的静态成员变量
		}
	}

	public void printInner() {
		inner_class innClass = new inner_class();

		System.out.println(innClass.inner_string); // 外部类可以通过内部类的类对象来直接访问内部类类的私有成员变量
	}

	public void inner_function() {
		// 函数中定义内部类
		class inner_function_class {
			private String inner_function_string = "inner_function_string";

			public void printInnerFunction() {
				System.out.println(outer_static_string); // 内部类可以直接访问外部类的静态成员变量
				System.out.println(outer_string); // 内部类可以直接访问外部类的静态成员变量
				System.out.println(inner_function_string); // 可以直接访问自己的成员变量
			}
		}

		inner_function_class inner = new inner_function_class();
		System.out.println(inner.inner_function_string);// 外部类可以通过内部类的类对象来直接访问内部类的私有成员变量
	}

	public void printinnerfunction() {
		// inner_function_class inner = new inner_function_class( );
		// //函数中定义的内部类，只在所属的函数内可见
	}
}