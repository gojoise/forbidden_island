package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import fr.forbidden_island.core.Model;

/**
 * 
 * @author Mathieu & Arthur
 * Correspond � CVue de Conway.java
 *
 */


public class Fenetre extends JFrame {
	private IslandView view ; // /!\
	/**
	 * Constructeur de la Fenetre
	 */
	public Fenetre(Model mod){
		this.view=new IslandView(mod);
		this.setTitle("L'ile interdite");
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new FlowLayout());
		/*
		*Fait une r�f�rence vers this.view => les updates via repain() font effet car on travaille sur view qui est r�f�renc� par setContentPane
		*/
		this.setContentPane(this.view); //Probl�matique pour ajouter d'autres composants
		this.add(new PlayerView(mod));
		this.add(new CommandsView(mod));
		this.setVisible(true);
		
		//mets le rectangle plus bas
		this.view.setPosX(500);
	    this.view.setPosY(500);
	    this.view.repaint(); //fais la mise � jour directement sur this.view
	    


	}
}
