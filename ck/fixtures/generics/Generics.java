package bg;

import java.util.Map;
import java.util.List;

class Generics {


	public static <T extends Map<?, ?>> T notEmpty(final T map, final String message, final Object... values) {
		return null;
	}

	public static <T extends List<?, ?>> T notEmpty(final T map, final String message, final Object... values) {
		return null;
	}
}