package maxisTestPack.tileMap;

/**
 * This is a rock, you can't
 * pass and on the map 
 * its found with ID = 1;
 * @author Maximilian Otte
 *
 */
public class RockTile extends Tile{
	
	public RockTile(){
//		super((int) Math.floor(Math.random() * GRID_WIDTH), (int) Math.floor(Math.random() * GRID_HEIGHT) );
		setImage("/rock.png");
		setPassable(false);
//		setLayer(1);
	}
	
//	public RockTile(int x, int y){
////		super(x,y);
//		setImage("/rock.png");
////		setLayer(1);
//	}

}
