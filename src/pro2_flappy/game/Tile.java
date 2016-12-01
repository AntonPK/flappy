package pro2_flappy.game;

import java.awt.Graphics;

/**
 * 
 * Reprezentuje herni objekt umisteny do matice herni plochy
 * 
 * @author plasido1
 *
 */

public interface Tile {

	/**
	 * Sirka a vyska dlazdice v px
	 */
	static final int SIZE = 20;

	/**
	 * Kresli herni objekt na platno g.
	 * 
	 * @param x x-ova souradnice v px na obrazovce, kam se dlazdice nakresli
	 * @param y y-ova souradnice v px na obrazovce, kam se dlazdice nakresli
	 * @param g
	 */
	void draw(Graphics g, int x, int y);

}
