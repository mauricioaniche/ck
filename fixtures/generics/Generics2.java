package bg;

import java.util.Map;
import java.util.List;

class Generics2<X> {


	public static <T extends Map<?, ?>> T notEmpty(final T map, final String message, final Object... values) {
		return null;
	}

	public static <T extends List<?, ?>> T notEmpty(final T map, final String message, final Object... values) {
		return null;
	}

	public int get(X x) {
		return 1;
	}
}