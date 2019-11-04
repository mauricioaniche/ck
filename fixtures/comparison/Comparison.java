package comparison;

class Comparison {

	public void m1() {
		int a = 1, b = 2;

		if(a == 1 || b == 2) {
			System.out.println("Yay!");
		}
	}

	public void m2() {
		int a = 1, b = 2;

		if(a == 1) {
			System.out.println("Yay!");
		}
	}

	public void m3() {
		int a = 1;
	}

	public void m4() {
		int a = 1;

		if(a != 1) {
			System.out.println("Yay!");
		}
	}
}