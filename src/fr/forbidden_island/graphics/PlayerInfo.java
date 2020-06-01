package fr.forbidden_island.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import fr.forbidden_island.core.Observer;
import fr.forbidden_island.data.Ressources;
import fr.forbidden_island.core.Model;
/**
 * Correspond à la vue de la GUI du joueur, c'est à dire son interface d'action avec l'app
 *
 */
public class PlayerInfo extends JPanel implements Observer{

	private Model modele;
	private JLabel info1;//Pour indiquer le joueur en cours
	private JLabel info2;//Pour indiquer le nombre d'actions
	private JPanel info3;//Pour indiquer l'inventaire
	private JPanel info4;//Pour indiquer les commandes disponibles
	private JPanel info5;//Pour indiquer la présence de clée
	private JLabel [] items= new JLabel[4];//Images des items dans l'inventaire
	private JLabel [] helps= new JLabel[8];//Les différents textes des commandes
	private JLabel key;//Image de la clé
	private Ressources ress;//Ressources recupérées dans la fenetre
	
	public PlayerInfo(Model mod,String initial,Ressources r) {
		this.modele = mod; //pointeur vers le modele
		this.setLayout(new FlowLayout());
		/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
		modele.addObserver(this); //ajoute a' observers
		//Construction des 2 premiers labels d'information
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
	     * Création d'une sous boite où on y met les infos des artéfacts que possède le joueur
	     * Et création de la boite des commandes
	     * Et Création de la boite de la clef
	     */
	    this.add(info1);
	    this.add(info2);
	    createInv();
	    this.add(info3);
	    createHelp();
	    createBoxKey();
	    this.add(info5);
	    this.add(info4);
	    this.setBorder(BorderFactory.createTitledBorder("Informations du personnage"));
	    this.setPreferredSize(new Dimension(220, 30));
	    ress=r;//Assigantion des ressources récupérées dans Fenetre
	}
	/*
	 * Initialise la boite de l'inventaire
	 * Utilisé dans le constructeur
	 */
	private void createInv() {
		this.info3= new JPanel(new FlowLayout());
		this.info3.setBorder(BorderFactory.createTitledBorder("Inventaire :"));
		this.info3.setPreferredSize(new Dimension(200, 80));
		items[0]=new JLabel();
		items[1]=new JLabel();
		items[3]=new JLabel();
		items[2]=new JLabel();
		for(JLabel labels:items) {
			labels.setPreferredSize(new Dimension(35, 60));
			this.info3.add(labels);
		}
	}
	/*
	 * Initialise la boite de l'inventaire de la clé
	 * Utilisé dans le constructeur
	 */
	private void createBoxKey() {
			this.info5= new JPanel(new FlowLayout());
			this.info5.setBorder(BorderFactory.createTitledBorder("Clef :"));
			this.info5.setPreferredSize(new Dimension(120,60));
			key=new JLabel();
			this.info5.add(key);
	}
	/*
	 * Initialise la boite de l'aide mémoire des commandes
	 * Utilisé dans le constructeur
	 */
	private void createHelp() {
		this.info4= new JPanel(new FlowLayout());
		this.info4.setBorder(BorderFactory.createTitledBorder("Commandes :"));
		helps[0]=new JLabel("  Deplacements :");
		helps[1]=new JLabel("  Fleches directionelles");
		helps[2]=new JLabel("  Assecher :");
		helps[3]=new JLabel("  A");
		helps[4]=new JLabel("  Ramasser :");
		helps[5]=new JLabel("  R");
		helps[6]=new JLabel("  Fin de tour :");
		helps[7]=new JLabel("  Bouton ou Espace");
		this.info4.setPreferredSize(new Dimension(200, 300));
			for(JLabel labels:helps) {
				labels.setPreferredSize(new Dimension(200,20));
				labels.setFont(new Font("Arial", Font.ITALIC, 16));
				this.info4.add(labels);
			}
		for(int i=0;i<helps.length;i=i+2) {
			helps[i].setFont(new Font("Cambria", Font.CENTER_BASELINE, 16));
		}
	}
	/**
	 * Lorsque notifyObservers est appelé dans model, ce update est lancé
	 * mets à jour dynamiquement:
	 * -L'info sur le joueur en cours et le nombre d'actions
	 * -L'info sur l'inventaire de chaque joueur
	 * -l'info sur les clés
	 */
	public void update() {
		int num=modele.getNumJoueurV2();//Recupère le joueur en cours
		if(modele.joueurs[num].getClef()) {
			key.setIcon(new ImageIcon(ress.getImage(3)));//Afiche une clé si le joueur en possède une
		}else {
			key.setIcon(null);//sinon affiche rien
		}
		this.info1.setText(modele.joueurs[num].getName()); //change le "nom" du joueur à chaque fin de tour.
		this.info1.setIcon(new ImageIcon(ress.getImage(num+17)));//Mets l'icon du joueurs correspondant (mini perso)
		this.info2.setText("Actions restantes: "+ modele.getNbActionsString()); //affiche le nbActions du joueur
		int count=0;
		
		for(int numero=0;numero<modele.getNbArtefacts();numero++) {
			if(modele.artefacts[numero].hasProprio() && modele.joueurs[modele.currentPlayerV2]==modele.artefacts[numero].getProprio()) {
					items[count].setIcon(new ImageIcon(ress.getImage(numero+13)));
					count++;//Compte le nombre d'artefacts et les affiche dans l'inventaire
				}
			/*
			 * Ce swicth nettoie l'inventaire exemple :
			 * Si le joueur 1 possède deux artéfacts et le joueur 2 un artéfact, cela nettoie l'inventaire pour afficher le nombre d'artefact qu'il faut 
			 */
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
