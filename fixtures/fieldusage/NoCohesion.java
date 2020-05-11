package fieldusage;

class NoCohesion {
	private int a = 10;

	public void m1() {
		int a = 10;
	}

	public void m2() { a = 10; }
}