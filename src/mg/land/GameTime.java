package mg.land;

public class GameTime 
{
	protected long _curTime;
	protected long _prevTime;
	
	public GameTime()
	{
		this._curTime = this._prevTime = System.nanoTime();
	}
	
	public void Update()
	{
		this._prevTime = this._curTime;
		this._curTime = System.nanoTime();
	}
	
	public long getElapsedTime()
	{
		return this._curTime - this._prevTime;
	}
	
	public long getCurTime()
	{
		return this._curTime;
	}
	
}
