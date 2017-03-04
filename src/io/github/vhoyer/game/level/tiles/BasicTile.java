package io.github.vhoyer.game.level.tiles;

import io.github.vhoyer.game.level.Level;
import io.github.vhoyer.game.gfx.Screen;

public class BasicTile
	extends Tile {

	protected int tileID;
	protected int tileColour;
	private int scale = 1;

	public BasicTile(int id, int x, int y, int tileColour, int levelColour){
		super(id, false, false, levelColour);
		this.tileID = id;
		this.tileColour = tileColour;
	}

	public void render(Screen screen, Level level, int x, int y){
		screen.render(x, y, tileID, tileColour, 0x00, 1);
	}
}
