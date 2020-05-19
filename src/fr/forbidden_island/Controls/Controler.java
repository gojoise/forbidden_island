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
		modele.inonde3();
		modele.notifyObservers();
		System.out.println(42); // teste d'une action bidon pour notre bouton => affiche 42 sur la console et pas la fenetre.
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			modele.joueur(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			modele.joueur(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			modele.joueur(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			modele.joueur(Direction.right);
			break;
		case KeyEvent.VK_SPACE:	//assécher
			//TODO
		}
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
