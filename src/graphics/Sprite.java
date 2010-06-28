package graphics;

import java.util.Dictionary;
import java.util.List;

import mg.land.GameTime;
import mg.land.Main;
import android.graphics.Bitmap;
import android.graphics.Rect;

/*
 * The information about the sprite (its frame height, width, number of columns / rows, animation information, etc.)
 * 
 */
public class Sprite implements Cloneable
{
	protected SpriteInfo _info;
	protected Vector2 _location;
	
	public Sprite(String spriteName)
	{
		// TODO: Implement this
	}
	
	
	public Sprite(SpriteInfo info)
	{
		_info = info;
	}
	
	/**
	 * Call this once per frame to maintain animation.
	 * @param time
	 */
	public void Update(GameTime time)
	{
		
	}
	
	/**
	 * The origin of the sprite.
	 * @return
	 */
	public Vector2 getLocation()
	{
		synchronized(Main.game)
		{
			return this._location;
		}
	}
	
	public void setLocation(float x, float y)
	{
		synchronized(Main.game)
		{
			this._location.x = x;
			this._location.y = y;
		}
	}
	
	public Bitmap getTexture()
	{
		return _info.texture;
	}
	
	public Rect getSourceRect()
	{
		// TODO: Actually implement this.
		return new Rect();
		
	}
	
	public int getCellWidth()
	{
		return _info.cellWidth;
	}
	public int getCellHeight()
	{
		return _info.cellHeight;
	}
	
	/**
	 * Returns a copy of the sprite.
	 */
	public Sprite clone()
	{
		return new Sprite(_info);
	}
}
