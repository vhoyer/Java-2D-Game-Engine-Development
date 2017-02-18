package io.github.vhoyer.game.level;

import io.github.vhoyer.game.gfx.Screen;
import io.github.vhoyer.game.level.tiles.Tile;

public class Level {

	private byte[] tiles;
	public int width;
	public int height;

	public Level(int width, int height){
		tiles = new byte[width * height];
		this.width = width;
		this.height = height;
		this.generateLevel();
	}

	public void generateLevel(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(x*y % 10 < 5){
					tiles[x + y * width] = Tile.GRASS.getID();
				}
				else{
					tiles[x + y * width] = Tile.STONE.getID();
				}
			}
		}
	}

	public void tick(){
	}

	public void renderTiles(Screen screen, int xOffset, int yOffset){
		if(xOffset < 0) xOffset = 0;
		if(xOffset > ((width << 3)- screen.width)) xOffset = ((width << 3) - screen.width);
		if(yOffset < 0) yOffset = 0;
		if(yOffset > ((height << 3)- screen.height)) yOffset = ((height << 3) - screen.height);

		screen.setOffset(xOffset, yOffset);

		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}
		}
	}

	public Tile getTile(int x, int y){
		if(x < 0 || x > width || y < 0 || y > height){
			return Tile.VOID;
		}
		return Tile.tiles[tiles[x + y * width]];
	}
}
