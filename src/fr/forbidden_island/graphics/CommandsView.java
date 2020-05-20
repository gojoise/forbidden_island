package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JButton;
import fr.forbidden_island.Controls.Controler;
import fr.forbidden_island.core.Model;

public class CommandsView extends JButton {

	public CommandsView(Model mod){
		Controler ctrl = new Controler(mod);
		/** Enregistrement du contrôleur comme auditeur du bouton. */
		this.addActionListener(ctrl);
		this.addKeyListener(ctrl);
		this.setText("fin de tour !");
	}




}