package databaseLayer;

import businessLayer.DomainObject;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * Used Singleton Pattern for simulation of a database. Only one instance of
 * Database Class is allowed among all threads.
 * 
 * @author Nujoma Halls
 *
 */
public class Database {
   /**
    * Used concurrent HashMap to for  thread safety of shared data
    */
	public ConcurrentHashMap<String, Integer> myinventory = new ConcurrentHashMap<String, Integer>();
	private static Database database = null;

	private Database() {
	}

	/**
	 * Helper method to populate inventory for demo purposes. Ideally I would
	 * connect to an external SQL database.
	 */
	private void database_builder() {

		DomainObject mathbook = new DomainObject("A90", 16);
		DomainObject shoes = new DomainObject("L21", 7);
		DomainObject oreos = new DomainObject("Z53", 13);
		DomainObject umbrellas = new DomainObject("Q77", 2);

		myinventory.put(mathbook.getLocation(), mathbook.getLevel());
		myinventory.put(shoes.getLocation(), shoes.getLevel());
		myinventory.put(oreos.getLocation(), oreos.getLevel());
		myinventory.put(umbrellas.getLocation(), umbrellas.getLevel());

	}

	/**
	 * Public method used to instantiate the single instance of the Database
	 * class.
	 * 
	 * @return The only allowable instance of the Database class.
	 */
	public static Database getDatabase() {
		if (database == null) {
			database = new Database();
			database.database_builder();
		}

		return database;
	}

}
