package fr.forbidden_island.Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.forbidden_island.core.Model;


public class Controler implements ActionListener,KeyListener{
	Model modele;

	public Controler(Model mod) {
		this.modele=mod;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		modele.endTurn();
	}
	
	//demandé par l'interface
	public void keyTyped(KeyEvent e) {		
	}
	
	/**
	 * ici se trouve les différentes touches utilisées dans le jeu
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			modele.move(Direction.up); //aller en haut 
			break;
		case KeyEvent.VK_DOWN:
			modele.move(Direction.down);//aller en bas
			break;
		case KeyEvent.VK_LEFT:
			modele.move(Direction.left);//aller a' gauche
			break;
		case KeyEvent.VK_RIGHT:
			modele.move(Direction.right);//aller a' droite
			break;
		case KeyEvent.VK_A:	//asse'cher
			modele.dry();
			break;
		case KeyEvent.VK_R:	//ramasseArtefact
			modele.ramasseArtefact();
			break;
		}
		
	}
	
	//demandé par l'interface
	public void keyReleased(KeyEvent e) {
	}

}
