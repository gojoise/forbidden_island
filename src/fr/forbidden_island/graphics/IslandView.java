package fr.forbidden_island.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.core.Model;
import fr.forbidden_island.data.Ressources;
import fr.forbidden_island.data.Statut;
import fr.forbidden_island.data.TypeTerrain;;
/*
 * Correspond  la vue de la grille/plateau de jeu
 *
 */
public class IslandView extends JPanel implements Observer{
	private Model modele;
	private Ressources ress;

	public IslandView(Model mod,Ressources r) {
		this.modele = mod;
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute cette vue aux observers du modele
		this.setPreferredSize(new Dimension(1080, 620));
		this.ress=r;
	}
	
	public Model getModele() {return this.modele;}

	/**
	 * Fonction pour dessiner la grille avec des images differentes celon les proprits des cellules de la grille.
	 * De plus dessine des images pour les joueurs et artefacts par dessus les cellules 
	 */
	public void paintComponent(Graphics g) {
		super.repaint();
		for(int x=0;x<modele.getDimGrilleAbsc();x++) {
			for(int y=0;y<modele.getDimGrilleOrd();y++) {
				//Cellules de mer/submerges
				if(modele.getGrille()[x][y].getTypeTerrain()==TypeTerrain.mer) {
					g.drawImage(ress.getImage(0), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				}
				//Cellules de terre
				else if(modele.getGrille()[x][y].getTypeTerrain()==TypeTerrain.terre) {
					g.drawImage(ress.getImage(1), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				}
				//Cellules inondes
				else if(modele.getGrille()[x][y].getTypeTerrain()==TypeTerrain.inonde) {
					g.drawImage(ress.getImage(2), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				}
				//rajout image heliport au centre
				if(modele.getGrille()[x][y].getAbsc()==modele.getDimGrilleAbsc()/2 && modele.getGrille()[x][y].getOrd() == modele.getDimGrilleOrd()/2 && modele.getGrille()[x][y].getTypeTerrain()!=TypeTerrain.mer) {
					g.drawImage(ress.getImage(4), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				}
			}
		}
		//dessin des artefacts non noyés
		for(int j=0;j<modele.getNbArtefacts();j++) {
			if(!modele.artefacts[j].hasProprio() && modele.getGrille()[modele.artefacts[j].getAbsc()][modele.artefacts[j].getOrd()].getTypeTerrain()!=TypeTerrain.mer)
			g.drawImage(ress.getImage(j+5), modele.artefacts[j].getAbsc()*modele.getSize(), modele.artefacts[j].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
		}
		//dessin des joueurs vivants
		for(int i=0;i<modele.getNbJoueurs();i++) {
			if(modele.joueurs[i].getStatut()==Statut.vivant)
				g.drawImage(ress.getImage(i+9), modele.joueurs[i].getAbsc()*modele.getSize(), modele.joueurs[i].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);	
		}
	}
	/**
	 * Ce update est appell  chaque appel de notifyObservers de model
	 * this.repaint() rapelle paintComponent()
	 */

public void update() {
	this.repaint();
}


}