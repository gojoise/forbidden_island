package fr.forbidden_island.core;
import fr.forbidden_island.data.Ressources;
import fr.forbidden_island.graphics.*;

/**
 * Main Class with main method for the programm 
 */
public class Main {
	public static void main(String[] args) {
		Ressources ress= new Ressources();//Chargement des ressources i.e des images
		Fenetre f=new Fenetre(ress);//Creation d'une fenetre vide
		Acceuil a = new Acceuil(f, "Lancer la partie", true,ress);//Creation de la boite de dialogue permettant de faire varier les paramètres
	}
}
