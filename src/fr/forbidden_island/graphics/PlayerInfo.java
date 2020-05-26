package fr.forbidden_island.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.core.Model;
/*
 * Correspond à VueGrille ou autre élement de la vue, nom à modifier pour être plus explicite*
 *
 */
public class PlayerInfo extends JPanel implements Observer{

	private Model modele;
	private JLabel info1;
	private JLabel info2;
	
	public PlayerInfo(Model mod,String initial) {
		this.modele = mod;
		this.setLayout(new BorderLayout());
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute à observers
		//Construction du label
		Font police = new Font("Arial", Font.BOLD, 30);
		Font police2 = new Font("Arial", Font.BOLD, 18);
		this.info1=new JLabel(initial);
		this.info1.setFont(police);  
	    this.info1.setForeground(Color.blue);  
	    this.info1.setHorizontalAlignment(JLabel.CENTER);
		this.info2=new JLabel(initial);
		this.info2.setFont(police2);  
	    this.info2.setForeground(Color.blue); 
	    this.info2.setHorizontalAlignment(JLabel.CENTER);
	    this.info2.setText(initial);
	    this.add(info1,BorderLayout.NORTH);
	    this.add(info2,BorderLayout.CENTER);
	    this.setPreferredSize(new Dimension(220, 30));
	}
	//remplace par "int num=modele.getNumJoueurV2();"
	public void update() {
		//int num=modele.getNumJoueur();
		int num=modele.getNumJoueurV2();
		this.info1.setText(modele.joueurs[num].getName()); //change le "nom" du joueur à chaque fin de tour.
		
		this.info2.setText("Mouvement restants: "+ modele.getNbActionsString()); //affiche le nbActions du joueur 
	}
}
