package fr.forbidden_island.data;

import fr.forbidden_island.core.Model;

public class Cellule {
	
	private final int x, y; //position de la cellule sur la grille
	private Model modele;
	private boolean terrain;
	/*classe cellule :
	 *attribut terrain/joueur/special(artefact clef/element/heliport)
	 * 
	 * sous classe terrain:
	 * attribut  (booleen) terre/mer . (mer definitif)
	 * attribut booleen (avec condition terre) inondé/normal submergé (deviens mer) .
	 * 
	 * sous classe special
	 * artefacts / clef/element/heliport )
	 */
	
	public Cellule(Model mod,int x,int y) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.modele=mod;
	}
	

}
