package places;

import java.util.ArrayList;

import mails.Message;
import mails.MessageBox;
import main.Main;
import ants.*;

public class Anthill {
	
	private Queen queen;
	private ArrayList<ArrayList<Ant>> inactive_ants;
	private ArrayList<ArrayList<Ant>> active_ants;
	private ArrayList<Ant> dead_ants;
	private ArrayList<Site> sites;
	private int food;
	private int availableSite;
	
	/**
	 * Constructor of the anthill
	 * @param nbCycle
	 */
	public Anthill (int nbCycle) {
		this.queen = new Queen(this, nbCycle, 0);
		this.inactive_ants = this.queen.bornAnts();
		this.active_ants = new ArrayList<ArrayList<Ant>>();
		//We prepare the ArrayList of the ective ants
		for (int i = 0; i < 3; i++) {
			ArrayList<Ant> ants = new ArrayList<Ant>();
			active_ants.add(ants);
		}
		this.sites = new ArrayList<Site>();
		//We create the sites
		for (int i = 1; i < 6; i++) {
			Site site = new Site(i);
			sites.add(site);
		}
		this.food = 0;
		this.availableSite = 0;
		this.dead_ants = new ArrayList<Ant>();
	}

	/**
	 * Do the actions of the new cycle
	 */
	public void newCycle() {
		//Born the new ants
		if (Main.getNbCycle() % 5 == 4) {
			System.out.println("The queen is pregnant.");
			this.queen.sendMessage(new Message(this.queen, this, "Born Ants"));
		}
		
		//Management of the worker and the soldier (the only difference is there speed)
		for (int i = 0; i < 2; i++) {
			//We create an ArrayList of temporary ants
			ArrayList<ArrayList<Ant>> temporary_ants = new ArrayList<ArrayList<Ant>>();
			//We will put in this ArrayList the ants wich will be active at the end of this cycle
			ArrayList<Ant> actives = new ArrayList<Ant>();
			//And here the inactive
			ArrayList<Ant> inactives = new ArrayList<Ant>();
			temporary_ants.add(actives);
			temporary_ants.add(inactives);
			//Each ant which is already active move
			while (!this.active_ants.get(i).isEmpty()) {
				Ant that = this.active_ants.get(i).get(0);
				//If at the end of this cycle they come back to the anthill, they become inactive
				if(!that.moveOn()){
					//Active ants
					temporary_ants.get(0).add(that);
				}
				else {
					//Inactive ants
					temporary_ants.get(1).add(that);
				}
				//We remove the ant that was managed
				this.active_ants.get(i).remove(0);
			}
			//Each inactive ant go on a new site
			while (!this.inactive_ants.get(i).isEmpty()) {
				Ant that = this.inactive_ants.get(i).get(0);
				that.goToSite(this.sites.get(this.availableSite));
				if(i == 0) {
					System.out.println("The ant (worker " + that.getId() + ") goes to the site (site " + this.sites.get(this.availableSite).getDistance() + ")");
				}
				else {
					System.out.println("The ant (soldier " + that.getId() + ") goes to the site (site " + this.sites.get(this.availableSite).getDistance() + ")");
				}
				//We add the ant to the temporary active
				temporary_ants.get(0).add(that);
				//Then we remove it from the original ArrayList
				this.inactive_ants.get(i).remove(0);
			}
			//Then we put them again in  the right ArrayList...
			for (Ant ant : temporary_ants.get(0)) {
				//... Only if it's not too old
				if (!ant.getOlder()) {
					this.active_ants.get(i).add(ant);	
				}
			}
			for (Ant ant : temporary_ants.get(1)) {
				if (!ant.getOlder()) {
					this.inactive_ants.get(i).add(ant);	
				}
			}
		}
		
		//Same as before
		ArrayList<ArrayList<Ant>> temporary_ants = new ArrayList<ArrayList<Ant>>();
		ArrayList<Ant> actives = new ArrayList<Ant>();
		ArrayList<Ant> inactives = new ArrayList<Ant>();
		temporary_ants.add(actives);
		temporary_ants.add(inactives);
		//Management of the healer
		while (!this.active_ants.get(2).isEmpty()) {
			//We must convert the ant into a specific Healer
			Healer that = (Healer) this.active_ants.get(2).get(0);
			//If the healer is active but has no position yet, it's because he just got his orders,
			//so we send him to his target
			if(that.getPosition() == null && !(that.getTarget().getPosition() == null)) {
				that.goToSite(that.getTarget().getPosition().getSite());
			}
			//Else he does his move, the rest is the same as the workers and soldiers
			else {
				if(!that.moveOn()){
					//active
					temporary_ants.get(0).add(that);
				}
				else {
					//inactive
					temporary_ants.get(1).add(that);
				}
				this.active_ants.get(2).remove(0);
			}
		}
		for (Ant ant : temporary_ants.get(0)) {
			if (!ant.getOlder()) {
				this.active_ants.get(2).add(ant);	
			}
		}
		for (Ant ant : temporary_ants.get(1)) {
			if (!ant.getOlder()) {
				this.inactive_ants.get(2).add(ant);	
			}
		}
		
		//The queen get older
		this.queen.getOlder();	
	}
	
	/**
	 * Add some food to the site
	 * @param food
	 */
	public void addFood(int food) {
		this.food += food;
	}

	/**
	 * Update the available site to send the ants
	 */
	public void setAvailableSite() {
		this.availableSite = this.availableSite + 1;
		if (this.availableSite == 5) this.availableSite = 0;
	}

	/**
	 * Add an healer to the ArrayList of the active healers
	 * @param bleeding
	 */
	public boolean sendHealer(Ant bleeding) {
		if (!this.inactive_ants.get(2).isEmpty()) {
			Healer healer = (Healer) this.inactive_ants.get(2).get(0);
			healer.setTarget(bleeding);
			this.active_ants.get(2).add(healer);
			this.inactive_ants.get(2).remove(0);
		}
		//If there is no healer available
		else {
			return true;
		}
		return false;
	}

	/**
	 * Add a ant to the dead ants
	 * @param dead
	 */
	public void addDead(Ant dead) {
		this.dead_ants.add(dead);
	}

	/**
	 * Add the new born to the inactive ants
	 */
	public void addAnts() {
		ArrayList<ArrayList<Ant>> newBorn = this.queen.bornAnts();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < newBorn.get(i).size(); j++) {
				this.inactive_ants.get(i).add(newBorn.get(i).get(j));
			}
		}
	}
}
