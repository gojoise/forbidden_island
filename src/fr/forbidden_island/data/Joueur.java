package fr.forbidden_island.data;


public class Joueur {
	private Cellule cellule;
	private String nom;
	private int absc;
	private int ord;
	
	public Joueur(Cellule c) {
		this.cellule=c;
		
	}
	public String getName() {
		return this.nom;
	}
	public void setName(String name) {
		this.nom=name;
	}
	
	public Cellule getCellule() {
		return this.cellule;
	}
	public void setCellule(Cellule c) {
		this.cellule=c;
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
	
}
