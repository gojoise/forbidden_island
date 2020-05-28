package fr.forbidden_island.data;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ressources {
	private Image[] images; //attribut tableau d'image
	
	/**
	 * CONSTRUCT: charge les images dans un tableau a partir des fichiers.
	 */
	public Ressources() {
		Image image = null;
		images = new Image[4];
		for(int i=0;i<4;i++) {
			try {
				File fichier = new File("src/images/"+i+".png");
				image = ImageIO.read(fichier);
				images[i]=image;
				System.out.println(fichier.toString()+"   lu");
			} catch (IOException e) {
				System.out.println("pop");
			}
		}
	}
	/**fonction getImage(x) du tableau
	 * @param x 
	 * @return L'image de'sire'e
	 */
	public Image getImage(int x) {
		return images[x];
	}
	
	
	
}
