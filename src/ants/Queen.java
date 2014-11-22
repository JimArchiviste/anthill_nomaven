package ants;

import java.util.ArrayList;

import places.Anthill;

public class Queen extends Ant {

	public Queen (Anthill anthill, int nbCycle) {
		super(anthill);
		this.life = nbCycle;
		this.speed = 0;
	}

	public ArrayList<ArrayList<Ant>> bornAnts() {
		ArrayList<ArrayList<Ant>> ants = new ArrayList<ArrayList<Ant>>();
		//add Workers
		ArrayList<Ant> workers = new ArrayList<Ant>();
		for (int i = 0 ; i < (int)(10 + Math.random() * 20); i++) {
			Ant ant = new Worker(this.anthill);
			workers.add(ant);
		}
		ants.add(workers);
		//add Soldiers
		ArrayList<Ant> soldiers = new ArrayList<Ant>();
		for (int i = 0 ; i < (int)(6 + Math.random() * 12); i++) {
			Ant ant = new Soldier(this.anthill);
			soldiers.add(ant);
		}
		ants.add(soldiers);
		//add Healers
		ArrayList<Ant> healers = new ArrayList<Ant>();
		for (int i = 0 ; i < (int)(4 + Math.random() * 8); i++) {
			Ant ant = new Healer(this.anthill);
			healers.add(ant);
		}
		ants.add(healers);
		return ants;
	}

	@Override
	public boolean moveOn() {
		return false;
	}
	
}
