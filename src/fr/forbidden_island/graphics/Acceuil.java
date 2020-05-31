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
import javax.swing.JTextField;

import fr.forbidden_island.core.Model;
import fr.forbidden_island.data.Ressources;

public class Acceuil extends JDialog{

	private JLabel nomLabel,JoueurLabel,IconLabel;
	private JComboBox<Integer> choixJoueur;
	  private JTextField nom;
	  private Fenetre fen;
	  private Ressources ress;

	  public Acceuil(Fenetre f, String title, boolean modal,Ressources r) {
		super((JFrame)f,title,true);
		this.fen=f;
		this.ress=r;
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		initAcceuil();
		this.setVisible(true);
	}

	private void initAcceuil() {
	    //Icône
	    IconLabel = new JLabel(new ImageIcon(ress.getImage(21)));
	    JPanel panIcon = new JPanel();
	    panIcon.setBackground(Color.white);
	    panIcon.setLayout(new BorderLayout());
	    panIcon.add(IconLabel);
		
		

	    //Le nom du joueur
	    JPanel panNom = new JPanel();
	    panNom.setBackground(Color.white);
	    panNom.setPreferredSize(new Dimension(220, 60));
	    nom = new JTextField();
	    nom.setPreferredSize(new Dimension(100, 25));
	    panNom.setBorder(BorderFactory.createTitledBorder("Nom du personnage"));
	    nomLabel = new JLabel("Saisir un nom :");
	    panNom.add(nomLabel);
	    panNom.add(nom);



	    //Le nombre de joueurs
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

	    JPanel content = new JPanel();
	    content.setBackground(Color.white);
	    content.add(panNom);

	    content.add(panChoix);

	    JPanel control = new JPanel();
	    JButton okBouton = new JButton("OK");
	    okBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {        
	        nom.getText();
	    	//ici init la partie
			fen.initGame(new Model((int)choixJoueur.getSelectedItem()));
			fen.setVisible(true);
	        setVisible(false);
	      }     
	    });

	    JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	    	//ici fennetre detruite
			fen.setVisible(true);
	 		fen.dispose();
	        setVisible(false);
	      }      
	    });

	    control.add(okBouton);
	    control.add(cancelBouton);
	    
	    this.getContentPane().add(panIcon,BorderLayout.WEST);
	    this.getContentPane().add(content, BorderLayout.CENTER);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	}  

}

