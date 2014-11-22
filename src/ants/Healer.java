package ants;

import places.Anthill;

public class Healer extends Ant {

	public Healer (Anthill anthill) {
		super(anthill);
	}
	
	@Override
	public boolean moveOn() {
		return false;		
	}

}
