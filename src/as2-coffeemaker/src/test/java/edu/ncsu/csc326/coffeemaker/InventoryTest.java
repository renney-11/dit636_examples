package edu.ncsu.csc326.coffeemaker;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryTest {
    private Inventory inventory;
    private Recipe r1;
    private Recipe r2;
    @BeforeEach
    void setUp() throws Exception{
        inventory = new Inventory();
        r1 = new Recipe();
        r1.setName("Coffee");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("3");
        r1.setAmtMilk("1");
        r1.setAmtSugar("1");
        r1.setPrice("50");

        r2 = new Recipe();
        r2.setName("Latte");
        r2.setAmtChocolate("20");
        r2.setAmtCoffee("20");
        r2.setAmtMilk("20");
        r2.setAmtSugar("20");
        r2.setPrice("20");
    }

    @Test
    public void testGetChocolate(){
        int chocolate = inventory.getChocolate();
        assertEquals(15,chocolate);
    }

    @Test
    public void testSetChocolate_Normal() {
        inventory.setChocolate(20);
        assertEquals(20, inventory.getChocolate());
    }

    // Fault in original code, so test doesn't work as it should
    @Test
    public void testSetChocolate_Invalid() throws InventoryException{
        assertThrows(InventoryException.class,()-> {
            inventory.setChocolate(-1);
        });
    }

    @Test
    public void testAddChocolate_Normal()throws InventoryException {
        int initialChocolate = inventory.getChocolate();
        inventory.addChocolate("5");
        assertEquals(initialChocolate+5, inventory.getChocolate());
    }

    @Test
    public void testAddChocolate_NegativeInput() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addChocolate("-1");
        });
    }

    @Test
    public void testAddChocolate_NonInteger() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addChocolate("hello");
        });
    }

    @Test
    public void testAddChocolate_NullInput() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addChocolate(null);
        });
    }

    @Test
    public void testGetCoffee(){
        int coffee = inventory.getCoffee();
        assertEquals(15,coffee);
    }

    @Test
    public void testSetCoffee_Normal() {
        inventory.setCoffee(20);
        assertEquals(20, inventory.getCoffee());
    }

    // Fault in original code, so test doesn't work as it should
    @Test
    public void testSetCoffee_Invalid() throws InventoryException{
        assertThrows(InventoryException.class,()-> {
            inventory.setCoffee(-1);
        });
    }

    @Test
    public void testAddCoffee_Normal()throws InventoryException {
        int initialCoffee = inventory.getCoffee();
        inventory.addCoffee("5");
        assertEquals(initialCoffee+5, inventory.getCoffee());
    }

    @Test
    public void testAddCoffee_NegativeInput() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addCoffee("-1");
        });
    }

    @Test
    public void testAddCoffee_NonInteger() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addCoffee("hello");
        });
    }

    @Test
    public void testAddCoffee_NullInput() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addCoffee(null);
        });
    }

    @Test
    public void testGetMilk(){
        int milk = inventory.getMilk();
        assertEquals(15, milk);
    }

    @Test
    public void testSetMilk_Normal() {
        inventory.setMilk(20);
        assertEquals(20, inventory.getMilk());
    }

    // Fault in original code, so test doesn't work as it should
    @Test
    public void testSetMilk_Invalid() throws InventoryException{
        assertThrows(InventoryException.class,()-> {
            inventory.setMilk(-1);
        });
    }

    @Test
    public void testAddMilk_Normal()throws InventoryException {
        int initialMilk = inventory.getMilk();
        inventory.addMilk("5");
        assertEquals(initialMilk+5, inventory.getMilk());
    }

    @Test
    public void testAddMilk_NegativeInput() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addMilk("-1");
        });
    }

    @Test
    public void testAddMilk_NonInteger() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addMilk("hello");
        });
    }

    @Test
    public void testAddMilk_NullInput() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addMilk(null);
        });
    }

    @Test
    public void testGetSugar(){
        int sugar = inventory.getSugar();
        assertEquals(15,sugar);
    }

    @Test
    public void testSetSugar_Normal() {
        inventory.setSugar(20);
        assertEquals(20, inventory.getSugar());
    }

    // Fault in original code, so test doesn't work as it should
    @Test
    public void testSetSugar_Invalid() throws InventoryException{
        assertThrows(InventoryException.class,()-> {
            inventory.setSugar(-1);
        });
    }

    // Fault in the code so test cannot perform as it should
    @Test
    public void testAddSugar_Normal()throws InventoryException {
        int initialSugar = inventory.getSugar();
        inventory.addSugar("5");
        assertEquals(initialSugar+5, inventory.getSugar());
    }

    // Fault in the code so test cannot perform as it should
    @Test
    public void testAddSugar_NegativeInput() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addSugar("-1");
        });
    }

    @Test
    public void testAddSugar_NonInteger() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addSugar("hello");
        });
    }

    @Test
    public void testAddSugar_NullInput() throws InventoryException {
        assertThrows(InventoryException.class,()-> {
            inventory.addSugar(null);
        });
    }

    @Test
    public void testEnoughIngredients_Normal(){
        assertTrue(inventory.enoughIngredients(r1));
    }

    @Test
    public void testEnoughIngredients_Invalid(){
        assertFalse(inventory.enoughIngredients(r2));
    }

    @Test
    public void testUseIngredients_Normal(){
        assertEquals(true, inventory.useIngredients(r1));
    }

    @Test
    public void testUseIngredients_Invalid(){
        assertEquals(false, inventory.useIngredients(r2));
    }

    @Test
    public void testToString(){
        StringBuffer currentItems = new StringBuffer();
        currentItems.append("Coffee: " + inventory.getCoffee() + "\n");
        currentItems.append("Milk: " + inventory.getMilk() + "\n");
        currentItems.append("Sugar: " + inventory.getSugar() + "\n");
        currentItems.append("Chocolate: " + inventory.getChocolate() + "\n");
        assertEquals(inventory.toString(), currentItems.toString());
    }

}

