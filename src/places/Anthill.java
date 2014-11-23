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
	private MessageBox messagebox;
	private ArrayList<Site> sites;
	private int food;
	private int availableSite;
	
	public Anthill (int nbCycle) {
		this.queen = new Queen(this, nbCycle);
		this.inactive_ants = this.queen.bornAnts();
		this.active_ants = new ArrayList<ArrayList<Ant>>();
		for (int i = 0; i < 3; i++) {
			ArrayList<Ant> ants = new ArrayList<Ant>();
			active_ants.add(ants);
		}
		this.messagebox = new MessageBox();
		this.sites = new ArrayList<Site>();
		for (int i = 1; i < 6; i++) {
			Site site = new Site(i);
			sites.add(site);
		}
		this.food = 0;
		this.availableSite = 0;
		this.dead_ants = new ArrayList<Ant>();
	}

	public void newCycle() {
		//Worker and Soldier
		if (Main.getNbCycle() % 5 == 0) {
			this.queen.sendMessage(new Message(this.queen, this, "Born Ants"));
		}
		for (int i = 0; i < 2; i++) {
			ArrayList<ArrayList<Ant>> temporary_ants = new ArrayList<ArrayList<Ant>>();
			ArrayList<Ant> actives = new ArrayList<Ant>();
			ArrayList<Ant> inactives = new ArrayList<Ant>();
			temporary_ants.add(actives);
			temporary_ants.add(inactives);
			while (!this.active_ants.get(i).isEmpty()) {
				Ant that = this.active_ants.get(i).get(0);
				if(!that.moveOn()){
					//active
					temporary_ants.get(0).add(that);
				}
				else {
					//inactive
					temporary_ants.get(1).add(that);
				}
				this.active_ants.get(i).remove(0);
			}
			while (!this.inactive_ants.get(i).isEmpty()) {
				//if (this.inactive_ants.get(i).isEmpty()) break;
				Ant that = this.inactive_ants.get(i).get(0);
				that.goToSite(this.sites.get(this.availableSite));
				temporary_ants.get(0).add(that);
				this.inactive_ants.get(i).remove(0);
			}
			for (Ant ant : temporary_ants.get(0)) {
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

		ArrayList<ArrayList<Ant>> temporary_ants = new ArrayList<ArrayList<Ant>>();
		ArrayList<Ant> actives = new ArrayList<Ant>();
		ArrayList<Ant> inactives = new ArrayList<Ant>();
		temporary_ants.add(actives);
		temporary_ants.add(inactives);
		//Healer
		while (!this.active_ants.get(2).isEmpty()) {
			Healer that = (Healer) this.active_ants.get(2).get(0);
			if(that.getPosition() == null && !(that.getTarget().getPosition() == null)) {
				that.goToSite(that.getTarget().getPosition().getSite());
			}
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
			
		this.queen.getOlder();	
	}


	public MessageBox getMessageBox() {
		return this.messagebox;
	}
	
	public void addFood(int food) {
		this.food += food;
	}

	public void setAvailableSite() {
		this.availableSite += 1;
		if (this.availableSite == 5) this.availableSite = 0;
	}

	public void sendHealer(Ant bleeding) {
		if (!this.inactive_ants.get(2).isEmpty()) {
			Healer healer = (Healer) this.inactive_ants.get(2).get(0);
			healer.setTarget(bleeding);
			this.active_ants.get(2).add(healer);
			this.inactive_ants.get(2).remove(0);
		}
	}

	public void addDead(Ant dead) {
		this.dead_ants.add(dead);
	}

	public void addAnts() {
		ArrayList<ArrayList<Ant>> newBorn = this.queen.bornAnts();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < newBorn.get(i).size(); j++) {
				this.inactive_ants.get(i).add(newBorn.get(i).get(j));
			}
		}
	}
}
