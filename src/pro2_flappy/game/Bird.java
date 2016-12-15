package pro2_flappy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

public class Bird implements TickAware {

	// fyzika
	static final double koefUp = -5.0;
	static final double koefDown = 2.0;
	static final int ticksFlyingUp = 4;

	// souradnice stredu ptaka
	double viewportX;
	double viewportY;
    Image image; //obrazek ptaka
	// rychlost padani (pozitivni), nebo vzledu (negativni)
	double velocityY = koefDown;
	// kolik tiku jeste zbyva, neez ptak zacne padat po nakopnuti
	int ticksToFall = 0;
	
	public Bird(int initalX, int initialY,Image image) {
		this.viewportX = initalX;
		this.viewportY = initialY;
		this.image = image;
	}

	public void kick() {
		velocityY = koefUp;
		ticksToFall = ticksFlyingUp;
	}

	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		//g.fillOval((int) viewportX - Tile.SIZE / 2, (int) viewportY - Tile.SIZE / 2, Tile.SIZE, Tile.SIZE);
		g.drawImage(image,(int) viewportX - Tile.SIZE / 2, (int) viewportY - Tile.SIZE / 2, null);

		// vykresleni souradnic pro debugovani
		g.setColor(Color.BLACK);
		g.drawString(viewportX + "," + viewportY, (int) viewportX, (int) viewportY);
	}
	
	public boolean collidesWithRectangle(int x, int y, int w, int h ){
		//vytvorime kruznici reprezentujici obrys ptaka
		Ellipse2D.Float birdsBoundary = new Ellipse2D.Float((int) viewportX - Tile.SIZE / 2, 
															(int) viewportY - Tile.SIZE / 2,
															Tile.SIZE, Tile.SIZE);
		//overime zda kruznice ma neprazdny prunik													
		return birdsBoundary.intersects(x, y, w, h);
		
		
	}	
	
	@Override
	public void tick(long ticksSinceStart) {
		// reseni rychlosti a ticku do padu

		if (ticksToFall > 0) {
			ticksToFall--;

		} else {

			velocityY = koefDown;

		}
		viewportY += velocityY;
	}
	
	
	
	

}








