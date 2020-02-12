package wmc;

public class CC9 {

	public void m0() {
		int a = 10;
	}

	public void m1() {
		int a = 0;
		int b = 0;
		int c = 0;

		if(a > 0) {

		}

	}

	public void m2() {
		int a = 0;
		int b = 0;
		int c = 0;

		if(a > 0) {

		} else if(b > 0) {

		}

	}

	public void m3() {
		int a = 0;
		int b = 0;
		int c = 0;

		if(a > 0) {

		} else {

		}

	}

	public void m4() {
		int a = 0;
		int b = 0;
		int c = 0;

		if(a > 0) {

		} else if(b > 0) {

		} else if(c > 0) {

		} else {

		}
	}

	public void m5() {
		int a = 0;
		int b = 0;
		int c = 0;

		if(a > 0 && b < 0) {

		} else if(b > 0 || c > 0) {

		} else if((c == 0 || b < 0) & (a != 0)) {

		} else {

		}
	}

	public void m6() {
		boolean a = true;
		boolean b = false;
		boolean c = true;

		if(a) {

		} else if (a == true && b) {

		} else if (!b) {

		} else if(!b && c == false) {

		} else if(!c && b) {

		}
	}

	public void m7() {
		boolean x = returnTrue();

		if(x) {

		} else if (returnTrue()) {

		} else if (returnTrue() == false) {

		} else if (!returnTrue()) {

		}
	}

	public void m8() {
		int x = returnInt();

		if(x > 10) {

		} else if (returnInt() > 20) {

		} else if (returnInt() + 10 < 20) {

		}
	}

	public void m9() {
		int a = 0;
		int b = 0;

		boolean result = a > 10 ? true : false;
		boolean result2 = (a > 10 ? (b > 20 ? true : false) : false);

		if(result) {

		} else if ((a > 10 ? true : false)) {

		} else if ((a > 10 ? true : false) && b > 0) {

		}

	}

	public int returnInt() {
		return 10;
	}

	public boolean returnTrue() {
		return true;
	}


}
