package wmc;

public class CC4 {
	
	public void m1() {
		int a = 1;
		int b = 2;
		int c = 3;
		int d = 4;

		class MethodInner {
			public void print() {
				int x = 1, y = 2;
				if(x > 1) {
					if(y > 2) {
						System.out.println("test");
					}
				}
			}
		} // end of inner class

		if(a > 1) a++;
		if(b > 1) b++;
		if(c > 1) c++;
		if(d > 1) d++;

		if(a > 1 && b > 1 || c < 1)
			c++;

		if(a > 1 && (b > 1 || d < 10))
			d++;

	}
}
