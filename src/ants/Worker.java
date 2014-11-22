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
					this.sendMessage(new Message(this, this.anthill, "Empty Site : " + this.position.getSite().getDistance()));
				}
				else if (food_taken < this.capacity) {
					this.food = food_taken;
					this.sendMessage(new Message(this, this.anthill, "Empty Site" + this.position.getSite().getDistance()));
				}
				else {
					this.food = food_taken;
				}
				this.position.setCome();
			}
			else if (!this.position.getSite().equals(this.anthill.getAvailableSite())) {
				goBack();
				this.position.setCome();
			}
			else goForward();
		}
		else {
			if (this.position.getPosition() == 0) {
				//this.anthill.addFood(this.food);
				//this.capacity = 0;
				this.sendMessage(new Message(this, this.anthill, "Food Repository : " + this.food));
				this.position = null;
				this.position.setCome();
				return true;
			}
			else goBack();
		}
		return false;
	}
}
