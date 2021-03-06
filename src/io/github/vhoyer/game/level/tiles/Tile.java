package io.github.vhoyer.game.level.tiles;

import io.github.vhoyer.game.level.Level;
import io.github.vhoyer.game.gfx.Screen;
import io.github.vhoyer.game.gfx.Colours;

public abstract class Tile {

	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colours.get(000, -1, -1, -1), 0xff000000);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0, Colours.get(-1, 333, -1, -1), 0xff555555);
	public static final Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1, 131, 141, -1), 0xff00ff00);

	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	private int levelColour;

	public Tile(int id, boolean isSolid, boolean isEmitter, int levelColour){
		this.id = (byte)id;
		if(tiles[id] != null) throw new RuntimeException("Duplicated tile id on " + id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.levelColour = levelColour;

		tiles[id] = this;
	}

	public byte getID(){
		return id;
	}

	public boolean isSolid(){
		return solid;
	}

	public boolean isEmitter(){
		return emitter;
	}

	public int getLevelColour(){
		return this.levelColour;
	}

	public abstract void render(Screen screen, Level level, int x, int y);
}
