package fr.forbidden_island.Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.forbidden_island.core.Model;

public class Controler implements ActionListener{
	Model modele;
	
	public Controler(Model mod) {
		this.modele=mod;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(42); // teste d'une action bidon pour notre bouton => affiche 42 sur la console et pas la fenetre.
		
	}

}
