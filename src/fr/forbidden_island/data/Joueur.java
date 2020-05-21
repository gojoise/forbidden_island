package fr.forbidden_island.data;


public class Joueur {
	private Cellule cellule;
	private String nom;
	
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
	
}
