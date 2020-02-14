package innerclasses;

class SC2 {
	public void m1() {
		System.out.println(new Object() {
			@Override public String toString() {

				static class X {
					public void m1() {
						int a = 1;
					}
				}
				return "Hello world!";
			}
		});
	}
}