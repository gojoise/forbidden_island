package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.core.Model;
import fr.forbidden_island.data.Ressources;
import fr.forbidden_island.data.typeTerrain;;
/*
 * Correspond à VueGrille ou autre élement de la vue, nom à modifier pour être plus explicite*
 *
 */
public class IslandView extends JPanel implements Observer{
	private Model modele;
	private Ressources r;

	public IslandView(Model mod) {
		this.modele = mod;
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute à observers 
		this.setPreferredSize(new Dimension(1080, 620));
		r=new Ressources();
	}
	
	public Model getModele() {return this.modele;}

	/**fonction pour dessiner la grille avec des couleurs différentes celon les propriétées des cellules de la grille.
	 * 
	 */
	public void pre_paintComponent(Graphics g) {
		super.repaint();
		//Cellule [][] cellules = modele.getGrille();
		for(int x=0;x<modele.getDimGrilleAbsc();x++) {
			for(int y=0;y<modele.getDimGrilleOrd();y++) {
		
				if(modele.getGrille()[x][y].getTypeTerrain()==typeTerrain.mer) {
					g.drawImage(r.getImage(0), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				}
				else if(modele.getGrille()[x][y].getTypeTerrain()==typeTerrain.inonde) {
					g.drawImage(r.getImage(2), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				} 
				else if(modele.getGrille()[x][y].getTypeTerrain()==typeTerrain.terre){
					g.drawImage(r.getImage(1), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				}
			}
			for(int i=0;i<modele.getNbJoueurs();i++) {
				g.drawImage(r.getImage(3), modele.joueurs[i].getAbsc()*modele.getSize(), modele.joueurs[i].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
					if(i==0)
						g.setColor(Color.magenta);
					if(i==1 )
						g.setColor(Color.black);
					if(i==2)
						g.setColor(Color.darkGray);
					if(i==3)
						g.setColor(Color.lightGray);
					if(i==4)
						g.setColor(Color.RED);
					if(i==5)
						g.setColor(Color.orange);
					if(i==6)
						g.setColor(Color.yellow);
					if(i==7)
						g.setColor(Color.PINK);
			}
		}
		
	
}
	
	public void paintComponent(Graphics g) {
		super.repaint();
		
		for(int x=0;x<modele.getDimGrilleAbsc();x++) {
			for(int y=0;y<modele.getDimGrilleOrd();y++) {
				if(modele.getGrille()[x][y].getTypeTerrain()==typeTerrain.mer) {
					g.drawImage(r.getImage(0), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				}
				else if(modele.getGrille()[x][y].getTypeTerrain()==typeTerrain.terre) {
					g.drawImage(r.getImage(1), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				}
				else if(modele.getGrille()[x][y].getTypeTerrain()==typeTerrain.inonde) {
					g.drawImage(r.getImage(2), modele.getGrille()[x][y].getAbsc()*modele.getSize(), modele.getGrille()[x][y].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
				}
				else {
					for(int i=0;i<modele.getNbJoueurs();i++) {
						g.drawImage(r.getImage(3), modele.joueurs[i].getAbsc()*modele.getSize(), modele.joueurs[i].getOrd()*modele.getSize(), modele.getSize(), modele.getSize(),this);
					}
				}
			}
		}
	}





public void update() {
	this.repaint();
}


}
