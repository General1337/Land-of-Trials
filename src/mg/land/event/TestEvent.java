package mg.land.event;

import java.util.Iterator;
import java.util.List;

public class TestEvent
{
	public List<EventListener> listeners;
	
	public TestEvent()
	{
		System.out.println("I've been created by Lua!");
	}
	
	public void AddListener(EventListener listener)
	{
		System.out.println("Added listener");
		listener.Invoke("hello", "there", 3);
	}
	
	public void Trigger()
	{
		System.out.println("Triggered!");
		listeners.get(0).Invoke("hello", "there", "lua");
	}
}
 