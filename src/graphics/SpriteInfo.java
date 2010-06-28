package graphics;

import java.util.Dictionary;

import android.graphics.Bitmap;

public class SpriteInfo 
{
	public Bitmap texture;
	public int cellWidth;
	public int cellHeight;
	public int numCols;
	public int numFrames;
	
	public int curFrame;
	public int timeElapsed;
	public Dictionary<String, Animation> animations;
	
	public SpriteInfo(Bitmap texture, int cellWidth, int cellHeight, int numCols,
	int numFrames, Dictionary<String, Animation> animations)
	{
		this.texture = texture;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.numCols = numCols;
		this.numFrames = numFrames;
		this.animations = animations;
		this.curFrame = 0;
	}
}
