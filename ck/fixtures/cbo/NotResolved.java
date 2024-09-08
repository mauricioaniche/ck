package cbo;

import a.b.c.d.e.SomeClass;
import a.b.c.d.e.SomeClass2;

public class NotResolved {


	public A getA() {
		return new A();
	}

	public B getB2() {
		return new B();
	}

	public cbo.B getB() {
		return new B();
	}

	public C[] getC() {
		return new C[10];
	}

	public SomeClass getSomeClass() {
		return new SomeClass();
	}

	public SomeClass2[] getSomeClasses() {
		return new SomeClass2[10];
	}

}