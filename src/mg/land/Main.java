package mg.land;

import android.app.Activity;
import android.os.Bundle;
import org.luaj.platform.*;
import org.luaj.vm.*;


public class Main extends Activity 
{
	
	public static GameCore game;
	public Thread gameThread;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        game = new GameCore();
    }
    
    @Override
    public void onStart()
    {
    	gameThread = new Thread(game);
    	gameThread.start();
    }
    
    public void onResume()
    {
    	
    }
}
