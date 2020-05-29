package fr.forbidden_island.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.core.Model;
/*
 * Correspond a' VueGrille ou autre e'lement de la vue, nom a' modifier pour e'tre plus explicite*
 *
 */
public class PlayerInfo extends JPanel implements Observer{

	private Model modele;
	private JLabel info1;
	private JLabel info2;
	
	public PlayerInfo(Model mod,String initial) {
		this.modele = mod;
		this.setLayout(new FlowLayout());
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute a' observers
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
	    /*
	     * Créer une sous boite où on y met les infos des artéfacts que possède le joueur
	     */
	    this.add(info1);
	    this.add(info2);
	    this.setBorder(BorderFactory.createTitledBorder("Informations du personnage"));
	    this.setPreferredSize(new Dimension(220, 30));
	}
	//remplace par "int num=modele.getNumJoueurV2();"
	public void update() {
		int num=modele.getNumJoueurV2();
		this.info1.setText(modele.joueurs[num].getName()); //change le "nom" du joueur a' chaque fin de tour.
		
		this.info2.setText("Mouvement restants: "+ modele.getNbActionsString()); //affiche le nbActions du joueur 
	}
}
