package ClassCohesion;

class NoCohesion {
	private static int a = 10;

	public void m1() {
		int a = 10;
	}

	public void m2() { a = 10; }

	private static void m3() { a = 10; }

	private void m4() { a = 10; }
}