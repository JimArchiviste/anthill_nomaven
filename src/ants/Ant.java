package ants;

import mails.Message;
import mails.MessageBox;
import places.*;

public abstract class Ant {

	protected final int id;
	protected int life;
	protected Position position;
	protected int speed;
	protected Anthill anthill;
	
	/**
	 * Constructor of the anthill
	 * @param anthill
	 */
	public Ant (Anthill anthill, int id) {
		this.life = 100;
		this.speed = 1;
		this.anthill = anthill;
		this.id = id;
	}
	
	/**
	 * Get the id of the ant
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Send the ant to a specific site
	 * @param site
	 */
	public void goToSite(Site site) {
		this.position = new Position(site, this.speed);
	}
	
	/**
	 * Make the ant go to the site
	 */
	protected void goForward() {
		this.position.add(this.speed);
	}
	
	/**
	 * Get the current position of the ant
	 * @return Position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Make the ant return to the anthill
	 */
	protected void goBack() {
		this.position.remove(this.speed);
	}

	/**
	 * Do the move of the ant
	 * @return
	 */
	public abstract boolean moveOn();
	
	/**
	 * Get the ant older
	 * @return boolean
	 */
	public boolean getOlder() {
		this.life -= 1;
		if (this.life == 0) {
			this.sendMessage(new Message(this, this.anthill, "Dead"));
			System.out.println("The ant " + this.getId() + " died");
			return true;
		}
		return false;
	}

	/**
	 * Make the ant send a message
	 * @param message
	 */
	public void sendMessage(Message message) {
		MessageBox.receiveMessage(message);
	}
}
