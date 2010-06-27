package graphics;

import java.util.Dictionary;
import java.util.List;

import mg.land.GameTime;
import android.graphics.Bitmap;
import android.graphics.Rect;

/*
 * The information about the sprite (its frame height, width, number of columns / rows, animation information, etc.)
 * 
 */
public class Sprite implements Cloneable
{
	protected Bitmap _texture;
	protected Vector2 _location;
	protected int _cellWidth;
	protected int _cellHeight;
	protected int _numCols;
	protected int _numFrames;
	
	protected int _curFrame;
	protected int _timeElapsed;
	protected Dictionary<String, Animation> _animations;
	
	
	public Sprite(String spriteName)
	{
		// TODO: Implement this
	}
	
	
	public Sprite(Bitmap texture, Vector2 location, int cellWidth, int cellHeight, int numCols,
			int numFrames, Dictionary<String, Animation> animations)
	{
		this._texture = texture;
		this._location = location;
		this._cellWidth = cellWidth;
		this._cellHeight = cellHeight;
		this._numCols = numCols;
		this._numFrames = numFrames;
		this._animations = animations;
		this._curFrame = 0;
		
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
		return _location;
	}
	
	public Bitmap getTexture()
	{
		return _texture;
	}
	
	public Rect getSourceRect()
	{
		// TODO: Actually implement this.
		return new Rect();
		
	}
	
	public int getCellWidth()
	{
		return _cellWidth;
	}
	public int getCellHeight()
	{
		return _cellHeight;
	}
	
	/**
	 * Returns a copy of the sprite.
	 */
	public Sprite clone()
	{
		return new Sprite(this._texture, new Vector2(0,0), this._cellWidth, this._cellHeight, this._numCols, this._numFrames,
				this._animations);
	}
}
