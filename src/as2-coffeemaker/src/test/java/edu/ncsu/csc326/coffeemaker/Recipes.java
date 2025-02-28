package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Recipes {
    private Recipe defaultRecipe;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;


    @BeforeEach
    public void setUp() throws RecipeException {
        defaultRecipe = new Recipe();

        r1 = new Recipe();
        r1.setName("Latte");
        r1.setPrice("50");
        r1.setAmtCoffee("2");
        r1.setAmtMilk("1");
        r1.setAmtSugar("3");
        r1.setAmtChocolate("4");

        r2 = new Recipe();
        r2.setName("Cappuccino");
        r2.setPrice("60");
        r2.setAmtCoffee("3");
        r2.setAmtMilk("2");
        r2.setAmtSugar("4");
        r2.setAmtChocolate("5");

        r3 = new Recipe();
        r3.setName("Latte");
        r3.setPrice("50");
        r3.setAmtCoffee("2");
        r3.setAmtMilk("1");
        r3.setAmtSugar("3");
        r3.setAmtChocolate("4");

        r4 = new Recipe();
        r4.setName("Hot Chocolate");
        r4.setAmtChocolate("4");
        r4.setAmtCoffee("0");
        r4.setAmtMilk("1");
        r4.setAmtSugar("1");
        r4.setPrice("65");
    }

    @Test
    public void testGetAmtChocolate_Normal() {
        assertNotEquals(0, r2.getAmtChocolate());
    }

    @Test
    public void testGetAmtMilk_Normal() {
        assertNotEquals(0, r2.getAmtMilk());
    }

    @Test
    public void testGetAmtCoffee_Normal() {
        assertNotEquals(0, r2.getAmtCoffee());
    }

    @Test
    public void testGetAmtSugar_Normal() {
        assertNotEquals(0, r2.getAmtSugar());
    }

    @Test
    public void testPutAmtChocolate_Normal() throws RecipeException{
        defaultRecipe.setAmtChocolate("2");
        assertEquals(2, defaultRecipe.getAmtChocolate());
    }

    @Test
    public void testPutAmtCoffee_Normal() throws RecipeException{
        defaultRecipe.setAmtCoffee("2");
        assertEquals(2, defaultRecipe.getAmtCoffee());
    }

    @Test
    public void testPutAmtMilk_Normal() throws RecipeException{
        defaultRecipe.setAmtMilk("2");
        assertEquals(2, defaultRecipe.getAmtMilk());
    }

    @Test
    public void testPutAmtSugar_Normal() throws RecipeException{
        defaultRecipe.setAmtSugar("2");
        assertEquals(2, defaultRecipe.getAmtSugar());
    }

    @Test
    public void testPutAmtChocolate_Negative() throws RecipeException{
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtChocolate("-1");
        });
    }

    @Test
    public void testPutAmtCoffee_Negative() throws RecipeException{
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtCoffee("-1");
        });
    }

    @Test
    public void testPutAmtMilk_Negative() throws RecipeException{
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtMilk("-1");
        });
    }

    @Test
    public void testPutAmtSugar_Negative() throws RecipeException{
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtSugar("-1");
        });
    }

    @Test
    public void testPutAmtMilk_null() {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtMilk(null);
        });
    }

    @Test
    public void testPutAmtSugar_null() {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtSugar(null);
        });
    }

    @Test
    public void testPutAmtCoffee_null() {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtCoffee(null);
        });
    }

    @Test
    public void testPutAmtChocolate_null() {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtChocolate(null);
        });
    }

    @Test
    public void testPutAmtChocolate_nonNumeric() throws RecipeException {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtChocolate("non-numeric");
        });
    }

    @Test
    public void testPutAmtCoffee_nonNumeric() throws RecipeException {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtCoffee("non-numeric");
        });
    }

    @Test
    public void testPutAmtSugar_nonNumeric() throws RecipeException {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtSugar("non-numeric");
        });
    }

    @Test
    public void testPutAmtMilk_nonNumeric() throws RecipeException {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setAmtMilk("non-numeric");
        });
    }

    @Test
    public void testGetName_empty() {
        assertEquals("", defaultRecipe.getName());
    }

    @Test
    public void testGetName_Normal() {
        defaultRecipe.setName("Coffee");
        assertEquals("Coffee", defaultRecipe.getName());
    }

    @Test
    public void testPutName_null() throws RecipeException {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setName(null);
        });
    }

    @Test
    public void testPutName_empty() throws RecipeException {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setName("");
        });
    }

    @Test
    public void testPutPrice_Normal() throws RecipeException {
        assertEquals(50, r1.getPrice());
    }

    @Test
    public void testPutPrice_Negative() {
        assertThrows(RecipeException.class, () -> {
            defaultRecipe.setPrice("-100");
        });
    }

    @Test
    public void testGetPrice_Normal() {
        assertEquals(0, defaultRecipe.getPrice());
    }

    @Test
    public void testToString_emptyString() {
        assertEquals("", defaultRecipe.toString());
    }

    @Test
    public void testEquals_SameName() throws RecipeException {
        Recipe r4 = new Recipe();
        r4.setName("Cappuccino");
        r4.setPrice("80");
        r4.setAmtCoffee("3");
        r4.setAmtMilk("2");
        r4.setAmtSugar("4");
        r4.setAmtChocolate("5");
        assertEquals(r2, r4);
    }

    @Test
    public void testEquals_DifferentRecipes() {
        assertNotEquals(r1, r2);
    }

    @Test
    public void testEquals_NullName() {
        r1.setName(null);
        assertFalse(r1.equals(r2));
    }

    @Test
    public void testHashCode_Normal() {
        assertNotEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testHashCode_SameRecipes() {
        assertEquals(r1.hashCode(), r1.hashCode());
    }
}