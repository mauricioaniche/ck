package rfc;

public class GO {

	
	public void m1() {
		m2(10);
		
		GO2 g = new GO2();
		g.magic();
	}
	
	public void m2(int x) {
	}
	
	public void m3() {
		// no resolution
		Bla b = new Bla();
		b.xyz();
		
	}
}
