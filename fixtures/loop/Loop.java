package loop;

class Loop {
	
	public void m1() {
		int total = 10;
		for(int i = 0; i < 10; i++) {
			total *= i;
		}
	}

	public void m2() {
		int total = 10;
		int i = 0;
		while(i == 0) {
			total *= i;
		}

		while(i == 0) {
			total *= i + 2;
		}
	}

	public void m3() {
		int total = 10;
		int i = 0;
		do {
			total *= i;
		} while(i == 0);
	}

	public void m4() {

	}

	public void m5() {
		int[] numbers = new int[] { 1, 2, 3, 4};

		for(int n : numbers) {
			System.out.println(n);
		}
	}
}