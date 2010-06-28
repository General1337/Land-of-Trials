package mg.land;


public class Resource 
{
	protected int guid;
	private static int curId;
	
	public static int getNextId()
	{
		synchronized(Main.getInstace())
		{
			
		}
		return curId;
	}
	
	public Resource()
	{
		guid = getNextId();
	}
	
	public int getId()
	{
		return guid;
	}
	
	static
	{
		curId = 0;
	}
}
