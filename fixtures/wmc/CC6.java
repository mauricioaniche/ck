package wmc;

public class CC6 {
	public boolean m1(int a) {
		if (0 <= a && a <= 10) {
			return true;
		}
		return false;
	}

	public boolean m2(int a) {
		boolean cond = (0 <= a && a <= 10);
		if (cond) {
			return true;
		}
		return false;
	}

}