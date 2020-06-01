package fr.forbidden_island.core;

import fr.forbidden_island.Controls.Direction;
import fr.forbidden_island.data.Artefact;
import fr.forbidden_island.data.Cellule;
import fr.forbidden_island.data.Joueur;
import fr.forbidden_island.data.TypeTerrain;
import fr.forbidden_island.data.Statut;
//pour l'ile aleatoire et inonde ale'atoire..
import java.util.Random;


public class Model extends Observable {

	///coords initialisées par le preset (dans init)
	private  int dimGrilleAbsc;
	private  int CarreCentralAbscMax;
	private  int CarreCentralAbscMin;
	private  int dimGrilleOrd;
	private  int CarreCentralOrdMax;
	private  int CarreCentralOrdMin;
	//sert au preset
	private String carteSize;	
	//la grille
	private Cellule[][] grille;
	//dit si la partie est terminée
	private boolean endOfTheGame=false;
	//pour ce qui est des joueurs initialisé par le preset (dans init)
	public Joueur[] joueurs;
	public int currentPlayer;
	private int nbJoueurs;
	//pour ce qui est des artefacts initialisé par le preset (dans init)
	private int nbArtefacts;//est égal au nombre de joueurs
	public Artefact[] artefacts;
	//pour ce qui est des actions
	public final int nbActionsmax=3;
	public int nbActions = nbActionsmax;
	//pour tout ce qui est initialisé ou modifié aléatoirement
	Random r = new Random();

	
	public Model(int nbJ,String cSize) {
		//récupéré dans le preset
		this.carteSize=cSize;
		this.nbJoueurs=nbJ;
		//Lance l'initialisation
		init();		
	}

	/**
	 * Fonction utilitaire pour le constructeur:
	 * initialise la grille du mode'le plus particulie'rement le mode'le entier
	 * initialise les joueurs
	 * initialise les artefacts
	 */
	private void init() {
		//choix de la carte dans le preset
		if(carteSize=="petite") {
			this.dimGrilleAbsc=25;
			this.dimGrilleOrd=16;
			Cellule.setSize(42);
		}
		if(carteSize=="moyenne") {
			this.dimGrilleAbsc=35;
			this.dimGrilleOrd=21;
			Cellule.setSize(30);
		}
		if(carteSize=="grande") {
			this.dimGrilleAbsc=42;
			this.dimGrilleOrd=29;
			Cellule.setSize(25);
		}
		//initialisation d'après le preset
		CarreCentralAbscMax =(dimGrilleAbsc * 3) / 4;
		CarreCentralAbscMin =dimGrilleAbsc / 4;
		CarreCentralOrdMax =(dimGrilleOrd * 3) / 4;
		CarreCentralOrdMin =dimGrilleOrd / 4;
		this.joueurs = new Joueur[this.nbJoueurs];
		nbArtefacts=nbJoueurs;
		this.artefacts = new Artefact[this.nbArtefacts];
		//initialisation des cellules de la grille
		this.grille = new Cellule[dimGrilleAbsc][dimGrilleOrd];
		for (int x = 0; x < dimGrilleAbsc; x++) {
			for (int y = 0; y < dimGrilleOrd; y++) {
				// donne des coords aux cellules
				grille[x][y] = new Cellule(this, x , y );
				// donne un état terre au cellules "centrales" (l'ile)
				if (x > CarreCentralAbscMin && x < CarreCentralAbscMax && y > CarreCentralOrdMin && y < CarreCentralOrdMax) {
					grille[x][y].setTypeTerrain(TypeTerrain.terre);
				}
				//donne un état mer au autres cellules (l'océan)
				else grille[x][y].setTypeTerrain(TypeTerrain.mer);
				//rend plus "aleatoire" les contours de l'ile (le carré central)
				float t = r.nextFloat();
				for (float i = 1; i >= 0; i--) {
					if (!(x > (CarreCentralAbscMin + i) && x < (CarreCentralAbscMax - i) && y > (CarreCentralOrdMin + i) && y < (CarreCentralOrdMax - i))) {
						if (t < (0.2 + (1 / (i + 1))))
							grille[x][y].setTypeTerrain(TypeTerrain.mer); // redessine aléatoirement les contours de l'ile
					}
				}
			}
		}
		//initialise les joueurs en les mettant dans les cellules de la grille et les rajoutant dans un tab joueurs[] avec leurs positions respectives.		
		initJoueurs(this.nbJoueurs);
		currentPlayer=0;
		//initialise les artefacts en les mettant dans les cellules de la grille et les rajoutant dans un tab artefacts[] avec leurs positions respectives.		
				initArtefacts();
	}

	/** 
	 * @param nbJ le nombre de joueurs à répartir
	 * en plus d'initialiser les joueurs sur une cellule, il donne une "chance" au premier de recevoir une clef
	 * @return les cellules où sont répartis les joueurs 
	 */
	private void initJoueurs(int nbJ) {
		if(nbJ>0) {			
			this.joueurs[0]=new Joueur(dimGrilleAbsc/2,(dimGrilleOrd/2)-1);
			//donne aléatoirement une clef ou non  au premier joueur
			float t = r.nextFloat();
			if (t < 0.45 ) {
				joueurs[0].setClef(true);
			}
		}
		if(nbJ>1) {			
			this.joueurs[1]=new Joueur(dimGrilleAbsc/2,(dimGrilleOrd/2)+1);
		}
		if(nbJ>2) {			
			this.joueurs[2]=new Joueur((dimGrilleAbsc/2)+1,dimGrilleOrd/2);			
		}
		if(nbJ>3) {			
			this.joueurs[3]=new Joueur((dimGrilleAbsc/2)-1,dimGrilleOrd/2);			
		}
		for(int i=0;i<nbJ;i++) {
			this.joueurs[i].setName("joueur n°"+(i+1));//initialise les "nom" de chaques joueurs de la partie
		}
	}

	/**
	 * initialise sur des cellules aléatoires de l'ile les artefacts
	 */
	private void initArtefacts() {				
		for(int i= 0;i<nbArtefacts;i++) {		
			int x = r.nextInt((CarreCentralAbscMax-1)-(CarreCentralAbscMin+1));
			int absc = (x+(CarreCentralAbscMin+1));		
			int y = r.nextInt((CarreCentralOrdMax-1)-(CarreCentralOrdMin+1));
			int ord = (y+(CarreCentralOrdMin+1));
			//tentative pour écarter les artefacts vers les bords de l'ile
//			int eloignement = 2;
//			if (!(absc > (CarreCentralAbscMin + eloignement) && absc < (CarreCentralAbscMax - eloignement) && ord > (CarreCentralOrdMin + eloignement) && ord < (CarreCentralOrdMax - eloignement))) {
				if((absc!=dimGrilleAbsc/2 || ord!=dimGrilleOrd/2) && !grille[absc][ord].hasArtefact() && !grille[absc][ord].hasJoueur() && grille[absc][ord].getTypeTerrain()==TypeTerrain.terre)			
					this.artefacts[i]=new Artefact(absc,ord);
			//}
			else
				i--;
		}
	}
	
	/**
	 *  pour tout la partie ile de la grille, appelle à la fin du tour:
	 * submerge
	 * inonde
	 * ensuite:
	 * change le statut des joueurs
	 * change de joueur (de currentplayer)
	 * verifie si la partie est terminée
	 */
	public void endTurn() {
		if(!endOfTheGame) {
			for (int x = CarreCentralAbscMin-1; x <= CarreCentralAbscMax; x++) {
				for (int y = CarreCentralOrdMin-1; y <= CarreCentralOrdMax; y++) {
					submerge(x, y);				
					inonde(x, y);
				}
			}		
			nbActions=nbActionsmax;					
			updateStatutPlayers();
			//currentPlayerV2=changePlayer(currentPlayerV2);
			setEndOfTheGame();
			changePlayer();
			if(endOfTheGame)
				nbActions=0;//pour que quand la partie est finie, le joueur ne puisse pas continuer à bouger avant la fin du tour
			}
	}
	
	/**
	 * Submerge les cellules inondés, fonction utilitaire à endTurn
	 * @param x = coord Absc
	 * @param y = coord Ord
	 */
	private void submerge(int x,int y) {
		if(grille[x][y].getTypeTerrain() == TypeTerrain.inonde)
			grille[x][y].setTypeTerrain(TypeTerrain.mer);

	}
	
	/**
	 * Inonde plusieurs cellules aléatoirement après clic sur le bouton fin de tour(ou barre espace), fonction utilitaire à endTurn
	 * @param x = coord Absc
	 * @param y = coord Ord
	 */
	private void inonde(int x, int y) {
		float t = r.nextFloat();
		int cpt = 0;
		for (Cellule c : grille[x][y].voisines(grille)) {
			if (c.getTypeTerrain()== TypeTerrain.mer )
				cpt++;
		}
		if(grille[x][y].getTypeTerrain() != TypeTerrain.mer && cpt>0)
			if (t < 0.09)grille[x][y].setTypeTerrain(TypeTerrain.inonde);
	}

	/**
	 * fonction utilitaire à endTurn, rend tout les joueurs "mourants" dans la mer éliminés ("morts") 
	 *  et rend tout les joueurs "fuyants" sur l'heliport sauvés ("saufs")
	 */
	private void updateStatutPlayers() {				
		for(Joueur j : joueurs) {
			//si un joueur remplit les condition pour mourir
			if(grille[j.getAbsc()][j.getOrd()].getTypeTerrain()==TypeTerrain.mer && j.getStatut()!=Statut.mort ) {
					j.setStatut(Statut.mourant);
					notifyObservers();
					j.setStatut(Statut.mort);
			}
			//si un joueur remplit les condition pour s'enfuir
		}
		if(joueurs[currentPlayer].getAbsc()==dimGrilleAbsc/2 && joueurs[currentPlayer].getOrd()==dimGrilleOrd/2 && joueurs[currentPlayer].getStatut()!=Statut.sauf) {
			for(Artefact a : artefacts) {
			if(a.hasProprio())
				if(joueurs[currentPlayer]==a.getProprio()) {
					joueurs[currentPlayer].setStatut(Statut.fuyant);	
					notifyObservers();
					joueurs[currentPlayer].setStatut(Statut.sauf);
					break;
				}
			}
		}
	}
	
	/**
	 * donne les differentes condition pour finir la partie (lose ou win) en mettant endOfTheGame=true.
	 */
	private void setEndOfTheGame() {
		//si l'heliport est sous l'eau
		if(grille[dimGrilleAbsc/2][dimGrilleOrd/2].getTypeTerrain()==TypeTerrain.mer)
			endOfTheGame=true;
		//si il n'y a plus d'artefacts
		int countArte=nbArtefacts;
		for(Artefact a: artefacts) {			
			if((grille[a.getAbsc()][a.getOrd()].getTypeTerrain()==TypeTerrain.mer && !a.hasProprio()))//l'artefact est dans l'eau sans proprio
				countArte--;
			if(a.hasProprio() && (a.getProprio().getStatut()!=Statut.vivant))// l'artefact a un proprio qui a gagne' ou perdu
				countArte--;
		}
		if(countArte==0)
			endOfTheGame=true;		
		//si tout les joueurs sont morts ou saufs	 
			int count=0;
			for (Joueur j :joueurs) {
				if(j.getStatut()!=Statut.vivant)count++;
			}
			if(count==nbJoueurs)
				endOfTheGame=true;
	notifyObservers();
	}
	
	/**
	 * si le jeu n'est pas fini, avance de 1 dans le tableau de joueurs.
	 * continue d'avancer de 1 tant qu'il ne trouve pas de joueur vivant
	 * DE PLUS: donne aléatoirement une clef ou non  au joueur dont cela va etre le tour
	 */
	private void changePlayer() {
		if(!endOfTheGame) {
		currentPlayer=(currentPlayer+1)%nbJoueurs;						
		while(joueurs[currentPlayer].getStatut()!=Statut.vivant ) {
			currentPlayer=(currentPlayer+1)%nbJoueurs;			
		}
		float t = r.nextFloat();
		if (t < 0.45 && !joueurs[currentPlayer].getClef()) {
			joueurs[currentPlayer].setClef(true);
		}
		notifyObservers();
		}
	}
	
	/**
	 * si le joueur en cours est le dernier (nbJoueur-1)
	 *   si le suivant ca'd le premier est elimine'
	 *    alors return Changeplayer(0)
	 *   sinon return 0
	 * sinon
	 * 	si le suivant est elimine'
	 *    alors return changePlayer(current+1)
	 *  sinon return current++;
	 * @param current le joueur en cours
	 * @return le nouveau joueur courant
	 */
	/*
	private int changePlayer(int current) {
		if(current==nbJoueurs-1) {
			if(joueurs[0].getStatut()!=Statut.vivant) {
				return changePlayer(0);
			}else {
				return 0;
			}
		}else {
			if(joueurs[current+1].getStatut()!=Statut.vivant) {
				return changePlayer(current+1);
			}else {
				return (++current);
			}
		}
   notifyObservers();	
	}*/
	
    ///////////////////////////des ACTIONS/////////////////////////////////////////
 
	/**
	 * Change la case du currentplayer (sous conditions) on récupère les vosines et on change la case selon la direction
	 * @param d la direction récupérée dans le controler
	 */
	public void move(Direction d) {
		if(joueurs[currentPlayer].getStatut()!=Statut.vivant)
			nbActions=0;
		if(nbActions>0) {
			Cellule [] v=grille[joueurs[currentPlayer].getAbsc()][joueurs[currentPlayer].getOrd()].voisines(grille); //on re'cupe're les voisines !!
			switch (d) {
			case up:
				if(grille[joueurs[currentPlayer].getAbsc()][joueurs[currentPlayer].getOrd()-1].getTypeTerrain()!=TypeTerrain.mer)
					if(!v[0].hasJoueur() || (v[0].getAbsc()==this.dimGrilleAbsc/2 && v[0].getOrd()==this.dimGrilleOrd/2 && !v[0].hasJoueur())) {  
					this.joueurs[currentPlayer].setOrd(joueurs[currentPlayer].getOrd()-1);
					nbActions--;
				}	
				break;
			case right:
				if(grille[joueurs[currentPlayer].getAbsc()+1][joueurs[currentPlayer].getOrd()].getTypeTerrain()!=TypeTerrain.mer)
					if(!v[1].hasJoueur() || (v[1].getAbsc()==this.dimGrilleAbsc/2 && v[1].getOrd()==this.dimGrilleOrd/2  && !v[1].hasJoueur())) {
					this.joueurs[currentPlayer].setAbsc(joueurs[currentPlayer].getAbsc()+1);
					nbActions--;
				}
				break;
			case down:
				if(grille[joueurs[currentPlayer].getAbsc()][joueurs[currentPlayer].getOrd()+1].getTypeTerrain()!=TypeTerrain.mer)
					if(!v[2].hasJoueur() || (v[2].getAbsc()==this.dimGrilleAbsc/2 && v[2].getOrd()==this.dimGrilleOrd/2  && !v[2].hasJoueur())) {
					this.joueurs[currentPlayer].setOrd(joueurs[currentPlayer].getOrd()+1);
					nbActions--;
				}
				break;
			case left:
				if(grille[joueurs[currentPlayer].getAbsc()-1][joueurs[currentPlayer].getOrd()].getTypeTerrain()!=TypeTerrain.mer)
					if(!v[3].hasJoueur() || (v[3].getAbsc()==this.dimGrilleAbsc/2 && v[3].getOrd()==this.dimGrilleOrd/2 && !v[3].hasJoueur())) {
					this.joueurs[currentPlayer].setAbsc(joueurs[currentPlayer].getAbsc()-1);
					nbActions--;
				}
				break;
			default:
				break;
			}
			notifyObservers();
		}
	}

	/**
	 * Asseche la cellule du currentPlayer
	 */
	public void dry() {
		if(grille[joueurs[currentPlayer].getAbsc()][joueurs[currentPlayer].getOrd()].getTypeTerrain()==TypeTerrain.inonde && nbActions>0) {
			grille[joueurs[currentPlayer].getAbsc()][joueurs[currentPlayer].getOrd()].setTypeTerrain(TypeTerrain.terre);
			nbActions--;
		}
		notifyObservers();
	}
	
	/**
	 * permet au joueur de rammasser l'artefact et de le mettre dans sont inventaire (artefactList) 
	 * si les conditions sont réunies
	 */
	public void ramasseArtefact() {
		Cellule v=grille[joueurs[currentPlayer].getAbsc()][joueurs[currentPlayer].getOrd()];
		if(v.hasArtefact() && nbActions>0 && joueurs[currentPlayer].getClef()) {
			for (Artefact a : artefacts) {
				if(a.getAbsc()==joueurs[currentPlayer].getAbsc() && a.getOrd()==joueurs[currentPlayer].getOrd() && !a.hasProprio()){
					a.setProprio(joueurs[currentPlayer]);					
					nbActions--;
					joueurs[currentPlayer].setClef(false);
				}
				
			}
			notifyObservers();			
		}
	}
	
	///////////////////////des GETs///////////////////////////
	
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
	
	public int getNbArtefacts() {
		return this.nbArtefacts;
	}

	public String getNbActionsString() {
		return  Integer.toString(this.nbActions);
	}

	public int getNumJoueur() {
		return this.currentPlayer;
	}
	
	public int getSize() {
		return Cellule.getSize();
	}
	public boolean getEndOfTheGame() {
		return endOfTheGame;
	}
}
