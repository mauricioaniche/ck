package wmc;

public class CC3 {
	
	public void m1() {
		int a = 1;
		int b = 2;
		int c = 3;
		int d = 4;

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
