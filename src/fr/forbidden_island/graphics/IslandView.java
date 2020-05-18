package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.core.Model;
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
		this.setPreferredSize(new Dimension(400, 400));
		this.setBackground(Color.white);
	}
	
	public void paintComponent() {
		//TODO
	}


	



	public void update() {
		
	}
}
