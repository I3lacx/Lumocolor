package maxisTestPack.tileMap;

/**
 * Bush can be destroyed when
 * using an axe, else not pass able;
 * @author Maximilian Otte
 *
 */
public class BushTile extends Tile{
	
	public BushTile(){
//		super((int) Math.floor(Math.random() * GRID_WIDTH),(int) Math.floor(Math.random() * GRID_HEIGHT));
//		setLayer(1);
		setImage("/Bush.png");
		setPassable(false);
	}
	
//	public BushTile(int x, int y){
////		super(x,y);
//		setImage("/Bush.png");
////		setLayer(1);
//	}

}
