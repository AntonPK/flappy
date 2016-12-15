package pro2_flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

public class BonusTile extends AbstractTile {
	boolean isActive = true;
	public BonusTile(Image image) {
		super(image);
		
	}
	@Override
	public void draw(Graphics g, int x, int y) {
		if(isActive){
		g.drawImage(image, x, y, null);
		}
		//g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
	}
	public void setIsActive(boolean value){
		isActive = value;
	}
}
