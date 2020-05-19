package fr.forbidden_island.core;

import fr.forbidden_island.data.Cellule;
import java.util.Random;

public class Model extends Observable {
	private final int dimGrilleAbsc = 35;
	private final int dimGrilleOrd = 21;
	private Cellule[][] grille;
	Random r = new Random();

	// if(t<0.33)this.length=1;
	// if(t>0.33 && t<0.66)this.length=2;
	// if(t>0.66)this.length=3;

	public Model() {

		this.grille = new Cellule[dimGrilleAbsc][dimGrilleOrd];
		init();
		// System.out.println(grille[0][0].getAbsc());
		// System.out.println(grille[0][0].getOrd());
	}

	private void init() {

		for (int x = 0; x < dimGrilleAbsc; x++) {
			for (int y = 0; y < dimGrilleOrd; y++) {
				// donne des coords aux cellules
				grille[x][y] = new Cellule(this, x * Cellule.size, y * Cellule.size);
				// donne un état terre au cellules "centrales"
				if (x > dimGrilleAbsc / 4 && x < (dimGrilleAbsc * 3) / 4 && y > dimGrilleOrd / 4
						&& y < (dimGrilleOrd * 3) / 4) {
					grille[x][y].setMer(false);
					float t = r.nextFloat();
					for (float i = 0; i < 2; i++) {
						if (!(x > (dimGrilleAbsc / 4) + i && x < ((dimGrilleAbsc * 3) / 4) - i && y > (dimGrilleOrd / 4) + i && y < ((dimGrilleOrd * 3) / 4) - i)) {
							int cpt = 0;
							for (Cellule c : grille[x][y].voisines(grille)) {
								if (!c.getMer())
									cpt++;
							}
							if (cpt > (i + 1)) {
								if (t < (0.2 + (1 / (i + 1))))
									grille[x][y].setMer(true);
							}
							if (t < ((1 / (i + 1))))
								grille[x][y].setMer(true);
						}
					}
					if (!(x > (dimGrilleAbsc / 4) && x < ((dimGrilleAbsc * 3) / 4) && y > (dimGrilleOrd / 4)
							&& y < ((dimGrilleOrd * 3) / 4))) {
						int cpt = 0;
						for (Cellule c : grille[x][y].voisines(grille)) {
							if (!c.getMer())
								cpt++;
						}
						if (cpt < 2)
							grille[x][y].setMer(true);
						cpt = 0;
						for (Cellule c : grille[x][y].voisines(grille)) {
							if (!c.getMer())
								cpt++;
						}
						if (cpt < 2)
							grille[x][y].setMer(true);

					}

				}
			}
		}
		grille[(dimGrilleAbsc/2)][dimGrilleOrd/2].setJoueur(true);
	}
	
	public void inonde3() {
		for (int x = (dimGrilleAbsc / 4)-1; x <= ((dimGrilleAbsc * 3) / 4); x++) {
			for (int y = (dimGrilleOrd / 4)-1; y <= ((dimGrilleOrd * 3) / 4); y++) {
				float t = r.nextFloat();
				int cpt = 0;
				if(grille[x][y].getEstInonde() )
					grille[x][y].setMer(true);
				for (Cellule c : grille[x][y].voisines(grille)) {
					if (c.getMer())
						cpt++;
				}
				if(!grille[x][y].getMer() && cpt>0)
					if (t < 0.09)grille[x][y].setEstInonde(true);
			}
		}
		
	}
	
	public int getDimGrilleAbsc() {
		return this.dimGrilleAbsc;
	}

	public int getDimGrilleOrd() {
		return this.dimGrilleOrd;
	}

	public Cellule[][] getGrille() {
		return this.grille;
	}
	/*
	 * création d'une ile (tableau de cellules)
	 *
	 * récup toutes les cellules (avec leur états)
	 */

}
