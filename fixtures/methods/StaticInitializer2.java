package methods;

public class StaticInitializer2 {

	private static int k;

	static {
		for(int i = 0; i < 10; i++) {
			// ...
		}

		k = 10;
	}

	public StaticInitializer() {
		// ...
	}

	public int a() {

		return k + 1;
	}

	static {
		k = 10;
	}

}