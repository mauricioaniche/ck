package cbo;

import dit.A;
import dit.B;
import dit.C;

public class Coupling2 implements CInterface {

	public void m1(A a, B b) {
		throw new RuntimeException();
	}
	
	public C m2() {
		return new C();
	}
}
