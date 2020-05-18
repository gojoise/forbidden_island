package fr.forbidden_island.core;

import fr.forbidden_island.data.Cellule;

public class Model extends Observable{
	private final int dimGrille=20;
	private Cellule [][] grille ;
	
	
	public Model() {
		this.grille=new Cellule[dimGrille][dimGrille];
		init();
	}
	
	private void init() {
		
		for (int x=0;x<dimGrille;x++) {
			for(int y=0;y<dimGrille;y++) {
				grille[x][y]=new Cellule(this,x*Cellule.size,y*Cellule.size);
			}
		}
		
	}
	/*
	 * création d'une ile (tableau de cellules)
	 *
	 * récup toutes les cellules (avec leur états)
	 */
	
	

}
