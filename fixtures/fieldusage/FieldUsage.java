package fieldusage;

class FieldUsage {
	

	private int a = 10;
	private int b = 20;
	private double c = 30.5;
	private String d = "Name";

	public void m1() {
		int x = 10;

		a = x + 1;
		b = a * 20;
	}

	public void m2() {
		int a = 10;
		a = 20;

		int bbb = this.a * 20;
	}

	public void m3() {
		this.a = 20;
		int x = this.a * 10;
	}

	public void m4() {
		this.xx = "John";

		String xxx = "Doe";
		String y = xx + xxx;
	}

	private String xx;
}