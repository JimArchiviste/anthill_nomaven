package main;

import java.util.ArrayList;

import mails.Message;
import mails.MessageBox;
import places.Anthill;

public class Main {
	
	private static int nbCycle = 0;

	/**
	 * Return the number of cycles
	 * @return int
	 */
	public static int getNbCycle() {
		return nbCycle;
	}

	public static void main(String[] args) {
		
		int nbCycle = 200;
		
		Anthill anthill = new Anthill(nbCycle);
		MessageBox.getInstance();
		//Do all the cycles
		for (; Main.nbCycle < nbCycle ; Main.nbCycle++) {
			System.out.println("------------ Start of a new cycle #" + (Main.nbCycle+1) + " ------------\n");
			//The anthill does what it has to do
			anthill.newCycle();
			System.out.println("== Management of the messages");
			//All the messages for the anthill are managed
			//If there is no healer available we put the message in the message box again, but not right after,
			//if so the message box is never empty
			ArrayList<Message> messages = new ArrayList<Message>();
			while (!MessageBox.isEmpty()) {
				Message m = MessageBox.getNextMessage();
				if(m.action()) {
					messages.add(m);
				}
				MessageBox.deleteMessage();
			}
			//We put the messages which were not managed in the message box again
			if (!messages.isEmpty()) {
				for (Message message : messages) {
					MessageBox.receiveMessage(message);
				}
			}
			System.out.println("== End of the messages\n");
			System.out.println("------------ End of the cycle ------------\n\n");
		}
	}

}
