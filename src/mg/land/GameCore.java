package mg.land;

/*
 * This is the core. It is run as a thread, with the game loop running in its own thread.
 * This is to make timing and other such issues easier, as well as make it easier to respond to OS events.
 * 
 */
public class GameCore implements Runnable
{
	public volatile boolean Running;
	public GameTime gameTime;
	
	public GameCore()
	{
		this.gameTime = new GameTime();
		Running = true;
	}
	
	
	public void run()
	{
		while(Running)
		{
			gameTime.Update();
			System.out.println(Running);
		}
	}
}
