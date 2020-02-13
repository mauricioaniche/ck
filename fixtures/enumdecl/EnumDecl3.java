package enumd;

public enum EnumDecl3 {

	A(1),
	B(2);

	private int i;

	public EnumDecl(int i) {
		this.i = i;
	}

	public int getX() {

		static class Other {
			public void x1(){
				int a = 0;
				int b = 0;

				if(a) {
					if(b) {
						System.out.println("hi");
					}
				}
			}
			public void x2(){}
			public void x3(){}
			public void x4(){}
		}

		int kk = 0;
		if(kk > 10) {
			// ...
		}

		for(int j = 0; j < 10; j++) {
			// ...
		}

		return this.i * 2;
	}

	static class Other2 {
		public void x1(){
			int a = 0;
			int b = 0;

			if(a) {
				if(b) {
					System.out.println("hi");
				}
			}
		}
		public void x2(){}
		public void x3(){}
		public void x4(){}
	}
}