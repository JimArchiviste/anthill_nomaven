package ants;

import places.Anthill;

public class Soldier extends Ant {
	
	/**
	 * Constructor of the soldier
	 * @param anthill
	 */
	public Soldier (Anthill anthill, int id) {
		super(anthill, id);
		this.speed = 2;
	}

	@Override
	public boolean moveOn() {
		//If the ant s on the way of a site
		if (this.position.getWay()) {
			//If it's already arrived to the site
			if (this.position.getDistance() == this.position.getSite().getDistance()) {
				goBack();
				System.out.println("The ant (soldier " + this.getId() + ") comes back from the site (site " + this.position.getSite().getDistance() + ")");
				this.position.setWay();
			}
			//If the site is now empty, no need to go at the end
			else if (this.position.getSite().getAmount() == 0) {
				goBack();
				System.out.println("The ant (soldier " + this.getId() + ") comes back from the site (site " + this.position.getSite().getDistance() + ")");
				this.position.setWay();
			}
			else{
				goForward();
				System.out.println("The ant (soldier " + this.getId() + ") goes to the site (site " + this.position.getSite().getDistance() + ")");
			}
			
			//If the soldier go too far
			if (this.position.getDistance() > this.position.getSite().getDistance()) {
				this.position.setPosition(this.position.getSite().getDistance());
				this.position.setWay();
			}
		}
		else {
			goBack();
			System.out.println("The ant (soldier " + this.getId() + ") comes back from the site (site " + this.position.getSite().getDistance() + ")");
			//If it came back to the ant
			if (this.position.getDistance() <= 0) {
				System.out.println("The ant (soldier " + this.getId() + ") is rteturned from the site (site " + this.position.getSite().getDistance() + ")");
				this.position = null;
				return true;
			}
		}
		return false;		
	}

}
