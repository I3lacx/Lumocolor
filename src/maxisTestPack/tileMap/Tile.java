package maxisTestPack.tileMap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import maxisTestPack.GameField;
import maxisTestPack.Main;

public abstract class Tile implements GameField {

	private BufferedImage image;
//	private int posX;
//	private int posY;
//	private int layer;
	private boolean passable;

	/**
	 * 0 = Grass 1 = Rock
	 * 
	 * @param type
	 */

	public Tile() {
//		setPosX(x);
//		setPosY(y);
	}
	
//	public int getLayer() {
//		return layer;
//	}
//
//	public void setLayer(int layer) {
//		this.layer = layer;
//	}

	public BufferedImage getImage() {
		return image;
	}
	
//	public int getPosY() {
//		return posY;
//	}
//
//	public void setPosY(int posY) {
//		this.posY = posY;
//	}
//
//	public int getPosX() {
//		return posX;
//	}
//
//	public void setPosX(int posX) {
//		this.posX = posX;
//	}
	public void setImage(String path) {
		try {
			image = ImageIO.read(Main.class.getResourceAsStream(path));
			
		} catch (IOException e) {
			System.out.println("Coudn't find file with path: " + path);
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, int posX, int posY, int drawX, int drawY){
		if(drawX < 0)
		g.drawImage(
				getImage(), (posX+1) * TILE_SIZE + drawX,
				posY * TILE_SIZE, null);
		if(drawX > 0)
			g.drawImage(
					getImage(), (posX-1) * TILE_SIZE + drawX,
					posY * TILE_SIZE, null);
		if(drawY < 0)
			g.drawImage(
					getImage(), posX * TILE_SIZE,
					drawY + (posY+1) * TILE_SIZE, null);
		else
			g.drawImage(
					getImage(), posX * TILE_SIZE + drawX,
					drawY + (posY-1) * TILE_SIZE, null);
	}
	
	/**
	 * method returns String in
	 * which the data will be saved
	 * and loaded
	 * Will be overritten by higher lvl
	 * classes with more complexity
	 * simple classes will be saved
	 * with their class name
	 * @return
	 */
	public String toString(){
		//e.g. maxisTestPack.Entity.Player -> Player
		String className = this.getClass().getName();
		int dotIndex = className.lastIndexOf('.');
		return className.substring(dotIndex+1);
	}
	
	/**
	 * needs to be implemented
	 * does nothing for now
	 * will be overwritten by entity class
	 */
	public void update(){
		
	}

	public boolean getPassable() {
		return passable;
	}
	
	public void setPassable(boolean p) {
		passable = p;
	}


}
