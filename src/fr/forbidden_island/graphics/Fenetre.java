package fr.forbidden_island.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import fr.forbidden_island.core.Model;
import fr.forbidden_island.core.Observer;
import fr.forbidden_island.data.Ressources;
import fr.forbidden_island.data.Statut;

/**
 * La classe Fenetre est la classe principale d'affichage de l'application
 * Dans celle ci se trouve touts les élements composants l'GUI ,Graphic User Interface 
 */

public class Fenetre extends JFrame implements Observer{
	private IslandView view;//Vue du plateau
	private CommandsView bouton;//Vue du bouton
	private JPanel container = new JPanel();//Conteneur de toute la fenetre
	private PlayerInfo Pview;//Vue de la GUI du joueur
	private JPanel PlayerInterface = new JPanel(); //Conteneur du bouton et de l'interface du joueur
	private JPanel Game = new JPanel();
	private Ressources ress;

	/**
	 * Construit une Fenetre vide ( non initialisée)
	 * @param r les ressources chargées dans le main 
	 */
	public Fenetre(Ressources r) {
		this.setTitle("L'ile interdite");
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ress=r;
		this.setResizable(true);
		this.setIconImage(ress.getImage(21));
	}
	/**
	 * Initialise les composants de la fenetre pour le jeu
	 * @param mod le modele passé en paramètre via un appel (Dans acceuil)
	 */
	public void initGame(Model mod) {
		// Instanciation des composants
		this.view = new IslandView(mod,ress);
		this.bouton = new CommandsView(mod);
		this.Pview = new PlayerInfo(mod, "",ress);
		this.Pview.update();
		mod.addObserver(this);
		
		// Init composants pour fenetre
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.setPreferredSize(new Dimension(1280, 720));
		PlayerInterface.setBackground(Color.gray);
		PlayerInterface.setLayout(new BorderLayout());
		PlayerInterface.setPreferredSize(new Dimension(212, 200));
		Game.setBackground(Color.white);
		Game.setLayout(new BorderLayout());
		Game.setPreferredSize(new Dimension(1080, 620));
		Game.add(view, BorderLayout.CENTER);
		container.add(Game, BorderLayout.CENTER);
		container.add(PlayerInterface, BorderLayout.EAST);
		PlayerInterface.add(bouton, BorderLayout.SOUTH);
		PlayerInterface.add(Pview, BorderLayout.CENTER);

		this.setContentPane(container);//Le Conteneur est defini comme conteneur de la fenetre	
	}
	/**
	 * Informe le numéro du joueur passé en paramètre de son elimination
	 * @param numJ le numero du joueur à informer
	 */
	private void purgeInfo(String numJ) {
		JOptionPane.showMessageDialog(null, "Le joueur N°"+numJ+" a perdu RIP !", "Un joueur a perdu !", JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Informe tous les joueurs de la fin de la partie
	 */
	private void endInfo() {
		if (view.getModele().getEndOfTheGame()) {
			JOptionPane.showMessageDialog(null, "La partie est finie !!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * Informe le numéro du joueur passé en paramètre de sa victoire
	 * @param numJ le numero du joueur à informer
	 */
	private void winInfo(String numJ) {
			JOptionPane.showMessageDialog(null, "Le joueur n°"+numJ+" s'en est sorti !", "Un joueur a réussi à s'enfuir!", JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Lorsque notifyObservers est appelé dans model, ce update est lancé
	 * Utilise les fonctions winInfo,endInfo et purgeInfo
	 */
	public void update() {
		if(view!=null) {
			for(int i=0;i<view.getModele().getNbJoueurs();i++) {
				if(view.getModele().joueurs[i].getStatut()==Statut.mourant) 				
					purgeInfo(Integer.toString(i+1));//quand le joueur est en train de mourir, affiche un message d'information
				else if(view.getModele().joueurs[i].getStatut()==Statut.fuyant)
					winInfo(Integer.toString(i+1));//quand le joueur est en train de ganger, affiche un message d'information
			}			
			this.repaint();
			endInfo();
		}	
	}
}
