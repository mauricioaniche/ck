package pcount;

class A {
	
	public void a() {
		int a = 1;
		int b = 1;
	}

	public void b() {
		int a = 1;
		a++;
		a = a + 2;

		int b = 1;
		b = b + a;
	}

	public void bb() {
		int a = 10;
		c(a);
	}

	public void c(int a) {

	}

	public void d(int a, int b) {
		a = a + 1;
		a = a / 10;
	}

	public void e(final B a, int b) {
		a = new B();
		a.m1();
	}
}