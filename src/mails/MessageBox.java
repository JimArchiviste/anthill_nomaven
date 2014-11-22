package mails;

import java.util.ArrayList;

import ants.Ant;
import places.Anthill;

public class MessageBox {

	private ArrayList<Message> messages;
	
	public MessageBox() {
		this.messages = new ArrayList<Message>();
	}

	public boolean isEmpty() {
		if (messages.isEmpty()) return true;
		return false;
	}

	public Message getNextMessage() {
		return messages.get(0);
	}

	public void deleteMessage() {
		messages.remove(0);
	}

	public void receiveMessage(Message message) {
		this.messages.add(message);
	}

}
