package subclasses;

class MessyClass {
	
	private int a;
	private int b;

	class SubClass1 {
		public void m1() {}

		public void m2() {}
	}

	static class SubClass2 {
		public void m1() {}

		public void m2() {}
	}

	public void m1() {
		this.a = 10;
		System.out.println(a);
	}

	public void m2() {
		this.b = 10;
		System.out.println(b);

	}

	public void m3() {
		Thread t = new Thread() 
        { 
            public void run() 
            { 
                System.out.println("Child Thread"); 
            } 
        }; 
	}

	public void m4() {
		Arrays.asList("a1", "a2", "a3")
		    .stream()
		    .findFirst()
		    .ifPresent(x -> System.out.println(x));
	}


	public void m5() {
		Thread t = new Thread() 
        { 
            public void run() 
            { 
                System.out.println("Child Thread"); 
            } 
        }; 
	}

	public void m6() {
		class SubClass3 {
			public void m1();
		};

		int a = 1;
	}

}
