package fr.forbidden_island.graphics;

import javax.swing.JFrame;
import java.awt.Graphics;


public class Fenetre extends JFrame {
	private Panneau pan = new Panneau();
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
