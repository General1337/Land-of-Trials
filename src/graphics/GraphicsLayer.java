package graphics;

import java.util.List;

import mg.land.GameTime;
import mg.land.Main;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GraphicsLayer extends SurfaceView implements SurfaceHolder.Callback
{
	protected List<Sprite> _spriteList;
	protected Vector2 _cameraOrigin;
	protected Vector2 _cameraDims;
	protected SurfaceHolder _surfaceHolder;
	
	
	public GraphicsLayer(Context context) 
	{
		super(context);
		
		_cameraDims = new Vector2(500, 500);
		_cameraOrigin = new Vector2(0,0);
		
		_surfaceHolder = this.getHolder();
		getHolder().addCallback(this);
		setFocusable(true);
	}
	

	public void Render(Canvas canvas)
	{
		canvas.drawColor(Color.BLUE); // clear the scene
		/*for(Sprite sprite : _spriteList)
		{
			//canvas.drawBitmap(bitmap, srcRect, dstRect, paint)
			Vector2 transformedCoords = TransformCoords(sprite.getLocation());
			Vector2 dimensions = TransformCoords(new Vector2((float)sprite.getCellWidth(), sprite.getCellHeight()));
			
			
			canvas.drawBitmap(sprite.getTexture(), sprite.getSourceRect(), 
					new Rect((int)transformedCoords.x, (int)transformedCoords.y, (int)dimensions.x, (int)dimensions.y), null);
		}
		*/
		Vector2 location = TransformCoords(new Vector2(200, 200));
		Vector2 radius = TransformCoords(new Vector2(0, 5));
		canvas.drawCircle(location.x, location.y, radius.y, new Paint());
		Log.d("I won!","Drew a circle!");
		
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) 
	{
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Transforms world coordinates into pixel locations
	 */
	public Vector2 TransformCoords(Vector2 worldCoords)
	{
		Vector2 resolution = Main.getInstance().Resolution();
		return new Vector2(Math.round(worldCoords.x * (resolution.x / this._cameraDims.x)), 
				Math.round(worldCoords.y * (resolution.y / this._cameraDims.y)));
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void AddSprite(Sprite sprite)
	{
		_spriteList.add(sprite);
	}
	
	public void RemoveSprite(Sprite sprite)
	{
		_spriteList.remove(sprite);
	}
	
	/*
	 * Moves the camera to a location that centers on the location specified
	 */
	public void CenterCamera(Vector2 location)
	{
		this._cameraOrigin.x = location.x + (_cameraDims.x / 2);
		this._cameraOrigin.y = location.y + (_cameraDims.y / 2);
	}
	
	public void Update(GameTime gameTime)
	{
		
	}
}
