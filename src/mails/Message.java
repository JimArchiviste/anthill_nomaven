package mails;

import places.Anthill;
import ants.*;

public class Message {
	
	private Ant from;
	private Anthill to;
	private String object;
	private String message;
	private Ant attachment;


	public Message(Ant from, Anthill to, String object) {
		super();
		this.from = from;
		this.to = to;
		this.object = object;
	}

	public Message(Ant from, Anthill to, String object, String message) {
		super();
		this.from = from;
		this.to = to;
		this.object = object;
		this.message = message;
	}

	public Message(Healer from, Anthill to, String object, Ant attachment) {
		super();
		this.from = from;
		this.to = to;
		this.object = object;
		this.attachment = attachment;
	}

	public void action() {
		switch (this.object) {
			case "Food Repository" :
				this.to.addFood(Integer.parseInt(this.message));
				break;
			case "Dead" :
				this.to.sendHealer(this.from);
				break;
			case "Born Ants" :
				this.to.addAnts();
				break;
			case "CareOK" :
				this.to.addDead(this.attachment);
				break;
			case "Empty Site" :
				this.to.setAvailableSite();
				break;
			default :
				break;
		}
		
	}

}
