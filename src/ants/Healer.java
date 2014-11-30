package ants;

import mails.Message;
import places.Anthill;

public class Healer extends Ant {
	
	private Ant target;
	private Ant bleeding;
	
	/**
	 * Constructor of the healer
	 * @param anthill
	 */
	public Healer (Anthill anthill, int id) {
		super(anthill, id);
	}
	
	@Override
	public boolean moveOn() {
		//If the target ant is dead in the anthill
		if (this.position == null && this.target.getPosition() == null) {
			this.sendMessage(new Message(this, this.anthill, "CareOK", this.target));
			System.out.println("The ant (healer " + this.getId() + ") took care of the ant (ant " + this.target.getId() + ")");
			this.target = null;
			this.position = null;
			return true;
		}
		//If the healer is in position to go forward
		else if (this.position.getWay()) {
			//If it arrives to the position of the target
			if (this.position.getDistance() == this.target.getPosition().getDistance()) {
				System.out.println("The ant (healer " + this.getId() + ") arrived to the ant (ant " + this.target.getId() + ")");
				this.bleeding = this.target;
				this.target = null;
				this.position.setWay();
			}
			//Else keep going
			else {
				goForward();
				System.out.println("The ant (soldier " + this.getId() + ") goes to the ant (ant " + this.target.getId() + ")");
			}
		}
		//Else if he goes back
		else {
			//If he came back to the anthill
			if (this.position.getDistance() == 0) {
				this.sendMessage(new Message(this, this.anthill, "CareOK", this.bleeding));
				System.out.println("The ant (healer " + this.getId() + ") took care of the ant (ant " + this.bleeding.getId() + ")");
				this.bleeding = null;
				this.position = null;
				return true;
			}
			//Else it goes back
			else {
				goBack();
				System.out.println("The ant (healer " + this.getId() + ") comes back with the ant (ant " + this.bleeding.getId() + ")");
			}
		}
		return false;			
	}

	/**
	 * Get the target of the healer
	 * @return Ant
	 */
	public Ant getTarget() {
		return this.target;
	}

	/**
	 * Set the target of the healer
	 * @param target
	 */
	public void setTarget(Ant target) {
		this.target = target;
	}
	
	@Override
	public boolean getOlder() {
		this.life -= 1;
		if (this.life == 0) {
			this.sendMessage(new Message(this, this.anthill, "Dead"));
			System.out.println("The ant " + this.getId() + " died");
			if (this.bleeding != null) {
				this.bleeding.getPosition().setPosition(this.position.getDistance());
				this.sendMessage(new Message(this.bleeding, this.anthill, "Dead"));
				System.out.println("The ant " + this.bleeding.getId() + " is still dead");
			}
			return true;
		}
		return false;
	}
	
}
