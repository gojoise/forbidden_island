package fr.forbidden_island.core;

import fr.forbidden_island.Controls.Direction;
import fr.forbidden_island.data.Cellule;
import fr.forbidden_island.data.Joueur;

import java.util.Random;

public class Model extends Observable {
	private final int dimGrilleAbsc = 35;
	private final int dimGrilleOrd = 21;
	private Cellule[][] grille;
	public Cellule currentPlayer;
	private int nbJoueurs=4;
	public final int nbActionsmax=5;
	public int nbActions = nbActionsmax;
	public Joueur[] joueurs = new Joueur[this.nbJoueurs];
	Random r = new Random();

	
	public Model() {

		this.grille = new Cellule[dimGrilleAbsc][dimGrilleOrd];
		init();//Lance l'initialisation
	}
	/**
	 * Fonction utilitaire pour le constructeur:
	 * initialise la grille du modèle plus particulièrement le modèle entier
	 */
	private void init() {

		for (int x = 0; x < dimGrilleAbsc; x++) {
			for (int y = 0; y < dimGrilleOrd; y++) {
				// donne des coords aux cellules
				grille[x][y] = new Cellule(this, x * Cellule.size, y * Cellule.size);
				// donne un état terre au cellules "centrales"
				if (x > dimGrilleAbsc / 4 && x < (dimGrilleAbsc * 3) / 4 && y > dimGrilleOrd / 4
						&& y < (dimGrilleOrd * 3) / 4) {
					grille[x][y].setMer(false);
				}
			    float t = r.nextFloat();
                for (float i = 2; i >= 0; i--) {
                    if (!(x > ((dimGrilleAbsc / 4) + i) && x < (((dimGrilleAbsc * 3) / 4) - i) && y > ((dimGrilleOrd / 4) + i) && y < (((dimGrilleOrd * 3) / 4) - i))) {
                        
//                    	int cpt = 0; 
//                        for(Cellule c : grille[x][y].voisines(grille)) {                   
//                            if (c.getMer())
//                                cpt++;
//                        }
//                        if (cpt ==4) {
//                            grille[x][y].setMer(true);
//                        }
//                         else {
                            if (t < (0.2 + (1 / (i + 1))))
                                grille[x][y].setMer(true); // redessine aléatoirement les contours de l'ile
                         // }
                    }
                }
			}
		}
		//initialise les joueurs en les mettant dans les cellules de la grille et les rajoutant dans un tab joueurs[] avec leurs positions respectives.
		initJoueurs(this.nbJoueurs);
		currentPlayer=joueurs[0].getCellule();
		for (Joueur j : joueurs) {
			grille[j.getCellule().getAbsc()/j.getCellule().getSize()][j.getCellule().getOrd()/j.getCellule().getSize()].setJoueur(true);
		}
		
	}
	/**
	 *  pour tout la partie ile de la grille appelle a la fin du tour:
	 * inonde
	 * submerge
	 * changeJoueur
	 */
	public void endTurn() {
		for (int x = (dimGrilleAbsc / 4)-1; x <= ((dimGrilleAbsc * 3) / 4); x++) {
			for (int y = (dimGrilleOrd / 4)-1; y <= ((dimGrilleOrd * 3) / 4); y++) {
				
				submerge(x, y);				
				inonde(x, y);
				
			}
		}
		changeJoueur();
	
	}
	/**
	 * Inonde plusieurs cellules aléatoirement après clic sur le bouton fin de tour
	 * @param x = coord Absc
	 * @param y = coord Ord
	 */
	public void inonde(int x, int y) {
		float t = r.nextFloat();
		int cpt = 0;
		for (Cellule c : grille[x][y].voisines(grille)) {
			if (c.getMer())
				cpt++;
		}
		if(!grille[x][y].getMer() && cpt>0)
			if (t < 0.09)grille[x][y].setEstInonde(true);
	}
	
	/**
	 * Submerge les cellules inondés
	 * @param x = coord Absc
	 * @param y = coord Ord
	 */
	public void submerge(int x,int y) {
		if(grille[x][y].getEstInonde() )
			grille[x][y].setMer(true);
		
	}

	/**
	 * Recherche dans la liste quel est le joueur correspondant à currentPlayer
	 * @return le numéro du joueur en cours
	 */
	public int getNumJoueur() {
		for(int i=0;i<joueurs.length;i++) {
			if(joueurs[i].getCellule()==currentPlayer) {
			return i;
			}
		}
		System.out.println("ya un bug dans getNumJoueur()");
		return -1;
		//attention		
	}
	/**
	 * Change la case du currentplayer on récupère les vosines et on change la case selon la direction
	 * @param d la direction récupérée dans le controler
	 */
	public void move(Direction d) {
		int numJoueur=getNumJoueur();
		if(nbActions>0) {
		Cellule [] v=currentPlayer.voisines(grille); //on récupère les voisines !!
		currentPlayer.setJoueur(false);
		switch (d) {
		case up:
			if(!v[0].getMer() && !v[0].getJoueur()) {
				this.currentPlayer=v[0];
				nbActions--;
		    }
			break;
		case right:
			if(!v[1].getMer() && !v[1].getJoueur()) {
				this.currentPlayer=v[1];
			    nbActions--;
			}
			break;
		case down:
			if(!v[2].getMer() && !v[2].getJoueur()) {
				this.currentPlayer=v[2];

				nbActions--;
			}
			break;
		case left:
			if(!v[3].getMer() && !v[3].getJoueur()) {
				this.currentPlayer=v[3];
				nbActions--;
			}
			break;
		default:
			break;
		}
		this.currentPlayer.setJoueur(true);
		this.joueurs[numJoueur].setCellule(currentPlayer);
		notifyObservers();
		//System.out.println(nbActions); //affiche le nb d'actions restantes
		}
	}
	/**
	 * Asseche la cellule du currentPlayer
	 */
	public void dry() {
		if(currentPlayer.getEstInonde()) {
		currentPlayer.setEstInonde(false);
		nbActions--;
		}
		// else met un message "vous pouvez pas assecher ici" ?
	}
	
	/**
	 * 
	 * @param nbJ le nombre de joueurs à répartir
	 * @return les cellules où sont répartis les joeuurs 
	 */
	private void initJoueurs(int nbJ) {
		Cellule[] casesDepart= new Cellule[nbJ];
		if(nbJ>0)
			casesDepart[0]=grille[(dimGrilleAbsc/2)][(dimGrilleOrd/2)-1];
		if(nbJ>1)
			casesDepart[1]=grille[(dimGrilleAbsc/2)][(dimGrilleOrd/2)+1];
		if(nbJ>2)
			casesDepart[2]=grille[(dimGrilleAbsc/2)+1][(dimGrilleOrd/2)];
		if(nbJ>3)
			casesDepart[3]=grille[(dimGrilleAbsc/2)-1][(dimGrilleOrd/2)];
		if(nbJ>4)
			casesDepart[4]=grille[(dimGrilleAbsc/2)+1][(dimGrilleOrd/2)+1];
		if(nbJ>5)
			casesDepart[5]=grille[(dimGrilleAbsc/2)-1][(dimGrilleOrd/2)-1];
		if(nbJ>6)
			casesDepart[6]=grille[(dimGrilleAbsc/2)-1][(dimGrilleOrd/2)+1];
		if(nbJ>7)
			casesDepart[7]=grille[(dimGrilleAbsc/2)+1][(dimGrilleOrd/2)-1];
		for(int i=0;i<nbJ;i++) {
			joueurs[i]=new Joueur(casesDepart[i]);
			
			joueurs[i].setName("joueur n°"+(i+1));
			System.out.println(joueurs[i].getName());
		}
	}
	/**
	 * Change le joueur en cours, i.e change le current player avec le nouveau joueur
	 * si on est au joueur 1 on passe au joueur 2 etc , dans l'ordre croissant en revenant au premier une fois au dernier
	 * ON recherche quel est le joueur (son numéro) en currentplayer
	 */
	public void changeJoueur() {
		nbActions=nbActionsmax;
		
		if(currentPlayer==joueurs[nbJoueurs-1].getCellule()) {
			this.currentPlayer=joueurs[0].getCellule();
		System.out.println("42");
		}
		else {
			for(int i=0;i<joueurs.length-1;i++) {
				if(joueurs[i].getCellule()==currentPlayer) {
					this.currentPlayer=joueurs[i+1].getCellule();
					System.out.println("43");
					break;
				}
			}
		}
	}
	
	
	public int getDimGrilleAbsc() {
		return this.dimGrilleAbsc;
	}

	public int getDimGrilleOrd() {
		return this.dimGrilleOrd;
	}

	public Cellule[][] getGrille() {
		return this.grille;
	}
	public int getNbJoueurs() {
		return this.nbJoueurs;
	}
	
	public String getNbActionsString() {
		return  Integer.toString(this.nbActions);
	}
	
	

}
