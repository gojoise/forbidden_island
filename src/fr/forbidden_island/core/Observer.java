package fr.forbidden_island.core;

public interface Observer {
	/**
	 * Un observateur doit posse'der une me'thode [update] de'clenchant la mise a'
	 * jour.
	 */
	public void update();
	/**
	 * La version officielle de Java posse'de des parame'tres pre'cisant le
	 * changement qui a eu lieu.
	 */
}
