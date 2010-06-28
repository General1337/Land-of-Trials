package mg.land;

import java.util.ArrayList;
import java.util.List;

import graphics.Sprite;
import graphics.Vector2;
import mg.land.event.*;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * This is the core. It is run as a thread, with the game loop running in its own thread.
 * This is to make timing and other such issues easier, as well as make it easier to respond to OS events.
 * 
 * This is a singleton class.
 */
public class GameCore extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
	/** for singleton */
	protected static GameCore instance = null;
	
	/**
	 * When false, the main loop terminates.
	 */
	protected volatile boolean Running;
	
	public GameTime gameTime;
	public LuaManager lua;
	public ContentPipeline pipeline;
	
	protected List<Sprite> _spriteList;
	protected Vector2 _cameraOrigin;
	protected Vector2 _cameraDims;
	protected SurfaceHolder _surfaceHolder;
	protected boolean _ready;
	protected Thread gameThread;
	
	
	public GameCore(Context context, AttributeSet attrs) // protected to enforce singleton
	{
		super(context, attrs);
		
		_cameraDims = new Vector2(500, 500);
		_cameraOrigin = new Vector2(0,0);
		_spriteList = new ArrayList<Sprite>();
		pipeline = new ContentPipeline();
	
		_surfaceHolder = this.getHolder();
		getHolder().addCallback(this);
		setFocusable(true);
		
		lua = new LuaManager();
		gameTime = new GameTime();
		
		gameThread = new Thread(this);
	}
	
	
	/**
	 * The main loop of the game, run in a seperate thread from the main activity.
	 * This thread will continue indefinitely until the Pause() function is called.
	 * 
	 */
	public void run()
	{
		Log.v("System", "Beginning update loop...");
		lua.loadScript("test.lua");
		
		Running = true;
		while(Running)
		{
			gameTime.Update();
			
			Canvas c = null;
			try
			{
				c = getHolder().lockCanvas(null);
				synchronized(this)
				{
					Render(c);
				}
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    getHolder().unlockCanvasAndPost(c);
                }
            }
		}
	}
	
	/**
	 * Call to pause the game. Threadsafe.
	 */
	public void Pause()
	{
		Log.v("System", "Pausing game core...");
		
		this.Running = false;
		try {
			this.gameThread.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Call to resume. Threadsafe.
	 */
	public void Resume()
	{
		Log.v("System", "Resuming game core...");
		
		this.Running = true;
		this.gameThread.notify();
	}
	
	
	public void Destroy()
	{

	}
	
	
	/**
	 * Renders the frame
	 * @param canvas
	 */
	public void Render(Canvas canvas)
	{
		canvas.drawColor(Color.BLUE); // clear the scene
		
		// just return if there is nothing to draw
		if(_spriteList.isEmpty())
			return;
		
		for(Sprite sprite : _spriteList)
		{
			//canvas.drawBitmap(bitmap, srcRect, dstRect, paint)
			sprite.Update(this.gameTime);
			Vector2 transformedCoords = TransformCoords(sprite.getLocation());
			Vector2 dimensions = TransformCoords(new Vector2((float)sprite.getCellWidth(), sprite.getCellHeight()));
			
			canvas.drawBitmap(sprite.getTexture(), sprite.getSourceRect(), 
				new Rect((int)transformedCoords.x, (int)transformedCoords.y, (int)dimensions.x, (int)dimensions.y), null);
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) 
	{
		// TODO Auto-generated method stub
	}
	
	/**
	 * Transforms world coordinates into pixel locations
	 */
	public Vector2 TransformCoords(Vector2 worldCoords)
	{
		return new Vector2(Math.round(worldCoords.x * (this.getWidth() / this._cameraDims.x)), 
				Math.round(worldCoords.y * (this.getHeight() / this._cameraDims.y)));
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
		this.Running = true;
		gameThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
		this.Running = false;
		
	}
	
	
	//*******************************************************************
	// * Graphics layer functionality
	// * 
	// *
	
	
	public void AddSprite(Sprite sprite)
	{
		synchronized(this)
		{
			_spriteList.add(sprite);
		}
	}
	
	
	public void RemoveSprite(Sprite sprite)
	{
		synchronized(this)
		{
			_spriteList.remove(sprite);
		}
	}
	
	
	/**
	 * Moves the camera to a location that centers on the location specified
	 */
	public void CenterCamera(Vector2 location)
	{
		synchronized(this)
		{
			this._cameraOrigin.x = location.x + (_cameraDims.x / 2);
			this._cameraOrigin.y = location.y + (_cameraDims.y / 2);
		}
	}
}