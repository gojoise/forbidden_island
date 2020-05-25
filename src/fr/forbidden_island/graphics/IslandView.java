package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.core.Model;
import fr.forbidden_island.data.typeTerrain;;
/*
 * Correspond � VueGrille ou autre �lement de la vue, nom � modifier pour �tre plus explicite*
 *
 */
public class IslandView extends JPanel implements Observer{
	private Model modele;

	public IslandView(Model mod) {
		this.modele = mod;
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute � observers 
		this.setPreferredSize(new Dimension(1080, 620));

	}
	public Model getModele() {return this.modele;}

	/**fonction pour dessiner la grille avec des couleurs diff�rentes celon les propri�t�es des cellules de la grille.
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.repaint();
		//Cellule [][] cellules = modele.getGrille();
		for(int x=0;x<modele.getDimGrilleAbsc();x++) {
			for(int y=0;y<modele.getDimGrilleOrd();y++) {
		
				if(modele.getGrille()[x][y].getTypeTerrain()==typeTerrain.mer) {
					g.drawRect(x, y, modele.getSize(), modele.getSize());
					g.fillRect(x, y, modele.getSize(), modele.getSize());
					g.setColor(Color.blue);
				}
				if(modele.getGrille()[x][y].getTypeTerrain()==typeTerrain.inonde) {
					g.drawRect(x, y, modele.getSize(), modele.getSize());
					g.fillRect(x, y, modele.getSize(), modele.getSize());
					g.setColor(Color.CYAN);
				} 
				if(modele.getGrille()[x][y].getTypeTerrain()==typeTerrain.terre){
					g.drawRect(x, y, modele.getSize(), modele.getSize());
					g.fillRect(x, y, modele.getSize(), modele.getSize());
					g.setColor(Color.green);  
				}
			}
			for(int i=0;i<modele.getNbJoueurs();i++) {
					g.drawRect(modele.joueurs[i].getAbsc(), modele.joueurs[i].getOrd(), modele.getSize(), modele.getSize());
					g.fillRect(modele.joueurs[i].getAbsc(), modele.joueurs[i].getOrd(), modele.getSize(), modele.getSize());
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






public void update() {
	this.repaint();
}


}
