package cbo;

public class Coupling8 {
	
	public D m1() {
		A a = new A();
		C[] x = new C[10];
		
		return d;
	}
}

public class Coupling81 {

	private Coupling8 c;
	
	public D m1() {
		
		CouplingHelper h = new CouplingHelper();
		C2 c2 = h.m1();
		
		return d;
	}
}

public class Coupling82 {
	
	public D m1() {
		Coupling8 c = new Coupling8();
		Coupling81 c = new Coupling81();
		
		return d;
	}
}

public class Coupling83 {
	
	public Coupling83 m1() {
		
		return new Coupling83();
		
	}
}