package enumd;

// based on: https://mkyong.com/java/java-enum-example/
public enum EnumDecl2 {
	ARIN("whois.arin.net"),
	RIPE("whois.ripe.net"),
	APNIC("whois.apnic.net"),
	AFRINIC("whois.afrinic.net"),
	LACNIC("whois.lacnic.net"),
	JPNIC("whois.nic.ad.jp"),
	KRNIC("whois.nic.or.kr"),
	CNNIC("ipwhois.cnnic.cn"),
	UNKNOWN("");

	private String url;

	EnumDecl2(String url) {
		this.url = url;
	}

	public String url() {
		if(url == "nothing") {
			return "a";
		} else {
			return url;
		}
	}

	public void m2() {
		for(int i = 0 ; i < 10; i++) {
			for(int j = 0 ; j < 10; i++) {
				if(j > 20) {
					if(i > 30) {
						System.out.println("hi");
					}
				}

			}
		}
	}
}