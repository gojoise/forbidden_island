package fr.forbidden_island.data;


import fr.forbidden_island.core.Model;

//
public class Cellule{
	
	private final int x, y; //position de la cellule sur la grille
	private Model modele;
	public final static int size=30;
	private typeTerrain typeTerrainC;
	//attribut image;
	private Joueur jx;
	/*classe cellule :
	 *attribut terrain/joueur/special(artefact clef/element/heliport)
	 * 
	 * sous classe terrain:
	 * attribut  (booleen) terre/mer . (mer definitif)
	 * attribut booleen (avec condition terre) inonde'/normal submerge' (deviens mer) .
	 * 
	 * sous classe special
	 * artefacts / clef/element/heliport )
	 */
	
	public Cellule(Model mod,int x,int y) {
		//charge l' images depuis classe image dans cette cellule
		this.x=x;
		this.y=y;
		this.modele=mod;
	}
	
	/**
	 * 
	 * @param la grille !!
	 * @return
	 */
	public Cellule [] voisines(Cellule [][] grille) {
		//if(this.estMer)throw new IllegalArgumentException(); 
		Cellule [] res=new Cellule[4];
		int x=this.getAbsc();
		int y=this.getOrd();
		res[0]=grille[x][y-1];//haut
		res[1]=grille[x+1][y];//droite
		res[2]=grille[x][y+1];//bas
		res[3]=grille[x-1][y];//gauche

		return res;
		}

	

	
	public int getAbsc () {return this.x;}
	
	public int getOrd () {return this.y;}
	
	public static int getSize () {return size;}

	public void setTypeTerrain (typeTerrain t) {this.typeTerrainC=t;}
	
	public typeTerrain getTypeTerrain () {return this.typeTerrainC;}
	
	public boolean hasJoueur(){
		for(Joueur joueur : modele.joueurs) {
			if(joueur.getAbsc()==this.x && joueur.getOrd()==this.y)			
				return true;
		}
		return false;
	}
	
	public boolean getArtefact(){
		for(Artefact artefact : modele.artefacts) {
			if(artefact.getAbsc()==this.x && artefact.getOrd()==this.y)			
				return true;
		}
		return false;
	}
	
	
	/**
	 * Re'cupe're l'objet joueur sur une cellule :)
	 * @return le joueur sur cette cellule
	 */
	public Joueur getJoueurInfo(){
		for(int i= 0;i<modele.getNbJoueurs();i++) {
			if(modele.joueurs[i].getAbsc()==this.x && modele.joueurs[i].getOrd()==this.y) {
			this.jx= modele.joueurs[i];
			return this.jx;
			}
		}
		
		return this.jx;
	}
	

}
