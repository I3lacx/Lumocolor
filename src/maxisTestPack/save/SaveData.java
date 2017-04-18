package maxisTestPack.save;

import maxisTestPack.entity.Grumpl;
import maxisTestPack.entity.Player;
import maxisTestPack.inventory.AxeItem;
import maxisTestPack.inventory.Inventory;
import maxisTestPack.inventory.RockItem;
import maxisTestPack.inventory.SwordItem;
import maxisTestPack.inventory.WoodItem;
import maxisTestPack.tileMap.AxeTile;
import maxisTestPack.tileMap.BushTile;
import maxisTestPack.tileMap.GrassTile;
import maxisTestPack.tileMap.Map;
import maxisTestPack.tileMap.MapEmpty;
import maxisTestPack.tileMap.RockTile;
import maxisTestPack.tileMap.Tile;
import maxisTestPack.GameField;

public class SaveData implements java.io.Serializable, GameField{

	//because java
	private static final long serialVersionUID = 1L;
	
	private Player player;

	private int posX;
	private int posY;
	
	private int worldX;
	private int worldY;
	
	private String[][] Inv;
	private int[][] slotCount = new int[8][8];
	private String[][][] tileArr = new String[GRID_WIDTH][GRID_HEIGHT][LAYERS];
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * loads the map and retuns a Map
	 * with each class implemented in there
	 * only for primitive classes, not for 
	 * player etc.
	 * @param load
	 * @return
	 */
	public static Map loadMap(SaveData load){
		String name;
		Map map = new MapEmpty();
		
		for (int k = 0; k < map.getTileArr()[0][0].length; k++) {
			for (int i = 0; i < map.getTileArr().length; i++) {
				for (int j = 0; j < map.getTileArr()[i].length; j++) {
					name = load.tileArr[i][j][k];
					switch(name) {
					case "GrassTile":
						map.getTileArr()[i][j][k] = new GrassTile();
						break;
					case "BushTile":
						map.getTileArr()[i][j][k] = new BushTile();
						break;
					case "RockTile":
						map.getTileArr()[i][j][k] = new RockTile();
						break;
					case "AxeTile":
						map.getTileArr()[i][j][k] = new AxeTile();
						break;
					case "Grumpl":
						map.getTileArr()[i][j][k] = new Grumpl(i,j,map);
						break;
					case "":
						map.getTileArr()[i][j][k] = null;
						break;
					case "Player":
						//does nothing for now
						break;
					default:
						System.err.println("Name not found Class: " + name + " not implemented");
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * loads the Inv from a String array into 
	 * a inv form
	 */
	public static Inventory loadInv(SaveData load) {
		String name;
		Inventory out = new Inventory();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(load.Inv[i][j] == "")
					out.getInv()[i][j] = null;
				else {
					name = load.Inv[i][j];
					switch(name){
					case "Rock":
						out.getInv()[i][j] = new RockItem();
						break;
					case "Sword":
						out.getInv()[i][j] = new SwordItem();
						break;
					case "Wood":
						out.getInv()[i][j] = new WoodItem();
						break;
					case "Axe":
						out.getInv()[i][j] = new AxeItem();
						break;
					}
				}
			}
		}

		return out;
	}
	
	/**
	 * laods the HUD
	 */
	public static void loadHUD(){
		
	}
	
	
	public String[][][] getTileArr(){
		return tileArr;
	}
	
	public void setTileArr(String[][][] map){
		this.tileArr = map;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public String[][] getInv() {
		return Inv;
	}

	public void setInv(String[][] inv) {
		Inv = inv;
	}

	public int[][] getSlotCount() {
		return slotCount;
	}

	public void setSlotCount(int[][] slotCount) {
		this.slotCount = slotCount;
	}

	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}
	
	
}
