package fr.forbidden_island.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import fr.forbidden_island.core.Model;
import fr.forbidden_island.data.Ressources;

/**
 * La classe acceuil est une classe qui créé une boite de dialogue permettant de paramètrer notre app !
 * Dans celle ci se trouve les différents choix à faire pour régler nos paramètres.
 */

public class Acceuil extends JDialog{

	private JLabel presetLabel,JoueurLabel,IconLabel;
	private JComboBox<Integer> choixJoueur;
	private JComboBox<String> choixPreset;
	private Fenetre fen;
	private Ressources ress;

	  public Acceuil(Fenetre f, String title, boolean modal,Ressources r) {
		/*Initialisation boite de dialogue
		 * pour la taille et propriété importante de la fenetre de la boite de dialogue
		 */
		super((JFrame)f,title,true);
		this.fen=f;
		this.ress=r;
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		initAcceuil();//initialise l'ensemble des composants de la boite de dialogue
		this.setVisible(true);
	}

	private void initAcceuil() {
	    //Init l'icone de déco
	    IconLabel = new JLabel(new ImageIcon(ress.getImage(21)));
	    JPanel panIcon = new JPanel();
	    panIcon.setBackground(Color.white);
	    panIcon.setLayout(new BorderLayout());
	    panIcon.add(IconLabel);
	    /*Le preset de taille d'ile
	     * initialise la zone de récupération pour le preset de l'ile
	     *
	     */
	    JPanel panPreset = new JPanel();
	    panPreset.setBackground(Color.white);
	    panPreset.setPreferredSize(new Dimension(220, 60));
	    panPreset.setBorder(BorderFactory.createTitledBorder("Preset de taille d'ile"));
	    choixPreset = new JComboBox<String>();
	    choixPreset.addItem("petite");
	    choixPreset.addItem("moyenne");
	    choixPreset.addItem("grande");
	    presetLabel = new JLabel("Choisir un preset :");
	    panPreset.add(presetLabel);
	    panPreset.add(choixPreset);
	    /*Le nombre de joueurs
	     * initialise la zone de récupération pour le nombre de joueurs
	     *
	     */
	    JPanel panChoix = new JPanel();
	    panChoix.setBackground(Color.white);
	    panChoix.setPreferredSize(new Dimension(220, 60));
	    panChoix.setBorder(BorderFactory.createTitledBorder("Nombre de joueurs"));
	    choixJoueur = new JComboBox<Integer>();
	    choixJoueur.addItem(1);
	    choixJoueur.addItem(2);
	    choixJoueur.addItem(3);
	    choixJoueur.addItem(4);
	    JoueurLabel = new JLabel("Choisir un nombre :");
	    panChoix.add(JoueurLabel);
	    panChoix.add(choixJoueur);
	    /*
	     * Initialise le conteneur de toutes les zones de choix
	     */
	    JPanel content = new JPanel();
	    content.setBackground(Color.white);
	    content.add(panPreset);
	    content.add(panChoix);

	    JPanel control = new JPanel();
	    JButton okBouton = new JButton("OK");
	    okBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {        
	    	//ici initialisation du jeu
	    	int nbJ=(int)choixJoueur.getSelectedItem();
	    	String preset=choixPreset.getSelectedItem().toString();
			fen.initGame(new Model(nbJ,preset));
			fen.setVisible(true);
	        setVisible(false);
	      }     
	    });

	    JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	    	//ici Fenetre de jeu détruite et boite de dialogue "fermée"
			fen.setVisible(true);
	 		fen.dispose();
	        setVisible(false);
	      }      
	    });
	    //Ajoute les deux bouton JButton déclarés plus haut au panneau de validation
	    control.add(okBouton);
	    control.add(cancelBouton);
	    /*
	     * Ajoute l'enselble des elements qui composent notreboite de dialogue
	     * i.e l'icone , le conteneur des choix et le panneau de validation
	     */
	    this.getContentPane().add(panIcon,BorderLayout.WEST);
	    this.getContentPane().add(content, BorderLayout.CENTER);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	}  

}

