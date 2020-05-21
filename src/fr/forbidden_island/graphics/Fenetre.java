package fr.forbidden_island.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.forbidden_island.core.Model;
import fr.forbidden_island.core.Observer;

/**
 * 
 * @author Mathieu & Arthur Correspond à CVue de Conway.java
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
		PlayerInterface.setPreferredSize(new Dimension(200, 200));
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
	
	
	public void gameOver() {
		this.getContentPane().removeAll();// Utile pour supprimer l'ancien écran de fin pour éviter de supperposer les textes
		this.remove(this);
		JLabel label = new JLabel("Game over *_*");
		JLabel label2 = new JLabel("Press Space to reset"); // On définit un nouveau message
		label.setFont(new Font("Verdana", 1, 20));
		label2.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setVerticalAlignment(SwingConstants.BOTTOM); // on le place en bas
		label.setSize(this.getSize());
		label2.setSize(this.getSize());
		this.getContentPane().add(label);
		this.getContentPane().add(label2);// on l'ajoute à l'interface graphique
		this.repaint();
		}
	
	
	public void update() {
		if(view.getModele().currentPlayer.getMer())
		gameOver();
		this.repaint();
	}
}
