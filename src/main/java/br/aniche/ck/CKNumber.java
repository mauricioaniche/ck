package br.aniche.ck;

public class CKNumber {

	private String file;
	private int dit;
	private String className;
	private int noc;
	private int wmc;

	public CKNumber(String file, String className) {
		this.file = file;
		this.className = className;
	}
	
	public String getFile() {
		return file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CKNumber other = (CKNumber) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		return true;
	}

	public int getDit() {
		return dit;
	}

	public void setDit(int dit) {
		this.dit = dit;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getClassName() {
		return className;
	}

	public void incNoc() {
		this.noc++;
	}
	
	public int getNoc() {
		return noc;
	}

	public void setWmc(int cc) {
		this.wmc = cc;
	}
	
	public int getWmc() {
		return wmc;
	}

	@Override
	public String toString() {
		return "CKNumber [file=" + file + ", dit=" + dit + ", className=" + className + ", noc=" + noc + ", wmc=" + wmc
				+ "]";
	}

}
