package com.zhuani21.blog.other;

public class Test {

	public static void main(String[] args) {
		int money = 14000;

		double total = 0;

		int year = 40;

		float rating = 0.18f;

		for (int i = 0; i < 41; i++) {
			System.out.println("year" + i + " : " + calc(money, i, rating));
			total += calc(money, i, rating);
			System.out.println("total: " + total);
		}
		// String str = ""; //我们想赋值这样一个字符，假设我输入法打不出来

		// 但我知道它的Unicode是0x1D11E
		// String str = "\u1D11E"; //这样写不会识别

		// 于是通过计算得到其UTF-16编码 D834 DD1E
		String str = "\uD834\uDD1E"; // 然后这么写

		System.out.println(str); // 成功输出了""
	}

	private static double calc(float money, int i, float rating) {

		for (int j = 0; j < i; j++) {
			money = money * (1 + rating);
		}
		return money;
	}

}
