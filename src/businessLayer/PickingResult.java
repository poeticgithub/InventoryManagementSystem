package businessLayer;

/**
 * *Has one private member which becomes true if a valid removal is attempted.
 * 
 * @author Nujoma Halls
 */
public class PickingResult {
	/**
	 * Boolean whose status determines whether or not an inventory update should
	 * occur.
	 */
	private boolean availibility = false;

	public PickingResult() {
	}

	/**
	 * Set Method to set the private data member
	 * 
	 * @param mybool
	 *            The desired value
	 * 
	 */
	public void setAvailibility(boolean mybool) {
		availibility = mybool;
	}

	/**
	 * Get Method to return the value of the private data member
	 * 
	 * @return private data member "availibility"
	 */
	public boolean getAvailibility() {
		return availibility;
	}
}
