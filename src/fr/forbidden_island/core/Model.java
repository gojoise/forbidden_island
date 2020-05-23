package fr.forbidden_island.core;

import fr.forbidden_island.Controls.Direction;
import fr.forbidden_island.data.Cellule;
import fr.forbidden_island.data.Joueur;
import fr.forbidden_island.data.typeTerrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class Model extends Observable {
	
	private final int dimGrilleAbsc = 35;
	private final int dimGrilleAbscV2;
	private final int dimGrilleOrd = 21;
	private final int dimGrilleOrdV2;
	private Cellule[][] grille;
	private static final String FILENAME = new File("carte_ileX.txt").getAbsolutePath();
	private BufferedReader fluxFichier = new BufferedReader(new FileReader("C:\\Users\\Arthur\\Desktop\\forbidden_island\\src\\carte_ileX.txt"));
	public Cellule currentPlayer;
	public Joueur currentPlayerV2;
	private int nbJoueurs=1;
	public final int nbActionsmax=5;
	public int nbActions = nbActionsmax;
	public Joueur[] joueurs = new Joueur[this.nbJoueurs];
	Random r = new Random();

	
	public Model() {
		this.grille = new Cellule[dimGrilleAbsc][dimGrilleOrd];
		
		 String  DimAbscCarteFile;
		 DimAbscCarteFile = fluxFichier.readLine();
		 this.dimGrilleAbscV2= Integer.parseInt(DimAbscCarteFile);
		 
		 String  DimOrdCarteFile;
		 DimOrdCarteFile = fluxFichier.readLine();
		 this.dimGrilleOrdV2= Integer.parseInt(DimOrdCarteFile);		
		init();//Lance l'initialisation
		//initV2();//Lance l'initialisation
		fluxFichier.close();
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
				// donne un état terre au cellules "centrales"
				if (x > dimGrilleAbsc / 4 && x < (dimGrilleAbsc * 3) / 4 && y > dimGrilleOrd / 4
						&& y < (dimGrilleOrd * 3) / 4) {
					grille[x][y].setTypeTerrain(typeTerrain.terre);
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
                                grille[x][y].setTypeTerrain(typeTerrain.mer); // redessine aléatoirement les contours de l'ile
                         // }
                    }
                }
			}
		}
		//initialise les joueurs en les mettant dans les cellules de la grille et les rajoutant dans un tab joueurs[] avec leurs positions respectives.
		initJoueurs(this.nbJoueurs);
		currentPlayer=joueurs[0].getCellule();		
		for (Joueur j : joueurs) {
			grille[j.getCellule().getAbsc()][j.getCellule().getOrd()].setJoueur(true);
		}
		//initJoueursV2(this.nbJoueurs);
		//currentPlayerV2=joueurs[0];
		
	}
	/**
	 * Fonction utilitaire pour le constructeur:
	 * initialise la grille de cellules du modèle en récuperant:
	 *  -les positions des joueurs(depuis la classe Joueur)
	 *  -la taille de la grille dans dimGrilleAbsc et dimGrilleOrd (depuis un fichier carte_ileX.txt)
	 *  -le type de terrain de chacune des cases (depuis un fichier carte_ileX.txt) 
	 */
	private void initV2() {
		
		
		 int typeTerrainCelFile;
		 
		
		for (int x = 0; x < dimGrilleAbscV2; x++) {
			for (int y = 0; y < dimGrilleOrdV2; y++) {
				typeTerrainCelFile = fluxFichier.read();
				// crée et donne des coords à une cellule
				grille[x][y] = new Cellule(this, x , y );
				// donne sont type de terrain initial à la cellule
				if(typeTerrainCelFile==0)
				grille[x][y].setTypeTerrain(typeTerrain.mer);
				if(typeTerrainCelFile==1)
					grille[x][y].setTypeTerrain(typeTerrain.terre);
				if(typeTerrainCelFile==2)
					grille[x][y].setTypeTerrain(typeTerrain.inonde);
				// donne un état terre au cellules "centrales"					
			}
		}
		//initialise les joueurs en les mettant dans les cellules de la grille et les rajoutant dans un tab joueurs[] avec leurs positions respectives.		
		initJoueursV2(this.nbJoueurs);
		currentPlayerV2=joueurs[0];
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
		//changeJoueurV2();
	
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
			if (c.getTypeTerrain()== typeTerrain.mer )
				cpt++;
		}
		if(grille[x][y].getTypeTerrain() != typeTerrain.mer && cpt>0)
			if (t < 0.09)grille[x][y].setTypeTerrain(typeTerrain.inonde);
	}
	
	/**
	 * Submerge les cellules inondés
	 * @param x = coord Absc
	 * @param y = coord Ord
	 */
	public void submerge(int x,int y) {
		if(grille[x][y].getTypeTerrain() == typeTerrain.inonde)
			grille[x][y].setTypeTerrain(typeTerrain.mer);
		
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
	
	public int getNumJoueurV2() {
		for(int i=0;i<joueurs.length;i++) {
			if(joueurs[i].getAbsc()==currentPlayerV2.getAbsc() && joueurs[i].getOrd()==currentPlayerV2.getOrd()) {
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
			if(v[0].getTypeTerrain()!=typeTerrain.mer && !v[0].getJoueur()) {
				this.currentPlayer=v[0];
				nbActions--;
		    }
			break;
		case right:
			if(v[1].getTypeTerrain()!=typeTerrain.mer && !v[1].getJoueur()) {
				this.currentPlayer=v[1];
			    nbActions--;
			}
			break;
		case down:
			if(v[2].getTypeTerrain()!=typeTerrain.mer && !v[2].getJoueur()) {
				this.currentPlayer=v[2];

				nbActions--;
			}
			break;
		case left:
			if(v[3].getTypeTerrain()!=typeTerrain.mer && !v[3].getJoueur()) {
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
	
	public void moveV2(Direction d) {
		int numJoueur=getNumJoueur();
		Cellule currentP = new Cellule(this, currentPlayerV2.getAbsc(), currentPlayerV2.getOrd());
		if(nbActions>0) {
		Cellule [] v=currentP.voisines(grille); //on récupère les voisines !!
		switch (d) {
		case up:
			if(v[0].getTypeTerrain()!=typeTerrain.mer && !v[0].getJoueurV2()) {
				this.currentPlayerV2.setOrd(currentPlayerV2.getOrd()-1);
				nbActions--;
		    }
			break;
		case right:
			if(v[1].getTypeTerrain()!=typeTerrain.mer && !v[1].getJoueurV2()) {
				this.currentPlayerV2.setAbsc(currentPlayerV2.getAbsc()+1);
			    nbActions--;
			}
			break;
		case down:
			if(v[2].getTypeTerrain()!=typeTerrain.mer && !v[2].getJoueurV2()) {
				this.currentPlayerV2.setOrd(currentPlayerV2.getOrd()+1);
				nbActions--;
			}
			break;
		case left:
			if(v[3].getTypeTerrain()!=typeTerrain.mer && !v[3].getJoueurV2()) {
				this.currentPlayerV2.setAbsc(currentPlayerV2.getAbsc()-1);
				nbActions--;
			}
			break;
		default:
			break;
		}
		//this.joueurs[numJoueur]==currentPlayerV2; //inutile non ?..
		notifyObservers();
		//System.out.println(nbActions); //affiche le nb d'actions restantes
		}
	}
	/**
	 * Asseche la cellule du currentPlayer
	 */
	//remplacer currentPlayer par currentPlayerV2
	public void dry() {
		if(currentPlayer.getTypeTerrain()==typeTerrain.inonde) {
		currentPlayer.setTypeTerrain(typeTerrain.terre);
		nbActions--;
		}
		notifyObservers();
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
	
	//ne place pas au bon endroit celon la carte..
	private void initJoueursV2(int nbJ) {
		
		if(nbJ>0)
			joueurs[0].setAbsc(dimGrilleAbsc/2);
			joueurs[0].setOrd((dimGrilleOrd/2)-1);
		if(nbJ>1)
			joueurs[1].setAbsc(dimGrilleAbsc/2);
			joueurs[1].setOrd((dimGrilleOrd/2)+1);
		if(nbJ>2)
			joueurs[2].setAbsc((dimGrilleAbsc/2)+1);
			joueurs[2].setOrd(dimGrilleOrd/2);
		if(nbJ>3)
			joueurs[3].setAbsc((dimGrilleAbsc/2)-1);
			joueurs[3].setOrd(dimGrilleOrd/2);
		if(nbJ>4)
			joueurs[4].setAbsc((dimGrilleAbsc/2)+1);
			joueurs[4].setOrd((dimGrilleOrd/2)+1);
		if(nbJ>5)
			joueurs[5].setAbsc((dimGrilleAbsc/2)-1);
			joueurs[5].setOrd((dimGrilleOrd/2)-1);
		if(nbJ>6)
			joueurs[6].setAbsc((dimGrilleAbsc/2)-1);
			joueurs[6].setOrd((dimGrilleOrd/2)+1);
		if(nbJ>7)
			joueurs[7].setAbsc((dimGrilleAbsc/2)+1);
			joueurs[7].setOrd((dimGrilleOrd/2)-1);
		for(int i=0;i<nbJ;i++) {
			//joueurs[i]=new Joueur();
			
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
		
		public void changeJoueurV2() {
			nbActions=nbActionsmax;
			
			if(currentPlayerV2==joueurs[nbJoueurs-1]) {
				this.currentPlayerV2=joueurs[0];
			System.out.println("42");
			}
			else {
				for(int i=0;i<joueurs.length-1;i++) {
					if(joueurs[i]==currentPlayerV2) {
						this.currentPlayerV2=joueurs[i+1];
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
