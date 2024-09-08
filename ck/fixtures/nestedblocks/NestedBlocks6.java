package nestedblocks;

class NestedBlocks6 {

	public void m1() {
		int a = 0;
		int c = 0;

		if(a > 10) {
			try{
				int b = 0;

				if(c > 0) {
					int d = 0;
				}
			} catch(Exception e) {

			}
		}
	}

	public void m2() {
		int a = 0;
		int c = 0;

		if(a > 10) {
			try{
				int b = 0;

				if(c > 0) {
					int d = 0;
				}
			} catch(Exception e) {
				if(true)
					if(true)
						if(true)
							if(true)
								System.out.println("x");

			}
		}
	}


}