package fr.forbidden_island.data;

//
public class Joueur {

	// coords
	private int absc;
	private int ord;

	private String nom;
	// l'inventaire du joueur
	private Artefact[] artefactList;
	private boolean clef = false;

	// le statut peut etre vivant, mort, sauvé, en train d'etre sauvé, ou en train
	// de mourir
	private Statut statut;

	public Joueur(int x, int y) {
		this.absc = x;
		this.ord = y;
		this.statut = Statut.vivant;
	}

	/**
	 * si la liste est null, cette methode return false
	 * 
	 * @return booleen selon si le joueur possede ou non un/des artefact(s)
	 */
	public boolean hasArtefacts() {
		if (this.artefactList == null)
			return false;
		else
			return true;
	}
	///////////// des GETs//////////////

	public int getAbsc() {
		return this.absc;
	}

	public int getOrd() {
		return this.ord;
	}

	public String getName() {
		return this.nom;
	}

	public boolean getClef() {
		return clef;
	}

	public Statut getStatut() {
		return this.statut;
	}

	public Artefact[] getArtefacts() {
		return this.artefactList;
	}
	///////////// des SETs//////////////

	public void setAbsc(int x) {
		this.absc = x;
	}

	public void setOrd(int y) {
		this.ord = y;
	}

	public void setName(String name) {
		this.nom = name;
	}

	public void setClef(boolean t) {
		this.clef = t;
	}

	public void setStatut(Statut t) {
		this.statut = t;
	}

}
