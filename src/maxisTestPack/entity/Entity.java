package maxisTestPack.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import maxisTestPack.GameField;
import maxisTestPack.GamePanel;
import maxisTestPack.tileMap.Map;
import maxisTestPack.tileMap.Tile;

public abstract class Entity extends Tile implements GameField {

//	attributes
	private BufferedImage image;
	private BufferedImage entitySheet;

	private String path;

	private int extraY;

	private Map map;

	private int entityHeight;
	private int entityWidth;

	private int movementSpeed = 1;

	private int drawPos = 0;
	private int animateStatus = 0;
	private int animationFrame;
	
	//to calculate the actual display pos of player
	private int drawX = 0;
	private int drawY = 0;
	
	//modifier for animation(position)
	private int modifierX;
	private int modifierY;
	
	private char facing = 'd';
	
	private boolean moving;
	private boolean animate = false;
	//becomes true during attack animation
	private boolean attacked = false;
	
	private int posX;
	private int posY;
	private int layer;
	
	private String animation;
	private int hitProcess;
	
	private int maxHealth;
	private int health;
	private int hitSpeed = 5;
	private int attackDamage = 1;
	
	boolean dead = false;

	/**
	 * constructor
	 * @param l 
	 * @param x
	 * @param y
	 * @param m
	 */
	public Entity(int x, int y, Map m){
		posX = x;
		posY = y;
		this.map = m;
		setPassable(false);
//		map.add(this, posX, posY, layer);
	}
	
	/**
	 * 
	 * @param direction
	 */
	public void turn(char direction) {

		if (animate)
			return;
		
		if (direction == facing) {
			if(canMove()) {
				moving = true;
				animation = "walk";
//				animate = true;
			}
		} else {
			facing = direction;
		}
		
		updateImage();
	}
	

	/**
	 * calculates nextX
	 * if over border then old Y
	 * @param x
	 * @return
	 */
	public int nextX(){
		int out;
		switch (facing) {
		case 'l':
			out = posX - 1; break;
		case 'r':
			out = posX + 1; break;
		default:
			return posX;
		}
		
		if (out < 0) return 0;
		if (out >= GameField.GRID_WIDTH) return GameField.GRID_WIDTH;
		
		return out;
	}
	
	/**
	 * calculates nextY
	 * if over border then old Y
	 * @param y
	 * @return
	 */
	public int nextY(){

		int out;
		switch (facing) {
		case 'u':
			out = posY - 1; break;
		case 'd':
			out = posY + 1; break;
		default:
			return posY;
		}
		
		if (out < 0) return 0;
		if (out >= GameField.GRID_WIDTH) return GameField.GRID_WIDTH;
		
		return out;
	}
	
	/**
	 * calculates nextX
	 * @param x
	 * @return
	 */
	public int lastX(int x){
		switch (facing) {
		case 'l':
			return x + 1;
		case 'r':
			return x - 1;
		default:
			return x;
		}
	}
	
	/**
	 * calculates nextY
	 * @param y
	 * @return
	 */
	public int lastY(int y){
		
		switch (facing) {
		case 'u':
			return y + 1;
		case 'd':
			return y - 1;
		default:
			return y;
		}
	}

	/**
	 * 
	 * updates the entity
	 * 
	 */
	public void update() {

		if (!moving || !canMove() || animate) {
			return;
		}

		map.setNewPos(posX, posY, nextX(), nextY(), layer);
		
		posX = nextX();
		posY = nextY();
		
		animate = true;
		moving = false;
	}

	/**
	 * 
	 */
	public void updateImage() {
		switch (facing) {
		case 'u':
			setImage(0, 2);
			break;
		case 'd':
			setImage(0, 1);
			break;
		case 'l':
			setImage(0, 0);
			break;
		case 'r':
			setImage(0, 3);
			break;
		default:
			System.err.println("Wrong Direction");
			break;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean canMove() {

//		if (nextX() == posX || nextX() >= GRID_WIDTH || nextY() == posY || nextY() >= GRID_HEIGHT) {
		if (nextX() == posX && nextY() == posY) {	
			moving = false;
			System.out.println("Can't move: Out of world");
			return false;
		}

		Tile nextTile = map.getTileArr()[nextX()][nextY()][1];
		if (nextTile != null && nextTile.getPassable() == false) {
			moving = false;
			System.out.println("Can't move: Object in the way");
			return false;
		}

		return true;
	}
	
	public void hit() {
		if (animate) return;
		map.getTileArr()[nextX()][nextY()][1] = null;
		animate = true;
		animation = "hitAttack";
		
	}
	
	public void die() {
//		Player player = (Player) getMap().getPlayer();
//		int x = player.getPosX();
//		int y = player.getPosY();
		getMap().getTileArr()[posX][posY][layer] = null;
		dead = true;
		
	}
	
	
	/**
	 * animates hit
	 */
	public void animateHit(Graphics g, int x, int y) {
		if (hitProcess > 100) {
			animate = false;
			updateImage();
			g.drawImage(image, x * TILE_SIZE, extraY + y * TILE_SIZE, null);
			hitProcess = 0;
			return;
		}
		
		animationFrame = 0;
		if (hitProcess <= 25) animationFrame = 5;
		else if (hitProcess <= 50) animationFrame = 0;
		else if (hitProcess <= 75) animationFrame = 5;
		else if (hitProcess <= 100) animationFrame = 0;

		setImage(animationFrame, 0);
		g.drawImage(image, x * TILE_SIZE, extraY + y * TILE_SIZE, null);
		hitProcess += hitSpeed;
	}


	/**
	 * animates the walking animation
	 * wird in draw() aufgerufen
	 * @param g
	 */
	public void animateWalk(Graphics g, int x, int y) {

		if (drawPos >= TILE_SIZE) {
			animate = false;
			updateImage();
			g.drawImage(image, x * TILE_SIZE, extraY + y * TILE_SIZE, null);
			drawPos = 0;
			setDrawX(0);
			setDrawY(0);
			return;
		}

		animationFrame = 0;
		if (drawPos <= 12 && drawPos >= 3) animationFrame = 1;
		else if (drawPos <= 18) animationFrame = 2;
		else if (drawPos <= 27)	animationFrame = 3;
		else if (drawPos <= 30)	animationFrame = 4;

		
		switch (facing) {
		case 'u':
			setImage(animationFrame, 2);
			setDrawY(getDrawY() - movementSpeed);
			g.drawImage(image, lastX(x) * TILE_SIZE, extraY + lastY(y) * TILE_SIZE - drawPos, null);
			break;
		case 'd':
			setImage(animationFrame, 1);
			setDrawY(getDrawY() + movementSpeed);
			g.drawImage(image, lastX(x) * TILE_SIZE, extraY + lastY(y) * TILE_SIZE + drawPos, null);
			break;
		case 'l':
			setImage(animationFrame, 0);
			setDrawX(getDrawX() - movementSpeed);
			g.drawImage(image, lastX(x) * TILE_SIZE - drawPos, extraY + lastY(y) * TILE_SIZE, null);
			break;
		case 'r':
			setImage(animationFrame, 3);
			setDrawX(getDrawX() + movementSpeed);
			g.drawImage(image, lastX(x) * TILE_SIZE + drawPos, extraY + lastY(y) * TILE_SIZE, null);
			break;
		}

		drawPos += movementSpeed;
	}
	
	/**
	 * performs a basic Animation without 
	 * movement
	 */
	public void basicAnimation(){
		// TODO
	}
	
//	public void attack() {
//		//the Tile infront of the Entity
//		if(getAnimate()) return;
//		
//		
//		
//		Tile target = getMap().getTileArr()[nextX()][nextY()][1];
//		
//		if(target != null && target.getClass() == Grumpl.class){
//			System.out.println("hi");
//			((Entity) target).damage(getTotalDamage());
//			setAnimation("dashAttack");
//			setAnimate(true);
//		}
//	}

//	private int getTotalDamage() {
//		if(this.getClass() == Player.class) {
//			if
//		}
//		return 0;
//	}

	/**
	 * A simple dash animation with moving the 
	 * character to the side a bit,
	 * without changing the sprites
	 */
	public void animateDashAttack(Graphics g, int x, int y){
		int modifier = 0;
		
		if (animateStatus >= 200) {
			animate = false;
			updateImage();
			g.drawImage(image, x * TILE_SIZE, extraY + y * TILE_SIZE, null);
			animateStatus = 0;
			attacked = false;
			return;
		}

		animationFrame = 0;
		modifier = 0;
		if (animateStatus < 60) modifier = 10;
		else if (animateStatus < 100) modifier = 20;
		else if (animateStatus < 150) modifier = 10;

		switch (facing) {
		case 'u':
			modifierY = -modifier;
			break;
		case 'd':
			modifierY = modifier;
			break;
		case 'l':
			modifierX = -modifier;
			break;
		case 'r':
			modifierX = modifier;
			break;
		}
		
		if(!attacked && animateStatus >= 70 && isPlayerFront()) {
//			damagePlayer(attackDamage);
			attacked = true;
		}
		
		
		animateStatus += hitSpeed;
		
		g.drawImage(image, x * TILE_SIZE + modifierX,
				extraY + y * TILE_SIZE + modifierY, null);
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void setImage(int x, int y) {
		try {
			entitySheet = ImageIO.read(GamePanel.class.getResourceAsStream(path));

		} catch (Exception e) {
			System.out.println("Coudn't find file with path: " + path);
			e.printStackTrace();
		}
		
		image = entitySheet.getSubimage(x * entityWidth, y * entityHeight, entityWidth, entityHeight);
	}

	/**
	 * 
	 * @param g
	 */
	public void draw(Graphics g, int x, int y) {
		
//		switch(animation) {
		
		if (animate) {
			switch (animation) {
			case "hitAttack" :
				animateHit(g, x, y); break;
			case "walk" :
				animateWalk(g, x, y); break;
			case "dashAttack" :
				animateDashAttack(g, x, y); break;
			}
		} else {
			updateImage();
			g.drawImage(image, x * TILE_SIZE, extraY + y * TILE_SIZE, null);
			drawPos = 0;
			moving = false;
		}
	}
	
	public boolean isPlayerFront(){
		Tile nextTile = map.getTileArr()[nextX()][nextY()][2];
	
		if(nextTile != null && nextTile.getClass() == Player.class)
			return true;
			
		return false;
	}
	
	/**
	 * Damages player with damage as int
	 * could depend on his armor and stuff
	 * so it don't transfer to damage instant
	 * @param damage
	 */
	public void damage(int damage){
		
		health= health - damage;
		if (health <= 0) die();
//		if(player.getClass() == Player.class){
//			((Player) player).damage(damage);
//		}
//		else{
//			System.err.println("This is not a Player!");
//		}
	}
/**
 * 
 * GET AND SET METHODES
 * @return 
 * 
 * @return
 */
	
////// get and set of position and layer
	public void setLayer(int l) {
		layer = l;
	}
	
	public void setPosX(int x) {
		posX = x;
	}
	
	public void setPosY(int y) {
		posY = y;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
///////	
	
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setExtraY(int extraY) {
		this.extraY = extraY;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public char getFacing() {
		return facing;
	}

	public void setFacing(char facing) {
		this.facing = facing;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setEntityHeight(int height) {
		entityHeight = height;
	}

	public void setEntityWidth(int width) {
		entityWidth = width;
	}
	
	public void setAnimation(String d) {
		this.animation = d;
	}
	
	public void setAnimate(boolean b){
		this.animate = b;
	}
	public boolean getAnimate(){
		return animate;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public int getHealth(){
		return this.health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
//	end of get/set-methods

	public int getDrawX() {
		return drawX;
	}

	public void setDrawX(int drawX) {
		this.drawX = drawX;
	}

	public int getDrawY() {
		return drawY;
	}

	public void setDrawY(int drawY) {
		this.drawY = drawY;
	}
}
