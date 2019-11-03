package enumd;

public enum EnumDecl {

	A(1),
	B(2);

	private int i;

	public EnumDecl(int i) {
		this.i = i;
	}

	public int getX() {
		int kk = 0;
		if(kk > 10) {
			// ...
		}

		for(int j = 0; j < 10; j++) {
			// ...
		}

		return this.i * 2;
	}
}