package ClassCohesion;

//Test example extracted from https://www.aivosto.com/project/help/pm-oo-cohesion.html#TCC_LCC
class Simple1 {
	class Complex2 {
		String n = "";

		public void n1() {
			n2("Hello world");
		}

		private void n2(String in) {
			n = in;
			System.out.println(n);
		}
	}

	private int a = 10;

	private int b = 10;

	public void m1() {
		a = 10;
	}

	public void m2() {
		a = 10;
	}

	public void m3() {
		b = 10;
	}

	public void m4() {
		int b = 10;
		java.util.List<String> stringList = new java.util.ArrayList<>();
		stringList = stringList.stream().map(string -> m5(b)).collect(java.util.stream.Collectors.toList());
	}

	public String m5(int b) {
		int a = b;
	}
}