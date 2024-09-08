package nestedblocks;

class NestedBlocks {

	public void m1() {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;

		if(a>0) {
			if(b>20) {
				for(int i = 0; i < 10; i++) {
					if(c>10) {
						if(d<0) {
							System.out.println("deep");
						}
					}

					if(d > 0) {
						System.out.println("aaaa");
					}
				}
			}
		}

		if(a < 0) {
			if(b<0) {
				System.out.println("small");
			}
		}
	}

	public void m2() {
		int a = 0;
		int b = 0;
		int c = 0;

		if(a > 0 && b > 0) {
			if(c>0) {
				System.out.println("xxx");
			}
		}
	}

	public void m3() {

	}
	
}