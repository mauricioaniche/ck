package cbo;

import dit.A;
import dit.B;
import dit.C;
import wmc.CC1;

public class Coupling1 {

	private B b;
	
	public CC1 m1() {
		A a = new A();
		C[] x = new C[10];
		
		// with no resolution
		Bla y;
		
		CouplingHelper h = new CouplingHelper();
		C2 c2 = h.m1();
		
		return new CC1();
		
	}
}
