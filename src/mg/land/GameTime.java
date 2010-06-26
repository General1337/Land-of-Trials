package mg.land;

public class GameTime 
{
	protected long _curTime;
	protected long _prevTime;
	protected long _pauseElapse;
	
	
	public GameTime()
	{
		this._curTime = this._prevTime = System.currentTimeMillis();
		_pauseElapse = 0;
	}
	
	public void Update()
	{
		this._prevTime = this._curTime;
		this._curTime = System.currentTimeMillis();
	}
	
	
	public long getElapsedTime()
	{
		return this._curTime - this._prevTime;
	}
	
	public long getCurTime()
	{
		return this._curTime;
	}
	
	public void pause()
	{
		_pauseElapse = getElapsedTime();
	}
	
	public void resume()
	{
		this._prevTime = System.currentTimeMillis() - _pauseElapse;
		this._curTime = System.currentTimeMillis();
		
	}
	
	public long getSystemTime()
	{
		return System.currentTimeMillis();
	}
	
}
