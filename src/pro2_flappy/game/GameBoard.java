package pro2_flappy.game;

import java.awt.Graphics;

import javax.security.auth.callback.TextInputCallback;

import pro2_flappy.game.tiles.WallTile;

public class GameBoard implements TickAware {

	Tile[][] tiles;
	int shiftX = 0;
	int viewportWidth = 200;   // TODO upravit
	Bird bird;

	public GameBoard() {

		tiles = new Tile[20][20]; // TODO vylepsit

		//tiles[0][0] = new WallTile();
		//tiles[1][1] = new WallTile();
		//tiles[3][3] = new WallTile();
		//tiles[5][5] = new WallTile();
				
		bird = new Bird(viewportWidth / 2, tiles.length * Tile.SIZE / 2);
	}
	public GameBoard ( Tile[][] tiles){
		this.tiles = tiles;
		bird = new Bird(viewportWidth / 2, tiles.length * Tile.SIZE / 2);
		
	}
	public int getHeightPix() {
		return tiles.length * Tile.SIZE;
	}
	
	public void kickTheBird() {
		bird.kick();
	}

	/**
	 * Kresli cely herni svet (zdi, bonusy, ptaka) na platno g
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {

		int minJ = shiftX / Tile.SIZE; // index prvního viditelného sloupce
		int maxJ = minJ + viewportWidth / Tile.SIZE + 2; // zajištìní zobrazení
															// všech vpravo

		for (int i = 0; i < tiles.length; i++) {
			for (int j = minJ; j < maxJ; j++) {
				
				// chceme, aby se svìt toèil dokola, takže j2 se pohybuje od 0 do poèet sloupcu pole -1
				int j2 = j %  tiles[i].length;
				
				
				Tile t = tiles[i][j2];

				if (t == null)
					continue;

				int screenX = j * Tile.SIZE - shiftX;
				int screenY = i * Tile.SIZE;

				t.draw(g, screenX, screenY);
			}
		}

		// kresli ptáka
		bird.draw(g);
		
	}

	@Override
	public void tick(long ticksSinceStart) {

		// s kazdym tikem posuneme hru o jeden pixel
		// tj. pocet ticku a pixelu posunu se rovnaji
		shiftX = (int) ticksSinceStart;

		// dame vedet jeste ptakovi, ze hodiny tickly
		bird.tick(ticksSinceStart);

	}

	

	

}
