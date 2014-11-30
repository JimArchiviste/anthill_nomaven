package ants;

import java.util.ArrayList;

import main.Main;
import places.Anthill;

public class Queen extends Ant {

	private int id_ant;
	
	/**
	 * Constructor of the queen
	 * @param anthill
	 * @param nbCycle
	 */
	public Queen (Anthill anthill, int nbCycle, int id) {
		super(anthill, id);
		this.life = nbCycle;
		this.speed = 0;
		this.id_ant = 1;
	}

	/**
	 * Born the new ants
	 * @return ArrayList<ArrayList<Ant>>
	 */
	public ArrayList<ArrayList<Ant>> bornAnts() {
		ArrayList<ArrayList<Ant>> ants = new ArrayList<ArrayList<Ant>>();
		//Depending of the cycle we need more or less healer
		if (Main.getNbCycle() < 95) {
			//add Workers
			ArrayList<Ant> workers = new ArrayList<Ant>();
			for (int i = 0 ; i < (int)(5 + Math.random() * 10); i++) {
				Ant ant = new Worker(this.anthill, id_ant);
				id_ant++;
				workers.add(ant);
			}
			ants.add(workers);
			//add Soldiers
			ArrayList<Ant> soldiers = new ArrayList<Ant>();
			for (int i = 0 ; i < (int)(3 + Math.random() * 6); i++) {
				Ant ant = new Soldier(this.anthill, id_ant);
				id_ant++;
				soldiers.add(ant);
			}
			ants.add(soldiers);
			//add Healers
			ArrayList<Ant> healers = new ArrayList<Ant>();
			ants.add(healers);
		}
		else if (Main.getNbCycle() >= 95 && Main.getNbCycle() <= 100) {
			ArrayList<Ant> workers = new ArrayList<Ant>();
			ants.add(workers);
			ArrayList<Ant> soldiers = new ArrayList<Ant>();
			ants.add(soldiers);
			ArrayList<Ant> healers = new ArrayList<Ant>();
			for (int i = 0 ; i < (int)(8 + Math.random() * 16); i++) {
				Ant ant = new Healer(this.anthill, id_ant);
				id_ant++;
				healers.add(ant);
			}
			ants.add(healers);
		}
		else {
			//add Workers
			ArrayList<Ant> workers = new ArrayList<Ant>();
			for (int i = 0 ; i < (int)(3 + Math.random() * 7); i++) {
				Ant ant = new Worker(this.anthill, id_ant);
				id_ant++;
				workers.add(ant);
			}
			ants.add(workers);
			//add Soldiers
			ArrayList<Ant> soldiers = new ArrayList<Ant>();
			for (int i = 0 ; i < (int)(2 + Math.random() * 5); i++) {
				Ant ant = new Soldier(this.anthill, id_ant);
				id_ant++;
				soldiers.add(ant);
			}
			ants.add(soldiers);
			//add Healers
			ArrayList<Ant> healers = new ArrayList<Ant>();
			for (int i = 0 ; i < (int)(5 + Math.random() * 8); i++) {
				Ant ant = new Healer(this.anthill, id_ant);
				id_ant++;
				healers.add(ant);
			}
			ants.add(healers);	
		}
		int i = ants.get(0).size() + ants.get(1).size() + ants.get(2).size();
		System.out.println(i + " new ants are came to the anthill");
		return ants;
	}

	@Override
	public boolean moveOn() {
		return false;
	}
	
}
