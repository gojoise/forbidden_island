package fr.forbidden_island.graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.forbidden_island.Controls.Controler;
import fr.forbidden_island.core.Model;

public class CommandsView extends JPanel {
	private Model modele;
	public CommandsView(Model mod) {
		this.modele = mod;
		JButton boutonAvance = new JButton("Action !");
		this.add(boutonAvance);
		
		Controler ctrl = new Controler(modele);
		/** Le controler écoute le bouton */
		boutonAvance.addActionListener(ctrl);
	}
	
}
