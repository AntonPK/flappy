package service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import pro2_flappy.game.GameBoard;
import pro2_flappy.game.Tile;
import pro2_flappy.game.tiles.BonusTile;
import pro2_flappy.game.tiles.EmptyTile;
import pro2_flappy.game.tiles.WallTile;

public class CsvGameBoardLoader implements GameBoardLoader {
		InputStream is;
		public CsvGameBoardLoader(InputStream is){
			this.is = is;
		}
	@Override
	public GameBoard loadLevel() {		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			BufferedImage imageOfTheBird = null;
			String[] line = br.readLine().split(";");
			int typeCount = Integer.parseInt(line[0]);
			Map<String,Tile> tileTypes = new HashMap<>();
			for(int i =0; i< typeCount;i++){
				line = br.readLine().split(";");
				String tileType = line[0];
				String clazz = line[1];
				int positionX = Integer.parseInt(line[2]);
				int positionY = Integer.parseInt(line[3]);
				int width = Integer.parseInt(line[4]);
				int height = Integer.parseInt(line[5]);
				String url = line[6];
				String referencedTileType = (line.length>=8) ?line[7] : ""; // nepovinný odkaz pouzivame u bonusu
				Tile referencedTile = tileTypes.get(referencedTileType);
				if(clazz.equals("Bird")){
					 imageOfTheBird = loadImage(positionX,positionY,width,height,url);
				}else{
				Tile tile = createTile(clazz,positionX,positionY,width,height,url,referencedTile);
				tileTypes.put(tileType, tile);}
				
			}
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			// vytvorime pole dlazdic odpovidajicich rozmeru
			Tile[][] tiles = new Tile[rows][columns];
			System.out.println(rows + "," + columns);
			for(int i =0; i<rows;i++){
				line = br.readLine().split(";");
				for(int j =0;j<columns;j++){
					String cell;
					if(j<line.length){
						//bunka v csv existuje
					cell = line[j];
					}
					else
						cell = "";
					//ziskame odpovidajici typ dlazdice
						tiles[i][j] = tileTypes.get(cell);
				}
				
			}
			GameBoard gb = new GameBoard(tiles,imageOfTheBird);
			return gb;
			
		} catch (IOException e) {
			throw new RuntimeException("Chyba pøi ètení souboru",e);
			
		}
				
	}
	private Tile createTile(String clazz, int positionX, int positionY, int width, int height, String url,Tile referencedTile) {
		//stahnout obrazek z url a ulozit do promenne
		try {
			BufferedImage resizedImage = loadImage(positionX, positionY, width, height, url);
			//vytvorime odpovidajici typ dlazdice
			switch(clazz){
			case "Wall" : 	return new WallTile(resizedImage);
			case "Empty" : return new EmptyTile(resizedImage);
			case "Bonus" : return new BonusTile(resizedImage,referencedTile);
			
			}
			throw new RuntimeException("Neznamy typ dlazdice" + clazz);
		} catch (MalformedURLException e) {
			throw new RuntimeException("špatná URL pro obrázek"+ clazz+ ":" + url,e);
			
		} catch (IOException e) {
			throw new RuntimeException("chyba pøi ètení orbázku "+ clazz+ "z URL" + url,e);
		}
		
	}
	private BufferedImage loadImage(int positionX, int positionY, int width, int height, String url)
			throws IOException, MalformedURLException {
		//stahnout obrazek z url a ulozit do promenne
		BufferedImage originalImage = ImageIO.read(new URL(url));
		// vyøíznout odpovídající sprite z velkého obrázku s mnoha sprity
		BufferedImage croppedImage = originalImage.getSubimage(positionX, positionY, width, height);
		//zvetsime cropped image aby sedel na velikost dlazdice
		BufferedImage resizedImage = new BufferedImage(Tile.SIZE, Tile.SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(croppedImage, 0, 0, Tile.SIZE, Tile.SIZE, null);
		return resizedImage;
	}

}
