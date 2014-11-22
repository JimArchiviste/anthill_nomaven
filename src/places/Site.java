package places;


public class Site {
	
	private int distance;
	private int amount;

	public Site (int distance) {
		this.distance = distance;
		this.amount = 1000;
	}

	public int getDistance() {
		return this.distance;
	}

	public int getAmount() {
		return amount;
	}

	public int reduceAmount(int i) {
		if (this.amount == 0) return -1;
		this.amount -= i;
		if (this.amount > 0) return i;
		else if (this.amount < 0) return i + this.amount;
		else return 0;
	}
}
