package graphics;

import mg.land.GameTime;
import android.graphics.Bitmap;
import android.graphics.Rect;

/*
 * The information about the sprite (its frame height, width, number of columns / rows, animation information, etc.)
 * 
 */
public class Sprite 
{
	protected Bitmap _texture;
	protected Vector2 _location;
	protected int _cellWidth;
	protected int _cellHeight;
	protected int _numCols;
	protected int _numCells;
	
	protected int _curFrame;
	protected int _frameRate; // in fps
	protected int _timeElapsed;
	
	
	public Sprite(String spriteName)
	{
		// TODO: Implement this
	}
	
	public void Update(GameTime time)
	{
		
	}
	
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
}
