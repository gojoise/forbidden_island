package fr.forbidden_island.data;

import fr.forbidden_island.core.Model;


public class Cellule {
	
	public static int size;
	private TypeTerrain typeTerrainC;
	
	// coords
	private final int x, y; // position de la cellule sur la grille
	
	//nécessaire pour hasJoueurs() et hasArtefact()
	private Model modele;

	public Cellule(Model mod, int x, int y) {
		this.x = x;
		this.y = y;
		this.modele = mod;
	}
	 
	/**
	 * renvoi un tableau de 4 cellules voisines de la cellule
	 * @param grille
	 * @return le tableau de 4 cellules
	 */
	public Cellule[] voisines(Cellule[][] grille) {
		Cellule[] res = new Cellule[4];
		int x = this.getAbsc();
		int y = this.getOrd();
		res[0] = grille[x][y - 1];// haut
		res[1] = grille[x + 1][y];// droite
		res[2] = grille[x][y + 1];// bas
		res[3] = grille[x - 1][y];// gauche

		return res;
	}
	/**parcours le tableau de joueurs de modele et regarde si un de ces joueurs se trouve sur la cellule
	 * attention, le joueur doit etre vivant.
	 * @return boolean selon si il y a ou pas un joueur sur la cellule
	 */
	public boolean hasJoueur() {
		for (Joueur joueur : modele.joueurs) {
			if (joueur.getAbsc() == this.x && joueur.getOrd() == this.y && joueur.getStatut() == Statut.vivant)
				return true;
		}
		return false;
	}
	/**parcours le tableau d'artefacts de modele et regarde si un de ces artefacts se trouve sur la cellule
	 * attention, l'artefact doit etre sans proriétaire (proprio).
	 * @return boolean selon si il y a ou pas un artefact sur la cellule
	 */
	public boolean hasArtefact() {
		for (Artefact artefact : modele.artefacts) {
			if (artefact != null) {
				if (artefact.getAbsc() == this.x && artefact.getOrd() == this.y && !artefact.hasProprio())
					return true;
			}
		}
		return false;
	}
	
	
///////////////////des GETs///////////////////////

	public int getAbsc() {
		return this.x;
	}

	public int getOrd() {
		return this.y;
	}
	
	public static int getSize() {
		return size;
	}

	public TypeTerrain getTypeTerrain() {
		return this.typeTerrainC;
	}
	
///////////////////des SETs///////////////////////
	
	public static void setSize(int s) {
		size = s;
	}
	
	public void setTypeTerrain(TypeTerrain t) {
		this.typeTerrainC = t;
	}

}
