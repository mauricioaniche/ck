package ClassCohesion;

//Test example extracted from https://www.aivosto.com/project/help/pm-oo-cohesion.html#TCC_LCC
class Simple2 {
	private int a = 10;

	private int b = 10;

	public void m1() {
		a = 10;
	}

	public void m2() {
		a = 10;
	}

	public void m3() {
		a = 10;
		b = 10;
	}

	public void m4() {
		b= 10;
		m5();
	}

	public void m5() {
		int a = 10;
	}
}