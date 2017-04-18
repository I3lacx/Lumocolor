/**
 * 
 */
package joschkasTestPack;

/**
 * @author Joschido
 *
 */
public class TileMap {
	
	public static int MAPWIDTH = 16;
	public static int MAPHEIGHT = 16;
	
	Tile[][] map = new Tile[MAPWIDTH][MAPHEIGHT];
	
	public TileMap() {
		for(int i=0; i<MAPHEIGHT; i++) {
			for(int j=0; j<MAPWIDTH; j++) {
				map[i][j] = new Grass();
			}
		}
		
		map[1][1] = new Rock();
		
	}
	
	public Tile getTile(int x, int y) {
		return map[x][y];
		
	}

}
