package pro2_flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import pro2_flappy.game.Tile;

public class BonusTile extends AbstractTile {
	boolean isActive = true;
	
	Tile emptyTile;
	public BonusTile(Image image, Tile emptyTile) {
		super(image);
		this.emptyTile = emptyTile;
	}
	@Override
	public void draw(Graphics g, int x, int y) {
		if(isActive){
		g.drawImage(image, x, y, null);
		}else{
			emptyTile.draw(g, x, y);
		}
		//g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
	}
	public void setIsActive(boolean value){
		isActive = value;
	}
}
