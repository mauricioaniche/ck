package wmc;

public class CC1 {

	int a = 0;
	
	public void m1() {
		if(a>10) a++;
		else a--;
	}
	
	public void m2() {
		for(int i = 0; i < 10; i++) {
			a++;
		}
	}

	public void m3() {
		try {
			if (a > 10) a++;
			else a--;
		}catch (Exception e){
			a = 3;
		}
	}
}
