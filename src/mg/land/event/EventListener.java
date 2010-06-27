package mg.land.event;

/**
 * Base event listener interface.
 * All event listeners extend this interface.
 */
public interface EventListener 
{
	public void Invoke(Object arg1, Object arg2, Object arg3);
}
