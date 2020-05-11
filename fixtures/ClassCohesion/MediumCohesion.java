package ClassCohesion;

class MediumCohesion {
	private int a = 10;

	public void m1() {
		int a = 10;
	}

	public void m2() { a = 10; }

	public void m3() {
		a = 10;
	}
}