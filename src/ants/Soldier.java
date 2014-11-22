package ants;

import places.Anthill;

public class Soldier extends Ant {
	
	public Soldier (Anthill anthill) {
		super(anthill);
		this.speed = 2;
	}

	@Override
	public boolean moveOn() {
		if (this.position.getCome()) {
			if (this.position.getPosition() == this.position.getSite().getDistance()) {
				goBack();
				this.position.setCome();
			}
			else if (!this.position.getSite().equals(this.anthill.getAvailableSite())) {
				goBack();
				this.position.setCome();
			}
			else goForward();
			
			if (this.position.getPosition() > this.position.getSite().getDistance()) {
				this.position.setPosition(this.position.getSite().getDistance());
				this.position.setCome();
			}
		}
		else {
			goBack();
			if (this.position.getPosition() == 0) {
				this.position = null;
				this.position.setCome();
				return true;
			}
		}
		return false;		
	}

}
