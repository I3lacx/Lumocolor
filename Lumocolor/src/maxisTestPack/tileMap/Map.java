package maxisTestPack.tileMap;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

import maxisTestPack.GameField;
import maxisTestPack.entity.Entity;
import maxisTestPack.entity.Player;

/**
 * Maps have an image, as texture and a int array with 2 dimensions to save for
 * each coordinate something
 * 
 * @author Maximilian Otte
 *
 */
@SuppressWarnings("serial")
public abstract class Map extends JPanel implements GameField,Serializable {

	private Tile[][][] tileArr = new Tile[GRID_WIDTH][GRID_HEIGHT][LAYERS];

	/**
	 * iterates through everything, starts with layer 0
	 * and calls the draw funciton on every element
	 * @param g
	 */
	public void draw(Graphics g, int range, int posX, int posY, int drawX, int drawY) {

		for (int k = 0; k < LAYERS; k++) {
			for (int i = posX - range, i2 = 0; i < posX + range; i++, i2++) {
				for (int j = posY - range, j2 = 0; j < posY + range; j++, j2++) {
					
					//draw out of boundry
					if(i < 0 || j < 0 || i >= GRID_WIDTH || j >= GRID_HEIGHT) {
						drawBackg(g,i2,j2, drawX, drawY);
					} else if (tileArr[i][j][k] != null) {
						// draws everything else
						tileArr[i][j][k].draw(g,i2,j2, drawX, drawY);
					}
				}
			}
		}
	}

	/**
	 * calls the update function everywhere
	 * could be used better
	 */
	public void update() {
		for (int k = 1; k < LAYERS; k++) {
			for (int i = 0; i < tileArr.length; i++) {
				for (int j = 0; j < tileArr[i].length; j++) {
					if (tileArr[i][j][k] != null) {
						// draws everything
						tileArr[i][j][k].update();
					}
				}
			}
		}
	}

	/**
	 * Sets new Positions for the given coord
	 */
	public void setNewPos(int oldX, int oldY, int newX, int newY, int layer) {

		if (oldX == newX && oldY == newY)
			return;

		Tile save = tileArr[oldX][oldY][layer];
		tileArr[newX][newY][layer] = save;
		tileArr[oldX][oldY][layer] = null;
	}

	/**
	 * this method saves 
	 */
	public String[][][] save(){
		String[][][] out = new String[GRID_WIDTH][GRID_WIDTH][LAYERS];
		
		for (int k = 0; k < LAYERS; k++) {
			for (int i = 0; i < tileArr.length; i++) {
				for (int j = 0; j < tileArr[i].length; j++) {
					if(tileArr[i][j][k] != null)
						out[i][j][k] = tileArr[i][j][k].toString();
					else 
						out[i][j][k] = "";
				}
			}
		}
		
		return out;
	}
	
	/**
	 * loads everything from String Array to map.
	 * 
	 * NOT WORKING RIGHT NOW
	 */
	public String[][][] load(){
		String[][][] out = new String[GRID_WIDTH][GRID_WIDTH][LAYERS];
		
		for (int k = 1; k < LAYERS; k++) {
			for (int i = 0; i < tileArr.length; i++) {
				for (int j = 0; j < tileArr[i].length; j++) {
					out[i][j][k] = tileArr[i][j][k].toString();
					System.out.println("call");
				}
			}
		}
		
		return out;
	}
	
	/**
	 * add an Tile given Layer
	 * 
	 * @param tile
	 */
	public void add(Tile tile, int layer, int posX, int posY) {
		tileArr[posX][posY][layer] = tile;
	}
	
	/**
	 * draws the areas where no tiles exist
	 */
	public void drawBackg(Graphics g, int x, int y, int drawX, int drawY){
		g.setColor(Color.BLACK);
		if(drawX < 0)
			g.fillRect((x+1) * TILE_SIZE + drawX, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			if(drawX > 0)
				g.fillRect((x-1) * TILE_SIZE + drawX, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			if(drawY < 0)
				g.fillRect(x * TILE_SIZE, (y+1) * TILE_SIZE + drawY, TILE_SIZE, TILE_SIZE);
			else
				g.fillRect(x * TILE_SIZE, (y-1) * TILE_SIZE + drawY, TILE_SIZE, TILE_SIZE);
	}

	/**
	 * calculates a random number between 0 and border
	 * 
	 * @param border
	 * @return
	 */
	public int random(int border) {
		return (int) Math.floor(Math.random() * border);
	}

	public void add(Entity entity) {
		tileArr[entity.getPosX()][entity.getPosY()][entity.getLayer()] = entity;
	}

	public Tile getPlayer() {
		for (int i = 0; i < tileArr.length; i++) {
			for (int j = 0; j < tileArr[i].length; j++) {
				if (tileArr[i][j][2] != null && tileArr[i][j][1].getClass() == Player.class) {
					return tileArr[i][j][2];
				}
			}
		}
		System.err.println("Error player not on map :(");
		return null;
	}
	
	/**
	 * fills layer 0 with Grass Tiles
	 */
	public void fillwithGrassTile(){
		for (int i = 0; i < getTileArr().length; i++) {
			for (int j = 0; j < getTileArr()[i].length; j++) {
				add(new GrassTile(), 0, i, j);
			}
		}
	}

	
	/**
	 * add an Tile to Layer 0
	 * 
	 * @param tile
	 */

	public Tile[][][] getTileArr() {
		return tileArr;
	}

}
