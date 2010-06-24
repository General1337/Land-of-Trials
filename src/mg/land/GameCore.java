package mg.land;

import mg.land.event.*;
import android.content.res.AssetManager;
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
	public LuaManager lua;
	
	protected GameCore() // protected to enforce singleton
	{
	}
	
	public static GameCore getInstance()
	{
		if(instance == null) 
		{
			Log.v("Setup", "Creating game core...");
			instance = new GameCore();
			instance.init();
		}
		return instance;
	}
	
	public void init()
	{
		this.lua = new LuaManager();
		this.gameTime = new GameTime();
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * The main update loop of the game.
	 */
	public void run()
	{
		Log.v("System", "Beginning update loop...");
		lua.loadScript("test.lua");
		
		Running = true;
		while(Running)
		{
			gameTime.Update();
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