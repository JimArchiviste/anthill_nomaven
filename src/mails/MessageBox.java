package mails;

import java.util.ArrayList;

import ants.Ant;
import places.Anthill;

public class MessageBox {

	private static ArrayList<Message> messages = new ArrayList<Message>();
	
	private MessageBox()
	{}
	
	private static MessageBox INSTANCE = new MessageBox();
	
	public static MessageBox getInstance()
	{	
		return INSTANCE;
	}
	/**public MessageBox() {
		this.messages = new ArrayList<Message>();
	}**/

	/**
	 * Indicate if the messageBox is empty
	 * @return boolean
	 */
	public static boolean isEmpty() {
		if (messages.isEmpty()) return true;
		return false;
	}

	/**
	 * Get the first message of the messageBox
	 * @return
	 */
	public static Message getNextMessage() {
		return messages.get(0);
	}

	/**
	 * Delete the first message of the message box
	 */
	public static void deleteMessage() {
		messages.remove(0);
	}

	/**
	 * Add a message to the message box
	 * @param message
	 */
	public static void receiveMessage(Message message) {
		MessageBox.messages.add(message);
	}

}
