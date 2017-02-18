package io.github.vhoyer.game.level.tiles;

import io.github.vhoyer.game.gfx.Screen;
import io.github.vhoyer.game.level.Level;

public class BasicTile
	extends Tile {

	protected int tileID;
	protected int tileColour;

	public BasicTile(int id, int x, int y, int tileColour){
		super(id, false, false);
		this.tileID = id;
		this.tileColour = tileColour;
	}

	public void render(Screen screen, Level level, int x, int y){
		screen.render(x, y, tileID, tileColour);
	}
}
