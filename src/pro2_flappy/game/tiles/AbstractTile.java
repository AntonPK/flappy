package pro2_flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import pro2_flappy.game.Tile;

public class AbstractTile implements Tile{
	Image image;
	public AbstractTile(Image image){
		this.image = image;
	}
	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
		//g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
	}

}
