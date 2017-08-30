package businessLayer;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * JUnit test to check the business logic of the application.
 * 
 * @author Nujoma Halls
 *
 */
public class WarehouseTest {
	WarehouseLogic myunit = new WarehouseLogic();
    /**
     * This test return false if picker attempts to remove too many items at once.
     */
	@Test
	public void pickProducTest1() {	  
		PickingResult testcase1 = myunit.pickProduct("A90", 10000);
	    boolean testBool1 = testcase1.getAvailibility();
	    assertEquals(false, testBool1);
	}
	/**
	 * This test returns true if picker attempts to remove a single item and the item level is not equal to 0.
	 *
	 */
	@Test
	public void pickProducTest2() {
	    PickingResult testcase2 = myunit.pickProduct("A90", 1);
		boolean testBool2 = testcase2.getAvailibility();
		assertEquals(true, testBool2);
	}
	/**
	 * This test returns false if user attempts to add too many items at once.
	 */
	@Test
	public void restoreProducTest1() {
		RestockingResult testcase3 = myunit.restockProduct("A90", 20000);
		boolean testBool3 = testcase3.getAvailibility();
		assertEquals(false, testBool3);
		
	}
	/**
	 * This test returns true if user attempts to add a single item and still remain at or below
	 * the maximum level for the product.
	 * 
	 */
	@Test
	public void restoreProducTest2() {	  
	    RestockingResult testcase4 = myunit.restockProduct("A90", 1);
	    boolean testBool4 = testcase4.getAvailibility();
	    assertEquals(true, testBool4);
	}
	    
}
