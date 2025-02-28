package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * Unit tests for the RecipeBook class.
 * Includes tests for the following RecipeBook methods:
 * - getRecipes
 * - addRecipe
 * - deleteRecipe
 * - editRecipe
 */

public class RecipeBookTest {
    private final int NUM_RECIPES = 4; // Total number of recipes in coffee maker
    private CoffeeMaker cm;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;
    private Recipe r5;

    @BeforeEach
    public void setUp() throws Exception {
        cm = new CoffeeMaker();

        // Set up for r1 - Black Coffee
        r1 = new Recipe();
        r1.setName("Black Coffee");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("2");
        r1.setAmtMilk("0");
        r1.setAmtSugar("0");
        r1.setPrice("35");

        // Set up for r2 - Chocolate Macchiato
        r2 = new Recipe();
        r2.setName("Chocolate Macchiato");
        r2.setAmtChocolate("5");
        r2.setAmtCoffee("2");
        r2.setAmtMilk("3");
        r2.setAmtSugar("1");
        r2.setPrice("65");

        // Set up for r3 - Cappuccino
        r3 = new Recipe();
        r3.setName("Cappuccino");
        r3.setAmtChocolate("0");
        r3.setAmtCoffee("2");
        r3.setAmtMilk("2");
        r3.setAmtSugar("0");
        r3.setPrice("45");

        // Set up for r4 - Hot Chocolate
        r4 = new Recipe();
        r4.setName("Hot Chocolate");
        r4.setAmtChocolate("5");
        r4.setAmtCoffee("0");
        r4.setAmtMilk("3");
        r4.setAmtSugar("3");
        r4.setPrice("50");

        // Set up for r5 - Warm Milk
        r5 = new Recipe();
        r5.setName("Warm Milk");
        r5.setAmtChocolate("0");
        r5.setAmtCoffee("0");
        r5.setAmtMilk("5");
        r5.setAmtSugar("2");
        r5.setPrice("25");
    }

    // Test to get all recipes when there are none in the recipe book
    @Test
    public void testGetAllRecipes_None() {
        Recipe[] recipes = cm.getRecipes();
        boolean areAllNull = true;
        for (Recipe recipe : recipes) {
            if (recipe != null) {
                areAllNull = false;
                break;
            }
        }
        assertNotNull(recipes, "The returned array should not be null");
        assertTrue(recipes.length == 0 || areAllNull,
                "The returned array should either contain 0 elements or all null elements");
    }

    // Test to get all recipes
    @Test
    public void testGetAllRecipes() {
        assertTrue(cm.addRecipe(r1));
        assertTrue(cm.addRecipe(r2));
        assertTrue(cm.addRecipe(r3));
        assertTrue(cm.addRecipe(r4));
        Recipe[] recipes = cm.getRecipes();
        assertNotNull(recipes, "The returned array should not be null");
        assertEquals(recipes.length, NUM_RECIPES, "The returned array should contain the correct number of recipes");
        assertTrue(recipes[0] instanceof Recipe, "The elements in the returned array should be Recipe objects");
    }

    // Test to add one recipe as normal
    @Test
    public void testAddRecipe_Normal() {
        boolean isRecipeAdded = cm.addRecipe(r1);
        assertTrue(isRecipeAdded);
    }

    // Test to add a duplicate recipe
    @Test
    public void testAddRecipe_Duplicate() {
        assertTrue(cm.addRecipe(r2));
        assertFalse(cm.addRecipe(r2));
    }

    // Test to add a recipe with null value
    @Test
    public void testAddRecipe_Null() {
        assertThrows(Exception.class,()-> {
            cm.addRecipe(null);
        });
    }

    // Test to add the maximum number of recipes
    @Test
    public void testAddRecipe_Max() {
        assertTrue(cm.addRecipe(r1));
        assertTrue(cm.addRecipe(r2));
        assertTrue(cm.addRecipe(r3));
        assertTrue(cm.addRecipe(r4));
    }
    // Test to add more than the maximum number of recipes
    @Test
    public void testAddRecipe_TooMany() {
        assertTrue(cm.addRecipe(r1));
        assertTrue(cm.addRecipe(r2));
        assertTrue(cm.addRecipe(r3));
        assertTrue(cm.addRecipe(r4));
        assertFalse(cm.addRecipe(r5), "Should not be able to add more than 4 recipes");
    }

    // Test to delete an existing recipe as normal
    @Test
    public void testDeleteRecipe_Normal() {
        String expected = "Black Coffee";
        assertTrue(cm.addRecipe(r1));
        String deleted = cm.deleteRecipe(0);
        assertEquals(expected, deleted, "Should return the name of the deleted recipe");
    }

    // Test to delete a non-existing recipe
    @Test
    public void testDeleteRecipe_NonExisting() {
        assertNull(cm.deleteRecipe(0), "Should return null for a non-existent recipe");
    }

    // Test to delete a previously deleted recipe
    @Test
    public void testDeleteRecipe_PrevDeleted() {
        String expected = "Warm Milk";
        assertTrue(cm.addRecipe(r5));
        String deleted = cm.deleteRecipe(0);
        assertEquals(expected, deleted, "Should return the name of the deleted recipe");
        String deletedAgain = cm.deleteRecipe(0);
        assertEquals(deletedAgain, null, "Should not be able to delete a recipe that has already been deleted");
    }

    // Test to attempt deleting a recipe via a negative or otherwise out-of-bounds number
    @Test
    public void testDeleteRecipe_OutOfBounds() {
        assertThrows(Exception.class,()-> {
            cm.deleteRecipe(-1);
        });
    }

    // Test to edit an existing recipe
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

    // Test to edit a non-existing recipe
    @Test
    public void testEditRecipe_NonExisting() throws RecipeException {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Latte");
        newRecipe.setPrice("55");
        newRecipe.setAmtChocolate("0");
        newRecipe.setAmtCoffee("2");
        newRecipe.setAmtMilk("5");
        newRecipe.setAmtSugar("3");

        String edited = cm.editRecipe(0, newRecipe);
        assertNull(edited, "Should not be able to edit a non-existing recipe");
    }

    // Test to attempt editing a recipe via a negative or otherwise out-of-bounds number
    @Test
    public void testEditRecipe_OutOfBounds() {
        assertThrows(Exception.class, () -> {
            cm.editRecipe(-1, r1);
        });
    }
}
