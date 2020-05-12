package VisibleMethods;

class VisibleMethods {
	class Complex2 {
		String n = "";

		public void n1() {
			n2("Hello world");
		}

		private void n2(String in) {
			n = in;
			System.out.println(n);
		}
	}

	private int a = 10;
	private int b = 20;
	private double c = 30.5;
	private String d = "Name";

	private void m1() {
		int x = 10;

		a = x + 1;
		b = a * 20;
	}

	private void m2() {
		int a = 10;
		a = 20;

		int bbb = this.a * 20;
	}

	public void m3() {
		this.a = 20;
		int x = this.a * 10;
	}

	void m4() {
		this.xx = "John";

		String xxx = "Doe";
		String y = xx + xxx;
	}

	private void m5() {
		int a = 10;
		FunctionInterface fobj = (int x) -> System.out.println(2 * a + b);
	}

	public void m6() {
		if(false){
			a = b + 10;
		} else{
			this.c = 20.0 + a;
		}
	}

	public int m7() {
		return a + b;
	}
	
	public int m8() {
		FieldUsage2 f2 = new FieldUsage2();
		return b + f2.a + f2.x;
	}

	private int m9() {
		FieldUsage2 f2 = new FieldUsage2();
		f2.a = 10;
		return f2.a;
	}

	public static void m10() {
		java.util.List<String> stringList = new java.util.ArrayList<>();
		stringList = stringList.stream().map(string -> m4(string, 1)).collect(java.util.stream.Collectors.toList());
	}

	public static void m11() {
		this.a = 10;
	} void m12() {
		this.a = 10;
	}

	private String xx;
}