package classtypes;

public class A {

	void m1() {}
	class B {
		void m2() {}
	}

	public void m2() {

		Thread t = new Thread()
		{
			public void run()
			{
				System.out.println("Child Thread 1");
			}
		};
	}

	public void m3() {
		MathOperation addition = (int a, int b) -> a + b;

		MathOperation op2 = new MathOperation() {
			public int operation(int a, int b) {
				return a + b;
			}
		};

		int result = op2.operation(2, 2);

	}
}