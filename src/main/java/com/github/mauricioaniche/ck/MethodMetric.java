package com.github.mauricioaniche.ck;

public class MethodMetric {
	private int cbo;
	private int rfc;
	private int wmc;
	private String methodName;

	public MethodMetric(String methodName) {
		this.methodName = methodName;
	}

	public void setCbo(int cbo) {
		this.cbo = cbo;
	}

	public void setRfc(int rfc) {
		this.rfc = rfc;
	}

	public void setWmc(int wmc) {
		this.wmc = wmc;
	}

	public int getCbo() {
		return cbo;
	}

	public int getRfc() {
		return rfc;
	}

	public int getWmc() {
		return wmc;
	}

	public String getMethodName() {
		return methodName;
	}

}
