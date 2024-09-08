package wmc;

public class CC10 {

	public void m1() {
		int a = 10;
		int b = 10;
		boolean x = true;

		if(a>10) {
			if(b > 10) {
				if(x) {
					if(!x) {
						for(;;) {
							while(true) {
								for(int i = 0; i < 10 && j < 10; i++) {
									boolean y = x ? false : true;
									boolean yy = a > 10 && !y ? false : true;
								}
							}
						}
					}
				}
			}
		}
		else a--;
	}

	public void m2() {
		int a = 10;
		int b = 10;
		boolean x = true;

		if(a>10) {
			if(b > 10) {
				if(x) {
					if(!x) {
						for(;;) {
							while(true) {
								for(int i = 0; i < 10 && j < 10; i++) {
								}
							}
						}
					}
				}
			}
		}
		else a--;
	}
}
