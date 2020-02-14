package innerclasses;

class MessyClass {
	
	private int a;
	private int b;

	class InnerClass1 {
		public void m1() {
			int x = 0;
			int y = 0;
			if(x > 10) {
				if(y < 20) {
					// ...
				} else {
					// ...
				}
			}
		}

		public void m2() {}
	}

	static class InnerClass2 {
		public void m1() {
			int a = a + 10;
		}

		public void m2() {}

		public void m3() {}
	}

	public void m1() {
		this.a = 10;

		for(int i = 0; i < 10; i++) {
			// ...
		}
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
                System.out.println("Child Thread 1");
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
                System.out.println("Child Thread 2");
            }

            public void x() { }
        }; 
	}

	public void m6() {
		class InnerClass3 {
			public void m1();
		};

		int a = 1;
	}

}
