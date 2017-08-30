package businessLayer;

/**
 * 
 * The domain objects in the warehouse. Consists of two properties: Location and
 * Level.
 * 
 * @author Nujoma Halls
 *
 */
public class DomainObject {
	private String location;
	private int level;

	public DomainObject(String location, int level) {
		this.location = location;
		this.level = level;
	}

	public DomainObject() {
	}

	/**
	 * 
	 * @return The location of the product.
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 *
	 * @param location
	 *            The location of the product
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @return The current amount of the product in the inventory.
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * 
	 * @param level
	 *            The current amount of the product in the inventory.
	 */
	public void setLevel(int level) {
		this.level = level;
	}

}
