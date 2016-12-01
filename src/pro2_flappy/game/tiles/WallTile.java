package pro2_flappy.game.tiles;

import java.awt.Graphics;

import pro2_flappy.game.Tile;

public class WallTile implements Tile {

	@Override
	public void draw(Graphics g, int x, int y) {

		g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
	}

}
