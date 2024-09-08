package modifiers;

import java.util.Map;
import java.util.List;

abstract class ClassWithModifiers {

	
	native void nativeRun();
	
	private void privateMethod();
	
	abstract void abstractMethod();
	
	final void finalMethod();
}
