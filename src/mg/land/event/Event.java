package mg.land.event;

import java.util.Iterator;
import java.util.List;

/*
 * 
 */
public class Event
{
	protected List<EventListener> eventList;
	
	public void Listen(EventListener item)
	{
		eventList.add(item);
	}
	public void Detatch(EventListener item)
	{
		eventList.remove(item);
	}
	
	public void Invoke( )
	{
		
	}
}
