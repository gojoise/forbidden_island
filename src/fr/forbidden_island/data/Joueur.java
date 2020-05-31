package fr.forbidden_island.data;


//
public class Joueur {
	private String nom;
	private int absc;
	private int ord;
	private Artefact [] artefactList;
	
	private Statut statut;
	
	
	
	public Joueur(int x,int y) {
		this.absc=x;
		this.ord=y;
		this.statut=Statut.vivant;
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
		this.ord=y;
	}
	
	public void setStatut(Statut t) {
		this.statut=t;
	}
	
	public Statut getStatut() {
		return this.statut;
	}
	

	public Artefact[] getArtefacts() {
		return this.artefactList;
	}
	
	public boolean hasArtefacts() {
		if(this.artefactList==null)
			return false;
		else
			return true;
	}
	
}
