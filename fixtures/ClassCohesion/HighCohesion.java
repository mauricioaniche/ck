package ClassCohesion;

class HighCohesion {
	private int a = 10;

	public void m1() {
		a = 10;
	}

	static void m2() {
		a = 10;
	}

	void m3() {
		a = 10;
	}
}