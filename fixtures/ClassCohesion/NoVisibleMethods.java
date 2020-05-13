package ClassCohesion;

class NoVisibleMethods {
	private int a = 10;

	private void m1() {
		a = 10;
	}

	private static void m2() {
		a = 10;
	}
}