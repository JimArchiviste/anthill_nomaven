package ants;

import places.Site;

public class Position {
	
	private Site site;
	private int position;
	private boolean way;

	/**
	 * Constructor of the position
	 * @param site
	 * @param position
	 */
	public Position(Site site, int position) {
		super();
		this.site = site;
		this.position = position;
		this.way = true;
	}

	/**
	 * Get the site of the position
	 * @return Site
	 */
	public Site getSite() {
		return this.site;
	}

	/**
	 * Get the distance made
	 * @return int
	 */
	public int getDistance() {
		return this.position;
	}
	
	/**
	 * Get the way of the ant
	 * @return boolean
	 */
	public boolean getWay() {
		return this.way;
	}

	/**
	 * Add distance to the distance made
	 * @param position
	 */
	public void add(int position) {
		this.position += position;
	}
	
	/**
	 * Remove distance to the distance made
	 * @param position
	 */
	public void remove(int position) {
		this.position -= position;
	}

	/**
	 * Set the way of the ant
	 */
	public void setWay() {
		this.way ^= true;
	}

	/**
	 * Set the distance made
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;		
	}
}
