package places;


public class Site {
	
	//distance is also the ID
	private int distance;
	private int amount;

	/**
	 * Constructor of the site
	 * @param distance
	 */
	public Site (int distance) {
		this.distance = distance;
		this.amount = 7500;
	}

	/**
	 * Return the distance of the anthill relatively to the anthill
	 * @return int
	 */
	public int getDistance() {
		return this.distance;
	}

	/**
	 * Return the amount of food of the site
	 * @return int
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Reduce the amount of the site. Manage if the amount is empty or almost
	 * @param i
	 * @return int
	 */
	public int reduceAmount(int i) {
		//If there is no more food
		if (this.amount == 0) return -1;
		this.amount -= i;
		//After the ant took the food,
		//If there was enough food, no problem
		if (this.amount > 0){
			return i;
		}
		//Else if the amount is now under 0, it means that the amount had not enough food, so the ant took the rest
		else if (this.amount < 0) {
			int result = i + this.amount;
			this.amount = 0;
			return result;
		}
		//If the mount was exactly the same as the ant can carry on, we return 0
		else return 0;
	}
}
