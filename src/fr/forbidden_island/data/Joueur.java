package fr.forbidden_island.data;


public class Joueur {
	private Cellule cellule;
	private String nom = "basil" ;
	
	public Joueur(Cellule c) {
		this.cellule=c;
		
	}
	public String getName() {
		return this.nom;
	}
	public void setName() {
		//TODO
	}
	
}
