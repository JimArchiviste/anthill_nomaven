package ants;

import mails.Message;
import places.*;

public abstract class Ant {

	protected int life;
	protected Position position;
	protected int speed;
	protected Anthill anthill;
	
	public Ant (Anthill anthill) {
		this.life = 100;
		this.speed = 1;
		this.anthill = anthill;
	}
	
	public void goToSite(Site site) {
		this.position = new Position(site, this.speed);
	}
	
	protected void goForward() {
		this.position.add(this.speed);
	}
	
	protected void goBack() {
		this.position.remove(this.speed);
	}

	public abstract boolean moveOn();
	
	public boolean older() {
		this.life -= 1;
		if (this.life == 0) {
			this.sendMessage(new Message(this, this.anthill, "Dead"));
			return true;
		}
		return false;
	}

	public void sendMessage(Message message) {
		this.anthill.getMessageBox().receiveMessage(message);
	}
}
