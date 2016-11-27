package com.github.mauricioaniche.ck;

import java.util.HashMap;
import java.util.Map;

public class CKNumber {

	private String file;
	private String className;
	private String type;

	private int dit;
	private int noc;
	private int wmc;
	private int cbo;
	private int lcom;
	private int rfc;
	private int nom;
	private int nopm;
	private int nosm;

	private int nof;
	private int nopf;
	private int nosf;

	private int nosi;
	private int loc;
	
	private Map<String, Integer> specific;
	private boolean error; 

	public CKNumber(String file, String className, String type) {
		this.file = file;
		this.className = className;
		this.type = type;
		
		this.specific = new HashMap<String, Integer>();
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


	public int getCbo() {
		return cbo;
	}

	public void setCbo(int cbo) {
		this.cbo = cbo;
	}

	public void setLcom(int lcom) {
		this.lcom = lcom;
	}
	public int getLcom() {
		return lcom;
	}

	public void setRfc(int rfc) {
		this.rfc = rfc;
	}
	
	public int getRfc() {
		return rfc;
	}

	public void setNom(int nom) {
		this.nom = nom;
	}
	public int getNom() {
		return nom;
	}
	
	public int getSpecific(String key) {
		if(!specific.containsKey(key)) return -1;
		return specific.get(key);
	}
	
	public void addSpecific(String key, int value) {
		specific.put(key, value);
	}

	public String getType() {
		return type;
	}

	public int getNopm() {
		return nopm;
	}

	public void setNopm(int nopm) {
		this.nopm = nopm;
	}

	public int getNosm() {
		return nosm;
	}

	public void setNosm(int nosm) {
		this.nosm = nosm;
	}

	public int getNof() {
		return nof;
	}

	public void setNof(int nof) {
		this.nof = nof;
	}

	public int getNopf() {
		return nopf;
	}

	public void setNopf(int nopf) {
		this.nopf = nopf;
	}

	public int getNosf() {
		return nosf;
	}

	public void setNosf(int nosf) {
		this.nosf = nosf;
	}
	
	public int getNosi() {
		return nosi;
	}

	public void setNosi(int nosi) {
		this.nosi = nosi;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}


	public boolean isError() {
		return error;
	}
	
	public void error() {
		this.error = true;
	}

	@Override
	public String toString() {
		return "CKNumber [file=" + file + ", className=" + className + ", type=" + type + ", dit=" + dit + ", noc="
				+ noc + ", wmc=" + wmc + ", cbo=" + cbo + ", lcom=" + lcom + ", rfc=" + rfc + ", nom=" + nom + ", nopm="
				+ nopm + ", nosm=" + nosm + ", nof=" + nof + ", nopf=" + nopf + ", nosf=" + nosf + ", nosi=" + nosi
				+ ", loc=" + loc + ", specific=" + specific + ", error=" + error + "]";
	}


}
