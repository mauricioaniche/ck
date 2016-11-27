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
	
	private Map<String, Integer> specific; 

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

	@Override
	public String toString() {
		return "CKNumber [file=" + file + ", className=" + className + ", type=" + type + ", dit=" + dit + ", noc="
				+ noc + ", wmc=" + wmc + ", cbo=" + cbo + ", lcom=" + lcom + ", rfc=" + rfc + ", nom=" + nom + ", nopm="
				+ nopm + ", specific=" + specific + "]";
	}

}
