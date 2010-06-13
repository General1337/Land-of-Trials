package mg.land;

/*
 * This is the core. It is run as a thread, with the game loop running in its own thread.
 * This is to make timing and other such issues easier, as well as make it easier to respond to OS events.
 * 
 */
public class GameCore implements Runnable
{
	protected volatile boolean Running;
	public GameTime gameTime;
	
	public GameCore()
	{
		this.gameTime = new GameTime();
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
			gameTime.Update();
			System.out.println(Running);
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
