package fr.forbidden_island.data;

public class Artefact {

	private int absc;
	private int ord;
	private Joueur proprio;
	
	public Artefact(int x,int y) {
		this.absc=x;
		this.ord=y;
		//ajouter des arrtibuts pour differencier les artefacts
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
	
	public void setProprio(Joueur joueur) {
		this.proprio=joueur;
		System.out.println(joueur.getName());
	}
	
	public boolean getProprio() {
		if(this.proprio==null)
			return false;
		else
			return true;
	}
}
