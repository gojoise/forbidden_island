package fr.forbidden_island.data;

public class Artefact {
	//coords
	private int absc;
	private int ord;
	//le joueur qui a ramassé l'artefact
	private Joueur proprio;
	
	public Artefact(int x,int y) {
		this.absc=x;
		this.ord=y;
	}
	

	/**
	 * attribue un joueur(proprio) a l'artefact
	 * @param joueur
	 */
	public void setProprio(Joueur joueur) {
		this.proprio=joueur;
	}
	
	/**
	 * si l'attribut prorio d'Artefact est null alors il n'a pas de proprio
	 * @return un booleen selon si l'artefact a un proprio
	 */
	public boolean hasProprio() {
		if(this.proprio==null)
			return false;
		else
			return true;
	}
	/**
	 * cette methode reste dangeureuse car elle peut renvoyer null si l'artefact n'a pas de proprio
	 * @return le joueur qui possède l'artefact
	 */
	public Joueur getProprio() {
		if(this.hasProprio())
			return this.proprio;
		else
			//indique sur la console si la methode est utilisée quand il n'y a pas de joueur a renvoyer
			System.out.println("dans Artefact.getProrio, artefact sans proprio (null)!!");
		return this.proprio;
	}

//////////////des GETs/////////////////////
	public int getAbsc() {
		return this.absc;
	}
	
	public int getOrd() {
		return this.ord;
	}
	
//////////////des SETs/////////////////////
	public void setAbsc(int x) {
		this.absc=x;
	}

	public void setOrd(int y) {
		this.ord=y;
	}
}
