package fr.forbidden_island.core;

import java.util.ArrayList;

/**
 * Classe des objets pouvant e'tre observe's.
 */
abstract class Observable {
	/**
	 * On a une liste [observers] d'observateurs, initialement vide, a' laquelle
	 * viennent s'inscrire les observateurs via la me'thode [addObserver].
	 */
	private ArrayList<Observer> observers;
	public Observable() {
		this.observers = new ArrayList<Observer>();
	}
	public void addObserver(Observer o) {
		observers.add(o);
	}

	/**
	 * Lorsque l'e'tat de l'objet observe' change, il est convenu d'appeler la
	 * me'thode [notifyObservers] pour pre'venir l'ensemble des observateurs
	 * enregistre's.
	 * On le fait ici concre'tement en appelant la me'thode [update] de chaque
	 * observateur.
	 */
	public void notifyObservers() {
		for(Observer o : observers) {
			o.update();
		}
	}
}
