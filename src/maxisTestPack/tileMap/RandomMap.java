package maxisTestPack.tileMap;

public class RandomMap extends Map{
	
	public RandomMap(){
	//TODO generates random Map
	fillwithGrassTile();
	
	for (int i = 0; i < 5; i++) {
		add(new RockTile(), 1, random(GRID_WIDTH), random(GRID_HEIGHT));
	}

	for (int i = 0; i < 5; i++) {
		add(new BushTile(), 1, random(GRID_WIDTH) , random(GRID_HEIGHT));
	}
	
	}
}
