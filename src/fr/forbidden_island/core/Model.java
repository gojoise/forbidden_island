package fr.forbidden_island.core;

import fr.forbidden_island.Controls.Direction;
import fr.forbidden_island.data.Cellule;
import fr.forbidden_island.data.Joueur;
import fr.forbidden_island.data.typeTerrain;

//pour la partie lecture fichier
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//pour l'ile aleatoire et inonde aléatoire..
import java.util.Random;

//
public class Model extends Observable {
	
	private final int dimGrilleAbsc = 35;
	private final int CarreCentralAbscMax =(dimGrilleAbsc * 3) / 4;
	private final int CarreCentralAbscMin =dimGrilleAbsc / 4;
					
//	private final int dimGrilleAbscV2;
	private final int dimGrilleOrd = 21;
	private final int CarreCentralOrdMax =(dimGrilleOrd * 3) / 4;
	private final int CarreCentralOrdMin =dimGrilleOrd / 4;
//	private final int dimGrilleOrdV2;
	private Cellule[][] grille;
	private  String FILENAME = "/forbidden_island/src/carte_ileX.txt";// essaie plutôt "src/carte_ileX.txt"
//  private BufferedReader fluxFichier = new BufferedReader(new FileReader(FILENAME));
	public int currentPlayerV2;
	private int nbJoueurs=4;
	public final int nbActionsmax=5;
	public int nbActions = nbActionsmax;
	public Joueur[] joueurs = new Joueur[this.nbJoueurs];
	Random r = new Random();

	//remplacer par initV2
	public Model() {
		this.grille = new Cellule[dimGrilleAbsc][dimGrilleOrd];
		
//		 String  DimAbscCarteFile;
//		 DimAbscCarteFile = fluxFichier.readLine();
//		 this.dimGrilleAbscV2= Integer.parseInt(DimAbscCarteFile);
//		 
//		 String  DimOrdCarteFile;
//		 DimOrdCarteFile = fluxFichier.readLine();
//		 this.dimGrilleOrdV2= Integer.parseInt(DimOrdCarteFile);	
		 
		init();//Lance l'initialisation
		//initV2();//Lance l'initialisation
//  	fluxFichier.close();
	}
	
	/**
	 * Fonction utilitaire pour le constructeur:
	 * initialise la grille du modèle plus particulièrement le modèle entier
	 */
	private void init() {

		for (int x = 0; x < dimGrilleAbsc; x++) {
			for (int y = 0; y < dimGrilleOrd; y++) {
				// donne des coords aux cellules
				grille[x][y] = new Cellule(this, x , y );
				// donne un état terre au cellules "centrales" (l'ile)
				if (x > CarreCentralAbscMin && x < CarreCentralAbscMax && y > CarreCentralOrdMin
						&& y < CarreCentralOrdMax) {
					grille[x][y].setTypeTerrain(typeTerrain.terre);
				}
				else grille[x][y].setTypeTerrain(typeTerrain.mer);
				// rend plus "aleatoire les contours de l'ile  (carré central)
//			    float t = r.nextFloat();
//                for (float i = 2; i >= 0; i--) {
//                    if (!(x > (CarreCentralAbscMin + i) && x < (CarreCentralAbscMax - i) && y > (CarreCentralOrdMin + i) && y < (CarreCentralOrdMax - i))) {
//                        
//
//                            if (t < (0.2 + (1 / (i + 1))))
//                                grille[x][y].setTypeTerrain(typeTerrain.mer); // redessine aléatoirement les contours de l'ile
//                    
//                    }
//                }
			}
		}

		//initialise les joueurs en les mettant dans les cellules de la grille et les rajoutant dans un tab joueurs[] avec leurs positions respectives.		
		initJoueursV2(this.nbJoueurs);
		currentPlayerV2=0;
		System.out.println("le j0 absc:"+joueurs[0].getAbsc());
		System.out.println("ord:"+joueurs[0].getOrd());
		//System.out.println();
		
	}
	/**
	 * Fonction utilitaire pour le constructeur:
	 * initialise la grille de cellules du modèle en récuperant:
	 *  -les positions des joueurs(depuis la classe Joueur)
	 *  -la taille de la grille dans dimGrilleAbsc et dimGrilleOrd (depuis un fichier carte_ileX.txt)
	 *  -le type de terrain de chacune des cases (depuis un fichier carte_ileX.txt) 
	 */
//	private void initV2() {
//		
//		
//		 int typeTerrainCelFile;
//		 
//		
//		for (int x = 0; x < dimGrilleAbscV2; x++) {
//			for (int y = 0; y < dimGrilleOrdV2; y++) {
//				typeTerrainCelFile = fluxFichier.read();
//				// crée et donne des coords à une cellule
//				grille[x][y] = new Cellule(this, x , y );
//				// donne sont type de terrain initial à la cellule
//				if(typeTerrainCelFile==0)
//				grille[x][y].setTypeTerrain(typeTerrain.mer);
//				if(typeTerrainCelFile==1)
//					grille[x][y].setTypeTerrain(typeTerrain.terre);
//				if(typeTerrainCelFile==2)
//					grille[x][y].setTypeTerrain(typeTerrain.inonde);
//				// donne un état terre au cellules "centrales"					
//			}
//		}
//		//initialise les joueurs en les mettant dans les cellules de la grille et les rajoutant dans un tab joueurs[] avec leurs positions respectives.		
//		initJoueursV2(this.nbJoueurs);
//		currentPlayerV2=joueurs[0];
//	}
//	
	/**
	 *  pour tout la partie ile de la grille appelle a la fin du tour:
	 * inonde
	 * submerge
	 * changePlayer
	 * purgePlayer
	 */
	
	public void endTurn() {
		for (int x = CarreCentralAbscMin-1; x <= CarreCentralAbscMax; x++) {
			for (int y = CarreCentralOrdMin-1; y <= CarreCentralOrdMax; y++) {
				
				submerge(x, y);				
				inonde(x, y);
				
			}
		}		
		nbActions=nbActionsmax;		
		//currentPlayerV2=(currentPlayerV2+1)%nbJoueurs;
		currentPlayerV2=changePlayer(currentPlayerV2);
		notifyObservers();	
		purgePlayers();

		
	}
	/**
	 * Inonde plusieurs cellules aléatoirement après clic sur le bouton fin de tour, fonction utilitaire à endTurn
	 * @param x = coord Absc
	 * @param y = coord Ord
	 */
	private void inonde(int x, int y) {
		float t = r.nextFloat();
		int cpt = 0;
		for (Cellule c : grille[x][y].voisines(grille)) {
			if (c.getTypeTerrain()== typeTerrain.mer )
				cpt++;
		}
		if(grille[x][y].getTypeTerrain() != typeTerrain.mer && cpt>0)
			if (t < 0.09)grille[x][y].setTypeTerrain(typeTerrain.inonde);
	}
	
	/**
	 * Submerge les cellules inondés, fonction utilitaire à endTurn
	 * @param x = coord Absc
	 * @param y = coord Ord
	 */
	private void submerge(int x,int y) {
		if(grille[x][y].getTypeTerrain() == typeTerrain.inonde)
			grille[x][y].setTypeTerrain(typeTerrain.mer);
		
	}
	/**
	 * si le joueur en cours est le dernier (nbJoueur-1)
	 *   si le suivant càd le premier est eliminé
	 *    alors return Changeplayer(0)
	 *   sinon return 0
	 * sinon
	 * 	si le suivant est eliminé
	 *    alors return changePlayer(current+1)
	 *  sinon return current++;
	 * @param current le joueur en cours
	 * @return le nouveau joueur courant
	 */
	private int changePlayer(int current) {
			if(current==nbJoueurs-1) {
				if(joueurs[0].estElimine()) {
					return changePlayer(0);
				}else {
					return 0;
				}
			}else {
				if(joueurs[current+1].estElimine()) {
					return changePlayer(current+1);
				}else {
					return (++current);
				}
			}
		}
	/**
	 * fonction utilitaire à endTurn, rend tout les joueurs dans la mer éliminés
	 */
	private void purgePlayers() {
		for(Joueur j : joueurs) {
			if(grille[j.getAbsc()][j.getOrd()].getTypeTerrain()==typeTerrain.mer && !(j.estElimine())) {
				j.setOut();
				System.out.println("Un joueur a été éliminé!");
			}
		}
	}
	/**
	 * Recherche dans la liste quel est le joueur correspondant à currentPlayer
	 * @return le numéro du joueur en cours
	 */
	public int getNumJoueurV2() {
		
			return this.currentPlayerV2;
		}
	
	/**
	 * Change la case du currentplayer on récupère les vosines et on change la case selon la direction
	 * @param d la direction récupérée dans le controler
	 */

	// attention !! les joueurs peuvent se passer dessus les uns les autres
	public void moveV2(Direction d) {
		
		if(nbActions>0) {
		Cellule [] v=grille[joueurs[currentPlayerV2].getAbsc()][joueurs[currentPlayerV2].getOrd()].voisines(grille); //on récupère les voisines !!
		switch (d) {
		case up:
			if(grille[joueurs[currentPlayerV2].getAbsc()][joueurs[currentPlayerV2].getOrd()-1].getTypeTerrain()!=typeTerrain.mer) {  
//				for(int i=0;i<nbJoueurs;i++) {
//					if(currentPlayerV2!=i){
//						if(joueurs[currentPlayerV2].getAbsc()!=joueurs[i].getAbsc() && joueurs[currentPlayerV2].getOrd()-1!=joueurs[i].getOrd()) {
							this.joueurs[currentPlayerV2].setOrd(joueurs[currentPlayerV2].getOrd()-1);
							nbActions--;
							
						//}	
					//}					
				//} 
			}	
		    
			break;
		case right:
			if(grille[joueurs[currentPlayerV2].getAbsc()+1][joueurs[currentPlayerV2].getOrd()].getTypeTerrain()!=typeTerrain.mer && !v[1].getJoueurV2()) {
				this.joueurs[currentPlayerV2].setAbsc(joueurs[currentPlayerV2].getAbsc()+1);
			    nbActions--;
			}
			break;
		case down:
			if(grille[joueurs[currentPlayerV2].getAbsc()][joueurs[currentPlayerV2].getOrd()+1].getTypeTerrain()!=typeTerrain.mer && !v[2].getJoueurV2()) {
				this.joueurs[currentPlayerV2].setOrd(joueurs[currentPlayerV2].getOrd()+1);
				nbActions--;
			}
			break;
		case left:
			if(grille[joueurs[currentPlayerV2].getAbsc()-1][joueurs[currentPlayerV2].getOrd()].getTypeTerrain()!=typeTerrain.mer && !v[3].getJoueurV2()) {
				this.joueurs[currentPlayerV2].setAbsc(joueurs[currentPlayerV2].getAbsc()-1);
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
		Cellule celluleJoueur = new Cellule(this, joueurs[currentPlayerV2].getAbsc(), joueurs[currentPlayerV2].getOrd());
		if(celluleJoueur.getTypeTerrain()==typeTerrain.inonde) {
			celluleJoueur.setTypeTerrain(typeTerrain.terre);
		nbActions--;
		}
		notifyObservers();
		// else met un message "vous pouvez pas assecher ici" ?
	}
	
	/** 
	 * @param nbJ le nombre de joueurs à répartir
	 * @return les cellules où sont répartis les joeuurs 
	 */
	//ne place pas au bon endroit selon la carte..
	//remplacer "dimGrille..." par "dimGrille...V2" quand initV2 sera pret..
	private void initJoueursV2(int nbJ) {
		if(nbJ>0) {			
			this.joueurs[0]=new Joueur(dimGrilleAbsc/2,(dimGrilleOrd/2)-1);
//			System.out.println("j0 absc:"+joueurs[0].getAbsc()); // absc du joueur 1
//			System.out.println("ord:"+joueurs[0].getOrd()); // ord du joueur 1
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
		if(nbJ>4) {		
			this.joueurs[3]=new Joueur((dimGrilleAbsc/2)+1,(dimGrilleOrd/2)+1);	
		}
		if(nbJ>5) {			
			this.joueurs[3]=new Joueur((dimGrilleAbsc/2)-1,(dimGrilleOrd/2)-1);				
		}
		if(nbJ>6) {
			this.joueurs[3]=new Joueur((dimGrilleAbsc/2)-1,(dimGrilleOrd/2)+1);			
		}
		if(nbJ>7) {
			this.joueurs[3]=new Joueur((dimGrilleAbsc/2)+1,(dimGrilleOrd/2)-1);	
		}
		for(int i=0;i<nbJ;i++) {
			this.joueurs[i].setName("joueur n°"+(i+1));
			//System.out.println(joueurs[i].getName());
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
	
	public int getSize() {
		return Cellule.getSize();
	}
	

}
