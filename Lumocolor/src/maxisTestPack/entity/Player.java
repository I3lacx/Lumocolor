package maxisTestPack.entity;

import maxisTestPack.GameField;
import maxisTestPack.headUpDisplay.HUD;
import maxisTestPack.inventory.AxeItem;
import maxisTestPack.inventory.Inventory;
import maxisTestPack.inventory.RockItem;
import maxisTestPack.inventory.WoodItem;
import maxisTestPack.save.ResourceManager;
import maxisTestPack.save.SaveData;
import maxisTestPack.tileMap.BushTile;
import maxisTestPack.tileMap.Map;
import maxisTestPack.tileMap.RockTile;
import maxisTestPack.tileMap.Tile;
import maxisTestPack.tileMap.AxeTile;

public class Player extends Entity {
	
	private Inventory inv = new Inventory();
	private boolean dead = false;
	private HUD hud;
	//private Map[][] world;
	private int worldX;
	private int worldY;
	
	public Player(int x, int y, Map map) {
		super(x, y, map);
		setPath("/playerSheet.png");
		setEntityHeight(50);
		setEntityWidth(32);
		setExtraY(-22);
		setMovementSpeed(3);
		setImage(0,1);
		setLayer(1);
		setHealth(100);
		setMaxHealth(100);
		setHud(new HUD(this));
/*		
		//temporary:
		setWorldX(5);
		setWorldY(5);
		//sets the lokal map to the new Position
		refreshMap();
		
		*/
		}
	
	/**
	 * Decides which action to do.
	 */
	public void action() {
		int[] nextXY = getNextXY();
		if(!inArea(nextXY))
			return;
		
		Tile wimf = getMap().getTileArr()[nextX()][nextY()][1];
		if(getMap().getTileArr()[nextXY[0]][nextXY[1]][1] == null) {
			setItem(nextXY[0], nextXY[1]);
		} else if(wimf.getClass() == RockTile.class) {
			smashRock();
		} else if(wimf.getClass() == BushTile.class) {
			cutTree();
		} else if(wimf.getClass() == AxeTile.class) {
			getAxe();
		} else if(wimf.getClass() == Grumpl.class) {
			attack();
		}
	}
	
	/**
	 * checks if given coordinates are inside the World boundry
	 * 
	 * @param xy
	 * @return
	 */
	public boolean inArea(int[] xy){
		if(xy[0] < 0 || xy[0] >= GameField.GRID_WIDTH || xy[1] < 0 || xy[1] >= GameField.GRID_HEIGHT)
			return false;
		return true;
	}
	
	public void attack() {
		if(getAnimate()) return;
		Tile target = getMap().getTileArr()[nextX()][nextY()][1];
		if (target != null) {
			
		}
	}
	
	/**
	 * sets the Block in front of the player to
	 * the Item in his slot if its a building block
	 * @param x x coordinate
	 * @param y	y coordinate
	 */
	public void setItem(int x, int y) {
		if(inv.getItem() == null) {
			return;
		}
		
		if(inv.getItem().getClass() == RockItem.class){
			getMap().add(new RockTile(), 1, x, y);
			inv.removeItem(new RockItem());
		}
		else if(inv.getItem().getClass() == WoodItem.class){
			getMap().add(new BushTile(), 1, x, y);
			inv.removeItem(new WoodItem());
		}
	}
	
	/**
	 * 
	 * @return position of the tile in the players front
	 */
	public int[] getNextXY() {
		int[] nextXY = new int[2];
		nextXY[0] = getPosX();
		nextXY[1] = getPosY();
		switch (getFacing()) {
		case 'u':
			nextXY[1]--;
			break;
		case 'd':
			nextXY[1]++;
			break;
		case 'l':
			nextXY[0]--;
			break;
		case 'r':
			nextXY[0]++;
			break;
		default:
			System.err.println("WRONG FACING DIRECTION");
		}
		return nextXY;
	}

	/**
	 * Actually you dont smash the rock but you take it.
	 */
	public void smashRock() {
		int[] nextXY = getNextXY();
		getMap().getTileArr()[nextXY[0]][nextXY[1]][1] = null;
		inv.addItem(new RockItem());

	}
	
	public void save(){
		System.out.println("SYSTEM SAVE");
		SaveData save = new SaveData();
		save.setPosX(getPosX());
		save.setPosY(getPosY());
		save.setWorldX(getWorldX());
		save.setWorldY(getWorldY());
		save.setInv(getInv().saveInv());
		save.setTileArr(getMap().save());
		save.setSlotCount(inv.getSlotCount());
		//saves Pos of player and inv of player

		ResourceManager.save(save, "Save/player.save");
		
	}
	
	public void load(){
		System.out.println("SYSTEM LOAD");
		SaveData load = (SaveData) ResourceManager.load("Save/player.save");

		Map newMap = SaveData.loadMap(load);
		inv = SaveData.loadInv(load);
		inv.setSlotCount(load.getSlotCount());
		
		setPosX(load.getPosX());
		setPosY(load.getPosY());
		setWorldX(load.getWorldX());
		setWorldY(load.getWorldY());
		
		setMap(newMap);
		setHud(new HUD(this));
		getMap().add(this);
		
	}
	
//	public void damage(int damage){
//		this.setHealth(getHealth() - damage);
//
//		if(getHealth() <= 0){
//			die();
//		}
//	}
	
//	public void die() {
//		Player player = (Player) getMap().getPlayer();
//		int x = player.getPosX();
//		int y = player.getPosY();
//		getMap().getTileArr()[x][y][2] = null;
//		dead = true;
//		
//	}
	
	public void cutTree() {
		if(!inv.itemEquipped(new AxeItem())) {
			System.out.println("No Axe");
			return;
		}
		
		int[] nextXY = getNextXY();
		getMap().getTileArr()[nextXY[0]][nextXY[1]][1] = null;
		inv.addItem(new WoodItem());
	}
	
	public void getAxe(){
		int[] nextXY = getNextXY();
		getMap().getTileArr()[nextXY[0]][nextXY[1]][1] = null;
		inv.addItem(new AxeItem());
	}
	/*
	public void refreshMap(){
		super.setMap(world[getWorldX()][getWorldY()]);
	}
	*/
	
	public void suprised(){
		setImage(5,0);
	}

	public void headDown(){
		setImage(5,1);
	}

	public Inventory getInv() {
		return inv;
	}
	public boolean getDead(){
		return dead;
	}

	public HUD getHud() {
		return hud;
	}

	public void setHud(HUD hud) {
		this.hud = hud;
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