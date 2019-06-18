package java11;

import java.util.Function;

class Java11 {

	void m1() {
		m2((@NotNull var n) -> n * 2);
	}

	void m2(Function<Integer, Integer> fn) {
		System.out.println(fn.apply(2));
	}

}
