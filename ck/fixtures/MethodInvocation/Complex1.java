package MethodInvocation;

public class Complex1 extends GO2 {
	class Complex2 {
		String n = "";

		public void n1() {
			n2("Hello world");
		}

		public void n2(String in) {
			n = in;
			System.out.println(n);
		}
	}

	public void m1() {
		m1();
		x = m2(10);

		Simple2 s = new Simple2();
		s.x();
	}
	
	public void m2(int x) {
		m3(x);
		super.magic();
	}
	
	public void m3(String x) {
		System.out.println(m4(x, 10));
	}

	public int m4(String x, int y){
		Simple2 s = new Simple2();
		s.x();
		m2(x);
		return y;
	}

	public void m5() {
		if(false){
			m2(1);
		} else{
			this.c = 20.0 + a;
		}
	}

	public int m6(){
		return m4("", 1);
	}

	public void m7(){
		int x = GO2.boring() + 1;
	}


	public void m8() {
		int a = 10;
		Simple2 s = new Simple2();
		s.x();

		java.util.List<String> stringList = new java.util.ArrayList<>();
		stringList = stringList.stream().map(string -> m4(string, 1)).collect(java.util.stream.Collectors.toList());
	}

	public void m9(){
		Complex2 complex2 = new Complex2();
		complex2.n1();
	}
}