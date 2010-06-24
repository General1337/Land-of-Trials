package mg.land;

public class LuaInterface 
{
	protected static GameCore _core;
	
	private LuaInterface() 
	{
		/*
		 * Protected to disallow instantiation.
		 */
	}
	
	
	public void init(GameCore core)
	{
		_core = core;
	}
	
	
}
