package fr.forbidden_island.data;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Ressources {
	private Image[] images; //attribut tableau d'image
//	private  String FILENAME = "src/carte_ileX.txt";
//	private FileOutputStream fOutpout;
	
	/**
	 * CONSTRUCT: charge les images dans un tableau a partir des fichiers.
	 */
	public Ressources() {
		Image image = null;
		images = new Image[9];
		for(int i=0;i<9;i++) {
			try {
				File fichier = new File("src/images/"+i+".png");
				image = ImageIO.read(fichier);
				images[i]=image;
				System.out.println(fichier.toString()+"   lu");
			} catch (IOException e) {
				System.out.println("pop");
			}
			
			
			
//			File f2 = new File(FILENAME);
//		       try {
//				f2.createNewFile();
//				fOutpout = new FileOutputStream(f2);
//			    //fOutpout.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		       

		}
	}
	/**fonction getImage(x) du tableau
	 * @param x 
	 * @return L'image de'sire'e
	 */
	public Image getImage(int x) {
		return images[x];
	}
	
//	public  FileOutputStream getOutpout() {
//		return fOutpout;
//	}
//	
	
}
