package maxisTestPack.tileMap;

import maxisTestPack.entity.Grumpl;
import maxisTestPack.entity.Player;

/**
 * first map extends map
 * @author Maxi
 *
 */
public class Map1 extends Map{

	public Map1() {
		fillwithGrassTile();
		
		for (int i = 0; i < 5; i++) {
			add(new RockTile(), 1, random(GRID_WIDTH), random(GRID_HEIGHT));
		}

		for (int i = 0; i < 5; i++) {
			add(new BushTile(), 1, random(GRID_WIDTH) , random(GRID_HEIGHT));
		}
		
//		for(int i = 0;i < 2; i++) {
		//	add(new Grumpl(random(GRID_WIDTH), random(GRID_HEIGHT), this));
			add(new Grumpl(1, 1, this));
//		}
		
		add(new AxeTile(), 1, 2, 2);
	}
	
}
