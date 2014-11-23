package ants;

import mails.Message;
import places.Anthill;

public class Healer extends Ant {
	
	private Ant target;
	private Ant bleeding;
	public Healer (Anthill anthill) {
		super(anthill);
	}
	
	@Override
	public boolean moveOn() {
		if (this.position == null && this.target.getPosition() == null) {
			this.sendMessage(new Message(this, this.anthill, "CareOK", this.bleeding));
			this.bleeding = null;
			this.position = null;
			return true;
		}
		else if (this.position.getCome()) {
			if (this.position.getPosition() == this.target.getPosition().getPosition()) {
				this.bleeding = this.target;
				this.target = null;
				this.position.setCome();
			}
			else goForward();
		}
		else {
			if (this.position.getPosition() == 0) {
				this.sendMessage(new Message(this, this.anthill, "CareOK", this.bleeding));
				this.bleeding = null;
				this.position = null;
				return true;
			}
			else goBack();
		}
		return false;			
	}

	public Ant getTarget() {
		return this.target;
	}

	public void setTarget(Ant target) {
		this.target = target;
	}
	
}
