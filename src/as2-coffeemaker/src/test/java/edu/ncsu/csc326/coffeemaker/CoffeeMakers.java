package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * Example Unit tests for CoffeeMaker class.
 * Do not submit as your own!
 */
public class CoffeeMakers {
	
	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;

	@BeforeEach
	public void setUp() throws Exception {
		cm = new CoffeeMaker();
		
		//Set up for r1
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");
		
		//Set up for r2
		r2 = new Recipe();
		r2.setName("Mocha");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");
		
		//Set up for r3
		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("100");
		
		//Set up for r4
		r4 = new Recipe();
		r4.setName("Hot Chocolate");
		r4.setAmtChocolate("4");
		r4.setAmtCoffee("0");
		r4.setAmtMilk("1");
		r4.setAmtSugar("1");
		r4.setPrice("65");
	}

	@Test
	public void testAddRecipe_Normal() {
		boolean isRecipeAdded = cm.addRecipe(r1);
		assertTrue(isRecipeAdded);
	}

	@Test
	public void testAddRecipe_Duplicate() {
		assertTrue(cm.addRecipe(r2));
		assertFalse(cm.addRecipe(r2));
	}

	@Test
	public void testDeleteRecipe_Normal() {
		String expected = "Coffee";
		assertTrue(cm.addRecipe(r1));
		String actual = cm.deleteRecipe(0);

		assertEquals(expected, actual, "Should return the name of the deleted recipe");
	}

	@Test
	public void testDeleteRecipe_NonExisting() {
		assertNull(cm.deleteRecipe(0), "Should return null for a non-existent recipe");
	}

	@Test
	public void testEditRecipe_Normal() {
		cm.addRecipe(r1);
		cm.editRecipe(0, r2);

		Recipe[] recipes = cm.getRecipes();

		assertEquals(r2.getName(), recipes[0].getName());
		assertEquals(r2.getAmtCoffee(), recipes[0].getAmtCoffee());
		assertEquals(r2.getAmtMilk(), recipes[0].getAmtMilk());
		assertEquals(r2.getAmtSugar(), recipes[0].getAmtSugar());
		assertEquals(r2.getAmtChocolate(), recipes[0].getAmtChocolate());
		assertEquals(r2.getPrice(), recipes[0].getPrice());
	}

	// Test fails due to addSugar()
	@Test
	public void testAddInventory_Normal() throws InventoryException {
		cm.addInventory("4", "7", "2", "0");
		assertEquals("Coffee: 15\nMilk: 17\nSugar: 17\nChocolate: 15\n", cm.checkInventory());
	}

	@Test
	public void testGetInventory_Normal() {
		assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", cm.checkInventory());
	}
}
