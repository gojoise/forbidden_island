package fr.forbidden_island.graphics;

import javax.swing.JFrame;
import java.awt.Graphics;

/**
 * 
 * @author Mathieu & Arthur
 * Correspond à CVue de Conway.java
 *
 */


public class Fenetre extends JFrame {
	private IslandView pan = new IslandView();
	/**
	 * Constructeur de la Fenetre
	 */
	public Fenetre(){
		this.setTitle("L'ile interdite");
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setContentPane(pan);
		
		this.setVisible(true);
		pan.setPosX(500);
	    pan.setPosY(500);
	    pan.repaint();

	}
}
