package fr.forbidden_island.core;

import static org.junit.Assert.*;
import org.junit.Test;

import fr.forbidden_island.data.Cellule;
import fr.forbidden_island.data.typeTerrain;


public class TestsUnitaires {
	
	public Model modele;
	/**
	 * Tests de Cellule
	 */
	public Cellule c = new Cellule(modele, 15, 51);
	
	@Test
	public void testGet() {
		int x=15;
		int y=51;
		assertEquals(x,c.getAbsc());
		assertEquals(y,c.getOrd());
	}
	
	@Test
	public void testTerrain() {
		typeTerrain t = typeTerrain.mer;
		c.setTypeTerrain(typeTerrain.mer);
		assertEquals(t,c.getTypeTerrain());
		}
	
	@Test 
	public void testVoisines() {
		Cellule [][] grille = new Cellule[3][3];

	}
	
}
