package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.core.Model;
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
		this.setPreferredSize(new Dimension(400, 400));
		this.setBackground(Color.white);
	}
	
	public void paintComponent() {
		//TODO
	}


	



	public void update() {
		
	}
}
