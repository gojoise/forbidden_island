package fr.forbidden_island.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.forbidden_island.core.Observer;
import fr.forbidden_island.data.Artefact;
import fr.forbidden_island.data.Ressources;
import fr.forbidden_island.core.Model;
/*
 * Correspond a' VueGrille ou autre e'lement de la vue, nom a' modifier pour e'tre plus explicite*
 *
 */
public class PlayerInfo extends JPanel implements Observer{

	private Model modele;
	private JLabel info1;
	private JLabel info2;
	private JPanel info3;
	private JLabel [] items= new JLabel[4];
	private Ressources ress;
	
	public PlayerInfo(Model mod,String initial,Ressources r) {
		this.modele = mod;
		this.setLayout(new FlowLayout());
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute a' observers
		//Construction du label
		Font police = new Font("Arial", Font.BOLD, 30);
		Font police2 = new Font("Arial", Font.BOLD, 18);
		this.info1=new JLabel(initial);
		this.info1.setFont(police);  
	    this.info1.setForeground(Color.blue);  
	    this.info1.setHorizontalAlignment(JLabel.CENTER);
		this.info2=new JLabel(initial);
		this.info2.setFont(police2);  
	    this.info2.setForeground(Color.blue); 
	    this.info2.setHorizontalAlignment(JLabel.CENTER);
	    this.info2.setText(initial);
	    /*
	     * Créer une sous boite où on y met les infos des artéfacts que possède le joueur
	     */
	    this.add(info1);
	    this.add(info2);
	    createPanel();
	    this.add(info3);
	    this.setBorder(BorderFactory.createTitledBorder("Informations du personnage"));
	    this.setPreferredSize(new Dimension(220, 30));
	    ress=r;
	}
	
	public void createPanel() {
		this.info3= new JPanel(new FlowLayout());
		this.info3.setBorder(BorderFactory.createTitledBorder("Inventaire :"));
		this.info3.setPreferredSize(new Dimension(200, 80));
		items[0]=new JLabel();
		items[1]=new JLabel();
		items[3]=new JLabel();
		items[2]=new JLabel();
		for(JLabel labels:items) {
			labels.setPreferredSize(new Dimension(30, 60));
			this.info3.add(labels);
			
		}
		
	}
	public void update() {
		int num=modele.getNumJoueurV2();
		this.info1.setText(modele.joueurs[num].getName()); //change le "nom" du joueur a' chaque fin de tour.
		this.info2.setText("Actions restantes: "+ modele.getNbActionsString()); //affiche le nbActions du joueur
		int count=0;
		
		for(int numero=0;numero<modele.getNbArtefacts();numero++) {
			if(modele.artefacts[numero].hasProprio())
				if(modele.joueurs[modele.currentPlayerV2]==modele.artefacts[numero].getProprio()) {
					items[count].setIcon(new ImageIcon(ress.getImage(numero+13)));
					count++;
				}
			switch (count) {
			case 0:
			for(JLabel j :items) {
					j.setIcon(null);
			}
			break;
			case 1:
				for(int i=1;i<items.length;i++) {
					items[i].setIcon(null);
				}
				break;
			case 2:
				items[2].setIcon(null);
				items[3].setIcon(null);
				break;
			case 3:
				items[3].setIcon(null);
				break;
			default:
				break;
			}
		}
	}
}
