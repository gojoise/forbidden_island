package fr.forbidden_island.Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.forbidden_island.core.Model;

public class Controler implements ActionListener{
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

}
