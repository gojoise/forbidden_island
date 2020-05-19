package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.core.Model;
/*
 * Correspond � VueGrille ou autre �lement de la vue, nom � modifier pour �tre plus explicite*
 *
 */
public class PlayerInfo extends JLabel implements Observer{

	private Model modele;
	
	public PlayerInfo(Model mod,String initial) {
		this.modele = mod;
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute � observers
		//Construction du label
		Font police = new Font("Arial", Font.BOLD, 30); 
		this.setFont(police);  
	    this.setForeground(Color.blue);  
	    this.setHorizontalAlignment(JLabel.CENTER);
	    this.setText(initial);
	    this.setPreferredSize(new Dimension(220, 20));
	}
	public void update() {
		// TODO Auto-generated method stub
	}
}
