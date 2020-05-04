import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Application avec interface graphique.
 * Thibaut Balabonski, Universit� Paris-Sud.
 * Mat�riel p�dagogique li� au cours POGL, s�ance du 20 avril 2020.
 * 
 * Un principe directeur est la s�paration stricte des deux parties suivantes :
 *  - Le coeur de l'application, appel� le mod�le, o� est fait l'essentiel
 *    du travail.
 *  - L'interface utilisateur, appel�e la vue, qui � la fois montre des choses
 *    � l'utilisateur et lui fournit des moyens d'interagir.
 *
 * Notre cas d'�tude : le jeu de la vie de Conway.
 * Une grille bidimensionnelle de dimensions finies est peupl�e de cellules
 * pouvant �tre vivantes ou mortes. � chaque tour un nouvel �tat est calcul�
 * pour chaque cellule en fonction de l'�tat de ses voisines imm�diates.
 * Un bouton permet de passer au tour suivant (on dit aussi la g�n�ration
 * suivante).
 */

/**
 * Un lien entre vue et mod�le : les informations montr�es � l'utilisateur
 * refl�tent l'�tat du mod�le et doivent �tre maintenues � jour.
 * 
 * Pour r�aliser cette synchronisation, on peut suivre le sch�ma de conception
 * observateur/observ�, dont le principe est le suivant :
 *  - Un observateur (en l'occurrence la vue) est li� � un objet observ� et se
 *    met � jour pour refl�ter les changement de l'observ�.
 *  - Un observ� est li� � un ensemble d'objets observateurs et les notifie de
 *    tout changement de son propre �tat.
 *
 * Java fournit une interface [Observer] (observateur) et une classe
 * [Observable] (observ�) assurant cette jonction.
 * Voici une mani�re sommaire de les recoder.
 */

/**
 * Interface des objets observateurs.
 */ 
interface Observer {
    /**
     * Un observateur doit poss�der une m�thode [update] d�clenchant la mise �
     * jour.
     */
    public void update();
    /**
     * La version officielle de Java poss�de des param�tres pr�cisant le
     * changement qui a eu lieu.
     */
}

/**
 * Classe des objets pouvant �tre observ�s.
 */
abstract class Observable {
    /**
     * On a une liste [observers] d'observateurs, initialement vide, � laquelle
     * viennent s'inscrire les observateurs via la m�thode [addObserver].
     */
    private ArrayList<Observer> observers;
    public Observable() {
	this.observers = new ArrayList<Observer>();
    }
    public void addObserver(Observer o) {
	observers.add(o);
    }

    /**
     * Lorsque l'�tat de l'objet observ� change, il est convenu d'appeler la
     * m�thode [notifyObservers] pour pr�venir l'ensemble des observateurs
     * enregistr�s.
     * On le fait ici concr�tement en appelant la m�thode [update] de chaque
     * observateur.
     */
    public void notifyObservers() {
	for(Observer o : observers) {
	    o.update();
	}
    }
}
/** Fin du sch�ma observateur/observ�. */


/**
 * Nous allons commencer � construire notre application, en voici la classe
 * principale.
 */
public class Conway {
    /**
     * L'amor�age est fait en cr�ant le mod�le et la vue, par un simple appel
     * � chaque constructeur.
     * Ici, le mod�le est cr�� ind�pendamment (il s'agit d'une partie autonome
     * de l'application), et la vue prend le mod�le comme param�tre (son
     * objectif est de faire le lien entre mod�le et utilisateur).
     */
    public static void main(String[] args) {
	/**
	 * Pour les besoins du jour on consid�re la ligne EvenQueue... comme une
	 * incantation qu'on pourra expliquer plus tard.
	 */
	EventQueue.invokeLater(() -> {
		/** Voici le contenu qui nous int�resse. */
                CModele modele = new CModele();
                CVue vue = new CVue(modele);
	    });
    }
}
/** Fin de la classe principale. */


/**
 * Le mod�le : le coeur de l'application.
 *
 * Le mod�le �tend la classe [Observable] : il va poss�der un certain nombre
 * d'observateurs (ici, un : la partie de la vue responsable de l'affichage)
 * et devra les pr�venir avec [notifyObservers] lors des modifications.
 * Voir la m�thode [avance()] pour cela.
 */
class CModele extends Observable {
    /** On fixe la taille de la grille. */
    public static final int HAUTEUR=40, LARGEUR=60;
    /** On stocke un tableau de cellules. */
    private Cellule[][] cellules;

    /** Construction : on initialise un tableau de cellules. */
    public CModele() {
	/**
	 * Pour �viter les probl�mes aux bords, on ajoute une ligne et une
	 * colonne de chaque c�t�, dont les cellules n'�volueront pas.
	 */ 
	cellules = new Cellule[LARGEUR+2][HAUTEUR+2];
	for(int i=0; i<LARGEUR+2; i++) {
	    for(int j=0; j<HAUTEUR+2; j++) {
		cellules[i][j] = new Cellule(this,i, j);
	    }
	}
	init();
    }

    /**
     * Initialisation al�atoire des cellules, except�es celle des bords qui
     * ont �t� ajout�s.
     */
    public void init() {
	for(int i=1; i<=LARGEUR; i++) {
	    for(int j=1; j<=HAUTEUR; j++) {
		if (Math.random() < .2) {
		    cellules[i][j].etat = true;
		}
	    }
	}
    }

    /**
     * Calcul de la g�n�ration suivante.
     */
    public void avance() {
	/**
	 * On proc�de en deux �tapes.
	 *  - D'abord, pour chaque cellule on �value ce que sera son �tat � la
	 *    prochaine g�n�ration.
	 *  - Ensuite, on applique les �volutions qui ont �t� calcul�es.
	 */ 
	for(int i=1; i<LARGEUR+1; i++) {
	    for(int j=1; j<HAUTEUR+1; j++) {
		cellules[i][j].evalue();
	    }
	}
	for(int i=1; i<LARGEUR+1; i++) {
	    for(int j=1; j<HAUTEUR+1; j++) {
		cellules[i][j].evolue();
	    }
	}
	/**
	 * Pour finir, le mod�le ayant chang�, on signale aux observateurs
	 * qu'ils doivent se mettre � jour.
	 */
	notifyObservers();
    }

    /**
     * M�thode auxiliaire : compte le nombre de voisines vivantes d'une
     * cellule d�sign�e par ses coordonn�es.
     */
    protected int compteVoisines(int x, int y) {
	int res=0;
	/**
	 * Strat�gie simple � �crire : on compte les cellules vivantes dans le
	 * carr� 3x3 centr� autour des coordonn�es (x, y), puis on retire 1
	 * si la cellule centrale est elle-m�me vivante.
	 * On n'a pas besoin de traiter � part les bords du tableau de cellules
	 * gr�ce aux lignes et colonnes suppl�mentaires qui ont �t� ajout�es
	 * de chaque c�t� (dont les cellules sont mortes et n'�volueront pas).
	 */
	for(int i=x-1; i<=x+1; i++) {
	    for(int j=y-1; j<=y+1; j++) {
		if (cellules[i][j].etat) { res++; }
	    }
	}
	return (res - ((cellules[x][y].etat)?1:0));
	/**
	 * L'expression [(c)?e1:e2] prend la valeur de [e1] si [c] vaut [true]
	 * et celle de [e2] si [c] vaut [false].
	 * Cette derni�re ligne est donc �quivalente �
	 *     int v;
	 *     if (cellules[x][y].etat) { v = res - 1; }
	 *     else { v = res - 0; }
	 *     return v;
	 */
    }

    /**
     * Une m�thode pour renvoyer la cellule aux coordonn�es choisies (sera
     * utilis�e par la vue).
     */
    public Cellule getCellule(int x, int y) {
	return cellules[x][y];
    }
}

/** Fin de la classe CModele. */

/**
 * D�finition d'une classe pour les cellules.
 * Cette classe fait encore partie du mod�le.
 */
class Cellule {
    /** On conserve un pointeur vers la classe principale du mod�le. */
    private CModele modele;

    /** L'�tat d'une cellule est donn� par un bool�en. */
    protected boolean etat;
    /**
     * On stocke les coordonn�es pour pouvoir les passer au mod�le lors
     * de l'appel � [compteVoisines].
     */
    private final int x, y;
    public Cellule(CModele modele, int x, int y) {
        this.modele = modele;
        this.etat = false;
        this.x = x; this.y = y;
    }
    /**
     * Le passage � la g�n�ration suivante se fait en deux �tapes :
     *  - D'abord on calcule pour chaque cellule ce que sera sont �tat � la
     *    g�n�ration suivante (m�thode [evalue]). On stocke le r�sultat
     *    dans un attribut suppl�mentaire [prochainEtat].
     *  - Ensuite on met � jour l'ensemble des cellules (m�thode [evolue]).
     * Objectif : �viter qu'une �volution imm�diate d'une cellule pollue
     * la d�cision prise pour une cellule voisine.
     */
    private boolean prochainEtat;
    protected void evalue() {
        switch (this.modele.compteVoisines(x, y)) {
        case 2: prochainEtat=etat; break;
        case 3: prochainEtat=true; break;
        default: prochainEtat=false;
        }
    }
    protected void evolue() {
        etat = prochainEtat;
    }
    
    /** Un test � l'usage des autres classes (sera utilis� par la vue). */
    public boolean estVivante() {
        return etat;
    }
}    
/** Fin de la classe Cellule, et du mod�le en g�n�ral. */


/**
 * La vue : l'interface avec l'utilisateur.
 *
 * On d�finit une classe chapeau [CVue] qui cr�e la fen�tre principale de 
 * l'application et contient les deux parties principales de notre vue :
 *  - Une zone d'affichage o� on voit l'ensemble des cellules.
 *  - Une zone de commande avec un bouton pour passer � la g�n�ration suivante.
 */
class CVue {
    /**
     * JFrame est une classe fournie pas Swing. Elle repr�sente la fen�tre
     * de l'application graphique.
     */
    private JFrame frame;
    /**
     * VueGrille et VueCommandes sont deux classes d�finies plus loin, pour
     * nos deux parties de l'interface graphique.
     */
    private VueGrille grille;
    private VueCommandes commandes;

    /** Construction d'une vue attach�e � un mod�le. */
    public CVue(CModele modele) {
	/** D�finition de la fen�tre principale. */
	frame = new JFrame();
	frame.setTitle("Jeu de la vie de Conway");
	/**
	 * On pr�cise un mode pour disposer les diff�rents �l�ments �
	 * l'int�rieur de la fen�tre. Quelques possibilit�s sont :
	 *  - BorderLayout (d�faut pour la classe JFrame) : chaque �l�ment est
	 *    dispos� au centre ou le long d'un bord.
	 *  - FlowLayout (d�faut pour un JPanel) : les �l�ments sont dispos�s
	 *    l'un � la suite de l'autre, dans l'ordre de leur ajout, les lignes
	 *    se formant de gauche � droite et de haut en bas. Un �l�ment peut
	 *    passer � la ligne lorsque l'on redimensionne la fen�tre.
	 *  - GridLayout : les �l�ments sont dispos�s l'un � la suite de
	 *    l'autre sur une grille avec un nombre de lignes et un nombre de
	 *    colonnes d�finis par le programmeur, dont toutes les cases ont la
	 *    m�me dimension. Cette dimension est calcul�e en fonction du
	 *    nombre de cases � placer et de la dimension du contenant.
	 */
	frame.setLayout(new FlowLayout());

	/** D�finition des deux vues et ajout � la fen�tre. */
	grille = new VueGrille(modele);
	frame.add(grille);
	commandes = new VueCommandes(modele);
	frame.add(commandes);
	/**
	 * Remarque : on peut passer � la m�thode [add] des param�tres
	 * suppl�mentaires indiquant o� placer l'�l�ment. Par exemple, si on
	 * avait conserv� la disposition par d�faut [BorderLayout], on aurait
	 * pu �crire le code suivant pour placer la grille � gauche et les
	 * commandes � droite.
	 *     frame.add(grille, BorderLayout.WEST);
	 *     frame.add(commandes, BorderLayout.EAST);
	 */

	/**
	 * Fin de la plomberie :
	 *  - Ajustement de la taille de la fen�tre en fonction du contenu.
	 *  - Indiquer qu'on quitte l'application si la fen�tre est ferm�e.
	 *  - Pr�ciser que la fen�tre doit bien appara�tre � l'�cran.
	 */
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }
}


/**
 * Une classe pour repr�senter la zone d'affichage des cellules.
 *
 * JPanel est une classe d'�l�ments graphiques, pouvant comme JFrame contenir
 * d'autres �l�ments graphiques.
 *
 * Cette vue va �tre un observateur du mod�le et sera mise � jour � chaque
 * nouvelle g�n�ration des cellules.
 */
class VueGrille extends JPanel implements Observer {
    /** On maintient une r�f�rence vers le mod�le. */
    private CModele modele;
    /** D�finition d'une taille (en pixels) pour l'affichage des cellules. */
    private final static int TAILLE = 12;

    /** Constructeur. */
    public VueGrille(CModele modele) {
	this.modele = modele;
	/** On enregistre la vue [this] en tant qu'observateur de [modele]. */
	modele.addObserver(this);
	/**
	 * D�finition et application d'une taille fixe pour cette zone de
	 * l'interface, calcul�e en fonction du nombre de cellules et de la
	 * taille d'affichage.
	 */
	Dimension dim = new Dimension(TAILLE*CModele.LARGEUR,
				      TAILLE*CModele.HAUTEUR);
	this.setPreferredSize(dim);
    }

    /**
     * L'interface [Observer] demande de fournir une m�thode [update], qui
     * sera appel�e lorsque la vue sera notifi�e d'un changement dans le
     * mod�le. Ici on se content de r�afficher toute la grille avec la m�thode
     * pr�d�finie [repaint].
     */
    public void update() { repaint(); }

    /**
     * Les �l�ments graphiques comme [JPanel] poss�dent une m�thode
     * [paintComponent] qui d�finit l'action � accomplir pour afficher cet
     * �l�ment. On la red�finit ici pour lui confier l'affichage des cellules.
     *
     * La classe [Graphics] regroupe les �l�ments de style sur le dessin,
     * comme la couleur actuelle.
     */
    public void paintComponent(Graphics g) {
	super.repaint();
	/** Pour chaque cellule... */
	for(int i=1; i<=CModele.LARGEUR; i++) {
	    for(int j=1; j<=CModele.HAUTEUR; j++) {
		/**
		 * ... Appeler une fonction d'affichage auxiliaire.
		 * On lui fournit les informations de dessin [g] et les
		 * coordonn�es du coin en haut � gauche.
		 */
		paint(g, modele.getCellule(i, j), (i-1)*TAILLE, (j-1)*TAILLE);
	    }
	}
    }
    /**
     * Fonction auxiliaire de dessin d'une cellule.
     * Ici, la classe [Cellule] ne peut �tre d�sign�e que par l'interm�diaire
     * de la classe [CModele] � laquelle elle est interne, d'o� le type
     * [CModele.Cellule].
     * Ceci serait impossible si [Cellule] �tait d�clar�e priv�e dans [CModele].
     */
    private void paint(Graphics g, Cellule c, int x, int y) {
        /** S�lection d'une couleur. */
	if (c.estVivante()) {
	    g.setColor(Color.BLACK);
	} else {
            g.setColor(Color.WHITE);
        }
        /** Coloration d'un rectangle. */
        g.fillRect(x, y, TAILLE, TAILLE);
    }
}


/**
 * Une classe pour repr�senter la zone contenant le bouton.
 *
 * Cette zone n'aura pas � �tre mise � jour et ne sera donc pas un observateur.
 * En revanche, comme la zone pr�c�dente, celle-ci est un panneau [JPanel].
 */
class VueCommandes extends JPanel {
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une
     * r�f�rence au mod�le.
     */
    private CModele modele;

    /** Constructeur. */
    public VueCommandes(CModele modele) {
	this.modele = modele;
	/**
	 * On cr�e un nouveau bouton, de classe [JButton], en pr�cisant le
	 * texte qui doit l'�tiqueter.
	 * Puis on ajoute ce bouton au panneau [this].
	 */
	JButton boutonAvance = new JButton(">");
	this.add(boutonAvance);
	/**
	 * Le bouton, lorsqu'il est cliqu� par l'utilisateur, produit un
	 * �v�nement, de classe [ActionEvent].
	 *
	 * On a ici une variante du sch�ma observateur/observ� : un objet
	 * impl�mentant une interface [ActionListener] va s'inscrire pour
	 * "�couter" les �v�nements produits par le bouton, et recevoir
	 * automatiquements des notifications.
	 * D'autres variantes d'auditeurs pour des �v�nements particuliers :
	 * [MouseListener], [KeyboardListener], [WindowListener].
	 *
	 * Cet observateur va enrichir notre sch�ma Mod�le-Vue d'une couche
	 * interm�diaire Contr�leur, dont l'objectif est de r�cup�rer les
	 * �v�nements produits par la vue et de les traduire en instructions
	 * pour le mod�le.
	 * Cette strate interm�diaire est potentiellement riche, et peut
	 * notamment traduire les m�mes �v�nements de diff�rentes fa�ons en
	 * fonction d'un �tat de l'application.
	 * Ici nous avons un seul bouton r�alisant une seule action, notre
	 * contr�leur sera donc particuli�rement simple. Cela n�cessite
	 * n�anmoins la cr�ation d'une classe d�di�e.
	 */	
	Controleur ctrl = new Controleur(modele);
	/** Enregistrement du contr�leur comme auditeur du bouton. */
	boutonAvance.addActionListener(ctrl);
	
	/**
	 * Variante : une lambda-expression qui �vite de cr�er une classe
         * sp�cifique pour un contr�leur simplissime.
         *
         JButton boutonAvance = new JButton(">");
         this.add(boutonAvance);
         boutonAvance.addActionListener(e -> { modele.avance(); });
         *
         */

    }
}
/** Fin de la vue. */

/**
 * Classe pour notre contr�leur rudimentaire.
 * 
 * Le contr�leur impl�mente l'interface [ActionListener] qui demande
 * uniquement de fournir une m�thode [actionPerformed] indiquant la
 * r�ponse du contr�leur � la r�ception d'un �v�nement.
 */
class Controleur implements ActionListener {
    /**
     * On garde un pointeur vers le mod�le, car le contr�leur doit
     * provoquer un appel de m�thode du mod�le.
     * Remarque : comme cette classe est interne, cette inscription
     * explicite du mod�le est inutile. On pourrait se contenter de
     * faire directement r�f�rence au mod�le enregistr� pour la classe
     * englobante [VueCommandes].
     */
    CModele modele;
    public Controleur(CModele modele) { this.modele = modele; }
    /**
     * Action effectu�e � r�ception d'un �v�nement : appeler la
     * m�thode [avance] du mod�le.
     */
    public void actionPerformed(ActionEvent e) {
        modele.avance();
    }
}
/** Fin du contr�leur. */