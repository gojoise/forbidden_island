package fr.forbidden_island.core;
import fr.forbidden_island.data.Ressources;
import fr.forbidden_island.graphics.*;


public class Main {
	public static void main(String[] args) {
		Ressources ress= new Ressources();
		Fenetre f=new Fenetre(ress);
		Acceuil a = new Acceuil(f, "Lancer la partie", true,ress); //new Vue	
		
		
	}
}
