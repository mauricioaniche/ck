package MethodInvocation;

public class Complex1 extends GO2 {
	public void m1() {
		m1();
		x = m2(10);

		Simple2 s = new Simple2();
		s.x();
	}
	
	public void m2(int x) {
		m3(x);
		super.magic();
	}
	
	public void m3(String x) {
		System.out.println(m4(x, 10));
	}

	public int m4(String x, int y){
		Simple2 s = new Simple2();
		s.x();
		m2(x);
	}
}