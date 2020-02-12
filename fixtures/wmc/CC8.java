package wmc;

import java.util.*;

public class CC8 {

	public void m0() {

		for(int i = 0, j = 20; i < 10 && j < 20; i++, j++ ) {
			// do something else
		}
	}

	public void m1() {

		for(int i = 0; i < 10; i++) {
			// do something
		}

		for(int i = 0, j = 20; i < 10 && j < 20; i++, j++ ) {
			// do something else
		}

		for(;;) {

		}

		boolean x = true;
		for(int i = 0; x; i++) {

		}
	}

	public void m2() {
		int i = 0;
		int j = 0;

		while(i < 10) {
			// do something
		}

		while(i < 10 && j < 20) {
			// do something else
		}

		while(true) {

		}

		boolean x = true;
		while(x) {

		}
	}

	public void m3() {
		int i = 0;
		int j = 0;

		do {

		} while(i < 10);

		do {
			// do something else
		} while ((i < 10 && j < 20));

		do {

		} while(true);

		boolean x = true;
		do {

		} while(x);
	}

	public void m4() {
		List<String> xs = new ArrayList<String>();
		for(String x : xs) {

		}
	}

	public void m5() {
		int a = 10;
		while(a > 10 ? true : false) {

		}

		do {

		} while(a > 10 ? true : false);


	}

	public void m6() {
		int i = 0;
		int j = 0;
		boolean y = false;

		while(true && y && !y && y == false || i > 0) {

		}

	}


}
