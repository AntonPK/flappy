package service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import pro2_flappy.game.GameBoard;
import pro2_flappy.game.Tile;
import pro2_flappy.game.tiles.WallTile;

public class CsvGameBoardLoader implements GameBoardLoader {
		InputStream is;
		public CsvGameBoardLoader(InputStream is){
			this.is = is;
		}
	@Override
	public GameBoard loadLevel() {		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			
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
				Tile tile = createTile(clazz,positionX,positionY,width,height);
				tileTypes.put(tileType, tile);
				
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
			GameBoard gb = new GameBoard(tiles);
			return gb;
			
		} catch (IOException e) {
			throw new RuntimeException("Chyba p�i �ten� souboru",e);
			
		}
				
	}
	private Tile createTile(String clazz, int positionX, int positionY, int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

}
