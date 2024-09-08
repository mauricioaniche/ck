package returns;

class Returns {
	
	public void m1() {
		boolean a = true;
		boolean b = false;

		if(a) return;
		if(b) return;
		return;
	}

	public int m2() {
		boolean a = true;

		if(a) return 10;
		return 20;
	}

	public void m3() {
		return;
	}

	public void m4() {
		
	}
}