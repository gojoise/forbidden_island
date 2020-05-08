package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * Correspond à VueGrille ou autre élement de la vue, nom à modifier pour être plus explicite*
 *
 */
class IslandView extends JPanel {
	private int posX = -50;
	private int posY = -50;
	

	public void paintComponent(Graphics g){
		super.repaint();
		g.setColor(Color.red);
		g.drawRect(posX, posY, 200, 40);
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
}
