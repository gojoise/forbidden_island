package fr.forbidden_island.graphics;

import javax.swing.JButton;
import fr.forbidden_island.Controls.Controler;
import fr.forbidden_island.core.Model;

public class CommandsView extends JButton {

	public CommandsView(Model mod){
		Controler ctrl = new Controler(mod);
		/** Enregistrement du contro'leur comme auditeur du bouton. */
		this.addActionListener(ctrl);
		this.addKeyListener(ctrl);
		this.setText("fin de tour !");
	}




}