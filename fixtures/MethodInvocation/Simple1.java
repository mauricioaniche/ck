package MethodInvocation;

public class Simple1 {
	public void m1() {
		x = m2(10);

		Simple2 s = new Simple2();
		s.x();
	}
	
	public void m2(int x) {
		m3(x);
	}
	
	public void m3(String x) {
		System.out.println(x)
	}
}