package nestedblocks;

class NestedBlocks4 {

	public void m1() {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;

		switch(a) {
			case 1:
				if(b>10) b++;
				break;
			case 2:
				if(b>10)
					switch(b) {
						case 10:
							if(c>10)
								c++;
							break;
					}
		}

	}

	public void m2() {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;

		switch(a) {
			case 1: {
				if (b > 10) b++;
				break;
			}
			default:
				if(b>20)
					switch(b) {
						case 10: {
							if (c > 30)
								c++;
							break;
						}
					}
		}

	}

	
}