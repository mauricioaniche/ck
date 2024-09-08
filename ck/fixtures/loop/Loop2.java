package loop;

class Loop2 {

	public void m0() {
		String _for;
	}

	public void m1() {
		for(;;) {

		}

		for(int i = 10;;) {

		}
	}

	public void m2() {
		while(true) {

		}
	}

	public void m3() {
		int a = 0;

		for(;;)
			while(true)
				a=1;
	}

	public void m4() {
		int a = 0;
		do a++; while(true);
	}

}