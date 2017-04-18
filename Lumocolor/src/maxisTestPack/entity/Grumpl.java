package maxisTestPack.entity;

import maxisTestPack.tileMap.Map;

/**
 * A monster with random movement
 * @author Maxi
 *
 */
public class Grumpl extends Hostile {

	//random number
	private int randomNumber;

	public Grumpl(int x, int y, Map map) {
		super(x, y, map);
		setPath("/GrumplSheet.png");
		setEntityHeight(32);
		setEntityWidth(32);
		setExtraY(0);
		setMovementSpeed(1);
		setImage(0,1);
		setHealth(1);
	}
	
	/**
	 * calculates random Movement to move in direction l, u, d or r
	 * should be part of entity
	 */
	public void randomMovement() {
		randomNumber = (int) (Math.random()*50);
		
		switch(randomNumber){
		case 0:
			turn('l');
			break;
		case 1:
			turn('r');
			break;
		case 2:
			turn('u');
			break;
		case 3:
			turn('d');
			break;
		default:
			setMoving(false);
			break;
		}
	}
	
	public void update(){
		randomMovement();
		super.update();
		
	}
}
