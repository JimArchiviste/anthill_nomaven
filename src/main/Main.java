package main;

import java.util.ArrayList;

import mails.Message;
import places.Anthill;

public class Main {
	
	private static int nbCycle = 0;

	public static int getNbCycle() {
		return nbCycle;
	}

	public static void main(String[] args) {
		
		int nbCycle = 200;
		//Create all the anthills
		ArrayList<Anthill> anthills = new ArrayList<Anthill>();
		for (int i  = 0; i < 1; i++) {
			anthills.add(new Anthill(nbCycle));
		}

		//Do all the cycles
		for (; Main.nbCycle < nbCycle ; Main.nbCycle++) {
			System.out.println("------------ Start of a new cycle #" + (Main.nbCycle+1) + " ------------\n");
			int j  = 1;
			//Each anthill does a cycle
			for (Anthill anthill : anthills) {
				System.out.println("------------ Anthill n°" + j + " ------------" );
				//The anthill does what it has to do
				anthill.newCycle();
				System.out.println("== Management of the messages");
				//All the messages for the anthill are managed
				while (!anthill.getMessageBox().isEmpty()) {
					Message m = anthill.getMessageBox().getNextMessage();
					m.action();
					anthill.getMessageBox().deleteMessage();
				}
				
				System.out.println("== End of the messages\n");
			}
			System.out.println("------------ End of the cycle ------------\n\n");
			
			/**try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}**/
		}
	}

}
