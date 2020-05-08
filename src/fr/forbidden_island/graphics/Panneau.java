package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

class Panneau extends JPanel {
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
