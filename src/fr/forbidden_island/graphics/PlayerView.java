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
public class PlayerView extends JPanel implements Observer{
	private int posX ;
	private int posY ;
	
	private Model modele;
	
	public PlayerView(Model mod) {
		// TODO Auto-generated constructor stub
		this.modele = mod;
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute à observers 
		this.posX=10;
		this.posY=10;
		this.setBackground(Color.yellow);
		this.setPreferredSize(new Dimension(100, 100));
		

		
	}

	public void paintComponent(Graphics g) {
		super.repaint();
		g.setColor(Color.BLUE);
		g.drawString("Joueur :"+1, posX, posY);
	}
	


	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}



	public void update() {
		// TODO Auto-generated method stub
		
	}
}
