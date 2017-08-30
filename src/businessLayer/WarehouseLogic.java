package businessLayer;

import databaseLayer.Database;

/**
 * The business logic of the application. Prevents pickers from attempting to
 * remove an item that is at level 0.
 *
 * @author T430
 *
 */
public class WarehouseLogic implements InventoryManagementSystem {

	Database test = Database.getDatabase();
	/**
	 * To prevent picker starvation. By adding this limiter, restockers will be
	 * forced to wait after reaching this maximum level
	 */
	private final int limiter = 20;
	private int current = 0;

	// Method from the InventoryManagementSystem Interface
	public PickingResult pickProduct(String productId, int amountToPick) {
		//Checks the current amount of the product and inserts it into variable called 'level'
		int level = test.myinventory.get(productId);
		PickingResult mypick = new PickingResult();
		if (amountToPick <= level)
			mypick.setAvailibility(true); //sets to true only if they are attempting to remove less than or the same as the current level
		return mypick;
	}

	// Method from the InventoryManagementSystem Interface
	public RestockingResult restockProduct(String productId, int amountToRestock) {
		//Checks the current amount of the product and inserts it into variable called 'level'
		int level = test.myinventory.get(productId);
		RestockingResult myRestock = new RestockingResult();
		if (level + amountToRestock <= limiter) //sets to true only if the addition is at or below the maximum allowed level
			myRestock.setAvailibility(true);
		return myRestock;
	}

	/**
	 * To remove product(s) from the Inventory.
	 * 
	 * @param productId Location of the product
	 * @param amountToPick The amount of the product to remove
	 * @throws InterruptedException
	 */
	public synchronized void pick(String productId, int amountToPick) throws InterruptedException {
		PickingResult testPick = pickProduct(productId, amountToPick);
		current = test.myinventory.get(productId);
		while (current == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Only updates the inventory if picker is removing the same as or less than the current level of the product
		if (testPick.getAvailibility()) {
			current -= amountToPick;
			test.myinventory.put(productId, current);
			System.out.println(
					"Picker Thread: " + Thread.currentThread().getName() + "  current inventory level: " + current);
			notifyAll();
		}
		//To slow down threads for demo purposes in the CLI presentation layer
		Thread.sleep(200);
	}

	/**
	 * To add product(s) to the Inventory.
	 * 
	 * @param productId Location of the product
	 * @param amountToRestock The amount of the product to add
	 * @throws InterruptedException
	 */
	public synchronized void restock(String productId, int amountToRestock) throws InterruptedException {
		RestockingResult testRestock = restockProduct(productId, amountToRestock);
		current = test.myinventory.get(productId);
		while (current == limiter) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Only updates the inventory if it remains below the maximum level after the addition
		if (testRestock.getAvailibility()) {
			current += amountToRestock;
			test.myinventory.put(productId, current);
			System.out.println(
					"Restocker Thread: " + Thread.currentThread().getName() + "  current inventory level: " + current);
			notifyAll();
		}
		//To slow down threads for demo purposes in the CLI presentation layer
		Thread.sleep(200);
	}

}
