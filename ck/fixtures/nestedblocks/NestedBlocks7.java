package nestedblocks;

class NestedBlocks7 {

	public void m1() {
		boolean x = true;
		int a = 0;

		synchronized (x) {
			if(a > 0) {
				// do something...
				int b = 0;
			}
		}
	}

	public void m2() {
		boolean x = true;
		int a = 0;

		synchronized (x)
			if(a > 0) {
				// do something...
				int b = 0;
			}

	}

}