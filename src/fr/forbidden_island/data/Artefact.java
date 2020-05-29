package fr.forbidden_island.data;

public class Artefact {

	private int absc;
	private int ord;
	
	public Artefact(int x,int y) {
		this.absc=x;
		this.ord=y;
	}

	public int getAbsc() {
		return this.absc;
	}
	public void setAbsc(int x) {
		this.absc=x;
	}

	public int getOrd() {
		return this.ord;
	}

	public void setOrd(int y) {
		this.ord=y;
	}
}
