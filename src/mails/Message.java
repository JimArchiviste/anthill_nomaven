package mails;

import places.Anthill;
import ants.*;

public class Message {
	
	private Ant from;
	private Anthill to;
	private String object;


	public Message(Ant from, Anthill to, String object) {
		super();
		this.from = from;
		this.to = to;
		this.object = object;
	}

	public void action() {
	}

}
