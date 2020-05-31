package fr.forbidden_island.Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.forbidden_island.core.Model;
import fr.forbidden_island.data.Joueur;

public class Controler implements ActionListener,KeyListener{
	Model modele;
	//
	public Controler(Model mod) {
		this.modele=mod;
	}
	/*
	 *Bouton : Inonder trois zones tire'es au hasard, avec deux cas a' conside'rer :
- Si la zone correspondante est dans la situation "normale" , elle devient "inonde'e". Cette
zone est alors en sursis, 
- Si la zone correspondante est de'ja' dans l'e'tat "inonde'e" ou "submerge'e", elle devient
"submerge'e".
	 */
	public void actionPerformed(ActionEvent e) {
		modele.endTurn();
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			modele.moveV2(Direction.up); //aller en haut 
			break;
		case KeyEvent.VK_DOWN:
			modele.moveV2(Direction.down);//aller en bas
			break;
		case KeyEvent.VK_LEFT:
			modele.moveV2(Direction.left);//aller a' gauche
			break;
		case KeyEvent.VK_RIGHT:
			modele.moveV2(Direction.right);//aller a' droite
			break;
		case KeyEvent.VK_A:	//asse'cher
			modele.dry();
			break;
		case KeyEvent.VK_R:	//ramasseArtefact
			modele.ramasseArtefact();
			break;
		}
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
