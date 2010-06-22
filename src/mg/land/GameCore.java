package mg.land;

import mg.land.event.*;
import android.util.Log;

/*
 * This is the core. It is run as a thread, with the game loop running in its own thread.
 * This is to make timing and other such issues easier, as well as make it easier to respond to OS events.
 * 
 * This is a singleton class.
 */
public class GameCore implements Runnable
{
	// for singleton
	protected static GameCore instance = null;
	
	protected volatile boolean Running;
	public GameTime gameTime;

	
	protected GameCore() // protected to enforce singleton
	{
	}
	
	public GameCore getInstance()
	{
		if(instance == null) 
		{
			instance = new GameCore();
			
			this.gameTime = new GameTime();
		}
		return instance;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * The main update loop of the game.
	 */
	public void run()
	{
		Running = true;
		while(Running)
		{
			//gameTime.Update();
			//System.out.println(Running);
		}
	}
	
	public void Pause()
	{
		this.Running = false;
	}
	public void Resume()
	{
		this.Running = true;
	}
}
