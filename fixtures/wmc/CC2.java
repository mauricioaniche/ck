package wmc;

public class CC2 {

	int a = 0;
	
	public void m1() {
		switch(a) {
		case 1:
			a++;
			break;
		case 2:
			a+=2;
		default:
			a+=3;
		}
	}
	
	public void m2() {
		int b = 10;
		switch(a) {
			case 1:
				a++;
				break;
			case b:
				a+=2;
		}
	}
}
