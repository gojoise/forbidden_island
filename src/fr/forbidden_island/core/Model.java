package fr.forbidden_island.core;

import fr.forbidden_island.data.Cellule;

public class Model extends Observable{
	private final int dimGrilleAbsc=70;
	private final int dimGrilleOrd=42;
	private Cellule [][] grille ;


	public Model() {
		this.grille=new Cellule[dimGrilleAbsc][dimGrilleOrd];
		init();
		//System.out.println(grille[0][0].getAbsc());
		//System.out.println(grille[0][0].getOrd());
	}

	private void init() {
		for (int x=0;x<dimGrilleAbsc;x++) {
			for(int y=0;y<dimGrilleOrd;y++) {
				//donne des coords aux cellules
				grille[x][y]=new Cellule(this,x*Cellule.size,y*Cellule.size);
					//donne un état  terre au cellules "centrales"
					if(x>dimGrilleAbsc/4 && x<(dimGrilleAbsc*3)/4  && y>dimGrilleOrd/4 && y<(dimGrilleOrd*3)/4) {
						grille[x][y].setTerrain(false);
					}
			}
		}

	}
	
	public int getDimGrilleAbsc() { return this.dimGrilleAbsc;}
	public int getDimGrilleOrd() { return this.dimGrilleOrd;}
	public Cellule [][] getGrille(){ return this.grille; }
	/*
	 * création d'une ile (tableau de cellules)
	 *
	 * récup toutes les cellules (avec leur états)
	 */



}
