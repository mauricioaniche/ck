package java21;

public class Java21RecordPattern {
	record Payment(String id) {
	}

	String describe(Object value) {
		return switch (value) {
			case Payment(String id) -> id;
			default -> "unknown";
		};
	}
}
