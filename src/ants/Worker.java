package ants;

import mails.Message;
import places.Anthill;

public class Worker extends Ant {
	
	private int food;
	private int capacity;

	public Worker(Anthill anthill) {
		super(anthill);
		this.food = 0;
		this.capacity = 13;
	}
	
	@Override
	public boolean moveOn() {
		if (this.position.getCome()) {
			if (this.position.getPosition() == this.position.getSite().getDistance()) {
				int food_taken = this.position.getSite().reduceAmount(this.capacity);
				if (food_taken == 0) {
					this.food = food_taken;
				}
				else if (food_taken == -1) {
					goBack();
				}
				else if (food_taken < this.capacity) {
					this.food = food_taken;
					this.sendMessage(new Message(this, this.anthill, "Empty Site", "" + this.position.getSite().getDistance()));
				}
				else {
					this.food = food_taken;
				}
				this.position.setCome();
			}
			else if (this.position.getSite().getAmount() == 0) {
				goBack();
				this.position.setCome();
			}
			else goForward();
		}
		else {
			if (this.position.getPosition() == 0) {
				this.sendMessage(new Message(this, this.anthill, "Food Repository", "" + this.food));
				this.food = 0;
				this.position = null;
				return true;
			}
			else goBack();
		}
		return false;
	}
	
}
