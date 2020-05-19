package fr.forbidden_island.data;

import fr.forbidden_island.Controls.Direction;
import fr.forbidden_island.core.Model;

public class Cellule{
	
	private final int x, y; //position de la cellule sur la grille
	private Model modele;
	public final static int size=30;
	private boolean estMer=true;
	private boolean inonde=false;
	private boolean Joueur=false;
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
		this.x=x;
		this.y=y;
		this.modele=mod;
	}
	
	/**
	 * 
	 * @param c une cellule non mer
	 * @return
	 */
	public Cellule [] voisines(Cellule [][] grille) {
		//if(this.terrain)throw new IllegalArgumentException(); 
		Cellule [] res=new Cellule[4];
		res[0]=grille[this.getAbsc()/size][(this.getOrd()-1)/size];//haut
		res[1]=grille[(this.getAbsc()+1)/size][this.getOrd()/size];//droite
		res[2]=grille[this.getAbsc()/size][(this.getOrd()+1)/size];//bas
		res[3]=grille[(this.getAbsc()-1)/size][this.getOrd()/size];//gauche
		return res;
		}


	

	
	public int getAbsc () {return this.x;}
	
	public int getOrd () {return this.y;}
	
	public int getSize () {return size;}
	
	public void setMer(boolean etat) { 
		this.estMer = etat; 
	}
	
	public boolean getMer(){
		return this.estMer;
	}
	
	public void setEstInonde(boolean etat) { 
		this.inonde = etat; 
	}
	
	public boolean getEstInonde(){
		return this.inonde;
	}
	
	public void setJoueur(boolean etat) { 
		this.Joueur = etat; 
	}
	
	public boolean getJoueur(){
		return this.Joueur;
	}

}
