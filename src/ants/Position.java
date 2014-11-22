package ants;

import places.Site;

public class Position {
	
	private Site site;
	private int position;
	private boolean come;

	public Position(Site site, int position) {
		super();
		this.site = site;
		this.position = position;
		this.come = true;
	}

	public Site getSite() {
		return this.site;
	}

	public int getPosition() {
		return this.position;
	}
	
	public boolean getCome() {
		return this.come;
	}

	public void add(int position) {
		this.position += position;
	}
	
	public void remove(int position) {
		this.position -= position;
	}

	public void setCome() {
		this.come ^= true;
	}

	public void setPosition(int position) {
		this.position = position;		
	}
}
