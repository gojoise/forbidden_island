package fr.forbidden_island.data;


//
public class Joueur {
	private String nom;
	private int absc;
	private int ord;
	
	private boolean isOut; //pour savoir si il est éliminé
	
	
	public Joueur(int x,int y) {
		this.absc=x;
		this.ord=y;
		this.isOut=false;
	}
	
	public String getName() {
		return this.nom;
	}
	
	public void setName(String name) {
		this.nom=name;
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
		this.absc=y;
	}
	
	public void setOut() {
		this.isOut=true;
	}
	
	public boolean estElimine() {
		return isOut;
	}
	
}
