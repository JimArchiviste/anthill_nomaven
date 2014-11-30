package mails;

import places.Anthill;
import ants.*;

public class Message {
	
	private Ant from;
	private Anthill to;
	private String object;
	private int message;
	private Ant attachment;


	/**
	 * Standard constructor
	 * @param from
	 * @param to
	 * @param object
	 */
	public Message(Ant from, Anthill to, String object) {
		super();
		this.from = from;
		this.to = to;
		this.object = object;
	}

	/**
	 * Constructor for the food repository
	 * @param from
	 * @param to
	 * @param object
	 * @param message
	 */
	public Message(Ant from, Anthill to, String object, int message) {
		super();
		this.from = from;
		this.to = to;
		this.object = object;
		this.message = message;
	}

	/**
	 * Constructor for the dead message
	 * @param from
	 * @param to
	 * @param object
	 * @param attachment
	 */
	public Message(Healer from, Anthill to, String object, Ant attachment) {
		super();
		this.from = from;
		this.to = to;
		this.object = object;
		this.attachment = attachment;
	}

	/**
	 * Do the action of the message
	 * @return boolean
	 */
	public boolean action() {
		switch (this.object) {
			case "Food Repository" :
				System.out.println(this.message + " foods have been added to the anthill");
				this.to.addFood(this.message);
				break;
			case "Dead" :
				//If there is no healer available
				if(!this.to.sendHealer(this.from)) {
					System.out.println("An healer have been sent to the ant " + this.from.getId());
					return true;
				}
				break;
			case "Born Ants" :
				this.to.addAnts();
				break;
			case "CareOK" :
				System.out.println("The healer " + this.from.getId() + " brought back the ant " + this.attachment.getId());
				this.to.addDead(this.attachment);
				break;
			case "Empty Site" :
				this.to.setAvailableSite();
				break;
			default :
				break;
		}
		return false;
		
	}

}
