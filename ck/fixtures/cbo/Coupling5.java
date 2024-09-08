package cbo;

public class Coupling5 {

	private A a;
	private C c;
	
	public void am1() {
		return;
	}

	public void am2() {
		a.method();
	}

	public void am3() {
		B b = new B();
		b.method();
	}

	public void am4() {
		a.method();

		B b = new B();
		b.method();
	}
}
