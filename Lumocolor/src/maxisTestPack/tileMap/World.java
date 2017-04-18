package maxisTestPack.tileMap;

import maxisTestPack.GameField;

public class World implements GameField{

	/**
	 * maps[Width][Height]
	 * Array das aus mehreren maps 
	 * besteht
	 */
	private Map[][] maps;	
	
	public World(){
		newMaps();
		
	}
	
	/**
	 * setzt alle maps in der Welt
	 * auf eine neue Map
	 */
	private void newMaps(){
		for(int i= 0; i < WORLD_HEIGHT; i++) 
			for(int j=0; j< WORLD_WIDTH; j++)
				this.maps[i][j] = new RandomMap();
	}
	
	/**
	 * nichts sicher ob das geht
	 * @return
	 */
	public String[][][][][] save(){
		String[][][][][] out = new String[WORLD_WIDTH][WORLD_HEIGHT][][][];
		
		for(int i= 0; i < WORLD_HEIGHT; i++) 
			for(int j=0; j< WORLD_WIDTH; j++)
					out[i][j] = maps[i][j].save();
		
		return out;
	}
	
	public Map[][] getMaps(){
		return this.maps;
	}
	
	public void setMaps(Map[][] maps){
		this.maps = maps;
	}
}
