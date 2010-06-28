package mg.land;


import graphics.Vector2;

import java.io.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import mg.land.event.*;
import org.keplerproject.luajava.*;

/**
 * The primary activity for the project, houses all OS callbacks.
 * This class instantiates the GameCore object in a seperate thread.
 * It is also a wrapper for most of the OS-specific functions.
 * @author Holtzen
 *
 */
public class Main extends Activity 
{
	
	public static GameCore game;
	protected static Main _main;
	
	public Thread gameThread;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        Main._main = this;
        //this.getResources().getIdentifier(name, defType, defPackage)
    }
    
    public static Main getInstance()
    {
    	return _main;
    }
    
    
    /**
     * Called when the game is first started or resumes from losing focus.
     */
    @Override
    public void onStart()
    {    	
    	Log.v("System", "Starting activity...");
    	super.onStart();
        setContentView(R.layout.main);
        game = (GameCore) this.findViewById(R.layout.main);
    }
    
    /**
     * Called when the game loses focus.
     */
    @Override 
    public void onStop()
    {
    	Log.v("System", "Stopping activity...");
    	super.onStop();
    	game.Pause();
    }
    
    /**
     * Called when the game is removed from memory / cleaned up.
     */
    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    }
    
    /**
     * Called when the game regains focus after losing it.
     */
    @Override
    public void onResume()
    {
    	
    	super.onResume();
    }
    
    @Override
    public void onPause()
    {
    	Log.v("System", "Pausing activity...");
    	game.Pause();
    	super.onPause();
    }
}
