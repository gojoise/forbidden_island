package fr.forbidden_island.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.forbidden_island.core.Model;
import fr.forbidden_island.core.Observer;
import fr.forbidden_island.data.Artefact;
import fr.forbidden_island.data.Ressources;
import fr.forbidden_island.data.Statut;
import fr.forbidden_island.data.TypeTerrain;

/**
 * 
 * @author Mathieu & Arthur Correspond a' CVue de Conway.java
 *
 */

public class Fenetre extends JFrame implements Observer{
	private IslandView view;
	private CommandsView bouton;
	private JPanel container = new JPanel();
	private PlayerInfo Pview;
	private JPanel PlayerInterface = new JPanel();
	private JPanel Game = new JPanel();
	private Ressources r=new Ressources();

	/**
	 * Constructeur de la Fenetre
	 */
	public Fenetre() {
		// Init fenetre
		this.setTitle("L'ile interdite");
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
	}
	/**
	 * Initialise les comosants de la fenetre pour le jeu
	 * @param mod le modele passé en paramètre du constructeur
	 */
	public void initGame(Model mod) {
		// Init des composants
		this.view = new IslandView(mod,r);
		this.bouton = new CommandsView(mod);
		this.Pview = new PlayerInfo(mod, "",r);
		this.Pview.update();
		mod.addObserver(this);

		// Init composants pour fenetre
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.setPreferredSize(new Dimension(1280, 720));
		PlayerInterface.setBackground(Color.gray);
		PlayerInterface.setLayout(new BorderLayout());
		PlayerInterface.setPreferredSize(new Dimension(212, 200));
		Game.setBackground(Color.white);
		Game.setLayout(new BorderLayout());
		Game.setPreferredSize(new Dimension(1080, 620));
		Game.add(view, BorderLayout.CENTER);
		container.add(Game, BorderLayout.CENTER);
		container.add(PlayerInterface, BorderLayout.EAST);
		PlayerInterface.add(bouton, BorderLayout.SOUTH);
		PlayerInterface.add(Pview, BorderLayout.CENTER);

		this.setContentPane(container);
		
	}


	private void purgeInfo(String nbJ) {
		JOptionPane.showMessageDialog(null, "Le joueur N°"+nbJ+" a perdu RIP !", "Un joueur a perdu !", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void endInfo() {
		if (view.getModele().getEndOfTheGame()) {
			JOptionPane.showMessageDialog(null, "La partie est finie !!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			//new Acceuil(new Model(),this); //Idée pour relancer la partie 
		}
	}
	
	private void winInfo(String nbJ) {
			JOptionPane.showMessageDialog(null, "Le joueur n°"+nbJ+" s'en est sorti !", "Un joueur a réussi à s'enfuir!", JOptionPane.INFORMATION_MESSAGE);
	}


	public void update() {
		if(view!=null) {
			for(int i=0;i<view.getModele().getNbJoueurs();i++) {
				if(view.getModele().joueurs[i].getStatut()==Statut.mourant) 				
					purgeInfo(Integer.toString(i+1));//quand le joueur est en train de mourir, affiche un message d'information
				else if(view.getModele().joueurs[i].getStatut()==Statut.fuyant)
					winInfo(Integer.toString(i+1));//quand le joueur est en train de ganger, affiche un message d'information
			}			
			this.repaint();
			endInfo();
		}	
	}
}
