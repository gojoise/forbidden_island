package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.Controls.Controler;
import fr.forbidden_island.core.Model;
import fr.forbidden_island.data.*;
/*
 * Correspond à VueGrille ou autre élement de la vue, nom à modifier pour être plus explicite*
 *
 */
public class IslandView extends JPanel implements Observer{
	private Model modele;

	public IslandView(Model mod) {
		this.modele = mod;
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute à observers 
		this.setPreferredSize(new Dimension(1080, 620));

	}
	public Model getModele() {return this.modele;}

	/**fonction pour dessiner la grille avec des couleurs différentes celon les propriétées des cellules de la grille.
	 * 
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.repaint();
		Cellule [][] cellules = modele.getGrille();
		for(Cellule [] lignes: cellules) {
			for(Cellule c : lignes) {
				if(c.getMer()) {
					g.fillRect(c.getAbsc(), c.getOrd(), c.getSize(), c.getSize());
					g.setColor(Color.blue);
				}
				else{
					if(c.getJoueur()) { 
						g.fillRect(c.getAbsc(), c.getOrd(), c.getSize(), c.getSize());
					for(int i=0;i<modele.getNbJoueurs();i++) {
						if(i==0)
							g.setColor(Color.magenta);
						if(i==1 && i>0)
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
				else if(c.getEstInonde()) {
					g.fillRect(c.getAbsc(), c.getOrd(), c.getSize(), c.getSize());
					g.setColor(Color.CYAN);
				} 
				else{
					g.fillRect(c.getAbsc(), c.getOrd(), c.getSize(), c.getSize());
					g.setColor(Color.green);  
				}
			}
		}
	}
}






public void update() {
	this.repaint();
}


}
