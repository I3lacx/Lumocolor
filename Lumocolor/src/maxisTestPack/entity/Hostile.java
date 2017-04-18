package maxisTestPack.entity;

import java.awt.Graphics;

import maxisTestPack.tileMap.Map;
import maxisTestPack.tileMap.Tile;

public abstract class Hostile extends Entity {

	public Hostile(int x, int y, Map m) {
		super(x, y, m);
		setLayer(1);
	}
	
	public void attack(){
		//the Tile infront of the Entity
		if(getAnimate()) return;
		
		Tile nextTile = getMap().getTileArr()[nextX()][nextY()][1];
		
		if(nextTile != null && nextTile.getClass() == Player.class){
			setAnimation("dashAttack");
			setAnimate(true);
		}
	}
	
	public void update(){
//		attack();
		super.update();
	}

}
