package fr.forbidden_island.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.forbidden_island.core.Model;
import fr.forbidden_island.core.Observer;
import fr.forbidden_island.data.typeTerrain;

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

	/**
	 * Constructeur de la Fenetre
	 */
	public Fenetre(Model mod) {
		// Init des composants
		this.view = new IslandView(mod);
		this.bouton = new CommandsView(mod);
		this.Pview = new PlayerInfo(mod, "");
		this.Pview.update();
		mod.addObserver(this);

		// Init fenetre
		this.setTitle("L'ile interdite");
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);


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
		this.setVisible(true);

	}


	private void purgeInfo(String nbJ) {
		JOptionPane.showMessageDialog(null, "Le joueur N�"+nbJ+" a perdu RIP !", "Un joueur a perdu !", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void endInfo() {
		if (view.getModele().ending()) {
			JOptionPane.showMessageDialog(null, "La partie est finie !!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		}
	}


	public void update() {
		int nbJ=view.getModele().getNbJoueurs();
		for(int i=0;i<nbJ;i++) {
			if(!(view.getModele().joueurs[i].estElimine())) {
				if(view.getModele().getGrille()[view.getModele().joueurs[i].getAbsc()][view.getModele().joueurs[i].getOrd()].getTypeTerrain()==typeTerrain.mer )//Rajouter une condition pour ne pas tester les joueurs e'limine's
					purgeInfo(Integer.toString(i+1));
			}
		}
		endInfo();
		this.repaint();
	}
}
