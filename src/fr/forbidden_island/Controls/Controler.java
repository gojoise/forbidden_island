package fr.forbidden_island.Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.forbidden_island.core.Model;
import fr.forbidden_island.data.Joueur;

public class Controler implements ActionListener,KeyListener{
	Model modele;
	
	public Controler(Model mod) {
		this.modele=mod;
	}
	/*
	 *Bouton : Inonder trois zones tir´ees au hasard, avec deux cas `a consid´erer :
— Si la zone correspondante est dans la situation « normale », elle devient « inond´ee ». Cette
zone est alors en sursis, 
— Si la zone correspondante est d´ej`a dans l’´etat « inond´ee » ou « submerg´ee », elle devient
« submerg´ee ».
	 */
	public void actionPerformed(ActionEvent e) {
		modele.endTurn();
		modele.notifyObservers();
		
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
			modele.moveV2(Direction.left);//aller à gauche
			break;
		case KeyEvent.VK_RIGHT:
			modele.moveV2(Direction.right);//aller à droite
			break;
		case KeyEvent.VK_ENTER:	//assécher
			modele.dry();
			break;
		}
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
