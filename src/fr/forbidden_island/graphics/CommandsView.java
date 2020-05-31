package fr.forbidden_island.graphics;

import javax.swing.JButton;
import fr.forbidden_island.Controls.Controler;
import fr.forbidden_island.core.Model;
/**
 * Classe affichant le bouton et servant de lien avec le controleur
 */
public class CommandsView extends JButton {

	public CommandsView(Model mod){
		Controler ctrl = new Controler(mod);
		/** Enregistrement du controleur comme auditeur du bouton. */
		this.addActionListener(ctrl);//Le controleur ecoute les clics du bouton
		this.addKeyListener(ctrl);//Le controleur ecoute les frappes de clavier
		this.setText("Fin de tour !");//Affiche les texte du boutton
	}
}