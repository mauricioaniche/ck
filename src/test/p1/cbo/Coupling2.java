package cbo;

import dit.A;
import dit.B;
import dit.C;
import wmc.CC1;

public class Coupling2 implements CInterface {

	public C m1(A a, B b) {
		throw new RuntimeException();
		return new C();
	}
}
