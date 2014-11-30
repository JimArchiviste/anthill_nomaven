package ants;

import mails.Message;
import places.Anthill;

public class Worker extends Ant {
	
	private int food;
	private int capacity;

	/**
	 * Constructor of the Worker
	 * @param anthill
	 */
	public Worker(Anthill anthill, int id) {
		super(anthill, id);
		this.food = 0;
		this.capacity = 13;
	}
	
	@Override
	public boolean moveOn() {
		//If it's on the way for a site
		if (this.position.getWay()) {
			//If it's arrived
			if (this.position.getDistance() == this.position.getSite().getDistance()) {
				//It takes the food (cf Site)
				int food_taken = this.position.getSite().reduceAmount(this.capacity);
				//System.out.println("food_taken" + food_taken);
				if (food_taken == 0) {
					this.food = food_taken;
					System.out.println("The ant (worker " + this.getId() + ") took " + this.food + " foods on the site "+ this.position.getSite().getDistance());
					this.sendMessage(new Message(this, this.anthill, "Empty Site"));
				}
				else if (food_taken == -1) {
					goBack();
					System.out.println("The ant (worker " + this.getId() + ") comes back from the site (site " + this.position.getSite().getDistance() + ")");
				}
				else if (food_taken < this.capacity) {
					this.food = food_taken;
					System.out.println("The ant (worker " + this.getId() + ") took " + this.food + " foods on the site "+ this.position.getSite().getDistance());
					this.sendMessage(new Message(this, this.anthill, "Empty Site"));
				}
				else {
					this.food = food_taken;
					System.out.println("The ant (worker " + this.getId() + ") took " + this.food + " foods on the site "+ this.position.getSite().getDistance());
				}
				this.position.setWay();
			}
			//Else if the site is now empty, no need to go at the end
			else if (this.position.getSite().getAmount() == 0) {
				goBack();
				System.out.println("The ant (worker " + this.getId() + ") comes back from the site (site " + this.position.getSite().getDistance() + ")");
				this.position.setWay();
			}
			else {
				goForward();
				System.out.println("The ant (worker " + this.getId() + ") walks to the site (site " + this.position.getSite().getDistance() + ")");
			}
		}
		else {
			//If the ant came back to the anthill it  gives its food to the anthill
			if (this.position.getDistance() == 0) {
				this.sendMessage(new Message(this, this.anthill, "Food Repository", this.food));
				System.out.println("The ant (worker " + this.getId() + ") is returned from the site (site " + this.position.getSite().getDistance() + "), with " + this.food + " foods");
				this.food = 0;
				this.position = null;
				return true;
			}
			//Else it just goes back
			else {
				goBack();
				System.out.println("The ant (worker " + this.getId() + ") comes back from the site (site " + this.position.getSite().getDistance() + ")");
			}
		}
		return false;
	}
	
}
