package classtypes;

public class A {

	void m1() {}
	class B {
		void m2() {}
	}

	public void m2() {
		new B() {{
			void m2() {

			}
		}}
	}

	public void m3() {
		MathOperation addition = (int a, int b) -> a + b;
	}
}