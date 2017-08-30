package presentationLayer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import businessLayer.WarehouseLogic;

/**
 * Console CLI to demonstrate working application logic. 
 * 16 threads running pickers and restockers.
 * Pickers successfuly avoid trying to remove a product that is  at level 0.
 *
 * @author Nujoma Halls
 *
 */
public class Console_CLI {

	public static void main(String[] args)  {
		
		final WarehouseLogic consoleDemo = new WarehouseLogic();
		System.out.println("Product location: A90. Initial Inventory Level: 16");

		Thread restocker1 = new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					//adding 1 to  the product located at "A90"
					consoleDemo.restock("A90", 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread restocker2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consoleDemo.restock("A90", 2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread picker1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//removing three of the product located at "A90"
					consoleDemo.pick("A90", 3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread picker2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consoleDemo.pick("A90", 2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		ExecutorService pool = Executors.newFixedThreadPool(4);
		ExecutorService pool2 = Executors.newFixedThreadPool(4);
		ExecutorService pool3 = Executors.newFixedThreadPool(4);
		ExecutorService pool4 = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 4; i++) {
			Thread thread = new Thread(restocker1);
			Thread thread2 = new Thread(picker1);
			Thread thread3 = new Thread(picker2);
			Thread thread4 = new Thread(restocker2);
			pool.execute(thread);
			pool2.execute(thread2);
			pool3.execute(thread3);
			pool4.execute(thread4);
		}

		pool.shutdown();
		pool2.shutdown();
		pool3.shutdown();
		pool4.shutdown();

	}

}
	
	


