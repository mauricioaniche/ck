package innerclasses;

class RecordSC2 {
	public void m1() {
		System.out.println(new Object() {
			@Override public String toString() {

				class X {
					public void m1() {
						int a = 1;
					}
				}
				return "Hello world!";
			}
		});
	}
}