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
	
	/**
	 * Call this once per frame to ensure consistent time reports.
	 */
	public void Update()
	{
		this._prevTime = this._curTime;
		this._curTime = System.currentTimeMillis();
	}
	
	/**
	 * Returns the elapsed game time since the last frame.
	 * @return
	 */
	public long getElapsedTime()
	{
		return this._curTime - this._prevTime;
	}
	
	/**
	 * Wrapper for OS's getTimeMillis
	 * @return
	 */
	public long getCurTime()
	{
		return this._curTime;
	}
	
	/**
	 * Call this whenever the thread is paused.
	 */
	public void pause()
	{
		_pauseElapse = getElapsedTime();
	}
	
	/**
	 * Call this whenever thread is resumed.
	 */
	public void resume()
	{
		this._prevTime = System.currentTimeMillis() - _pauseElapse;
		this._curTime = System.currentTimeMillis();
		
	}
	
	/**
	 * Wrapper for OS's getTimeMillis() function.
	 * @return
	 */
	public long getSystemTime()
	{
		return System.currentTimeMillis();
	}
	
}
