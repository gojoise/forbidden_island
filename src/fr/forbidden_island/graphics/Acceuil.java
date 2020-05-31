package fr.forbidden_island.graphics;

import javax.swing.JOptionPane;

import fr.forbidden_island.core.Model;

public class Acceuil extends JOptionPane{
	
	public Acceuil(Model mod,Fenetre f) {
		super();		
		int option = this.showConfirmDialog(null, "Voulez-vous lancer le jeu ?", "Lancement du jeu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
		if(option == JOptionPane.OK_OPTION){
			f.initGame(mod);
			f.setVisible(true);
		}else {
			f.setVisible(true);
			f.dispose();
		}
	}

}
