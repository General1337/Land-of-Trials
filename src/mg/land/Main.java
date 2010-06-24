package mg.land;

import java.io.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import mg.land.event.*;
import org.keplerproject.luajava.*;
import junit.framework.Assert;

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
        setContentView(R.layout.main);
        
        
        Main._main = this;
        game = GameCore.getInstance();
        
    }
    
    public static Main getInstance()
    {
    	return _main;
    }
    
    /*********************************************
     * Begin application layer functionality
     */
    public InputStream getAsset(String asset)
    {
    	InputStream out = null;
    	try
    	{
    		out = getAssets().open(asset);
    	    return out;
    	}
    	catch(Exception e)
    	{
    		Log.e("Unable to locate asset: ", asset);
    		
    	}

    	return out;
    }
    
    
    @Override
    public void onStart()
    {    	
    	Log.v("System", "Starting activity...");
    	super.onStart();
    	gameThread = new Thread(game);
    	gameThread.start();

    }
    
    public void onResume()
    {
    	
    	super.onResume();
    }
     
    public static int open(LuaState L) throws LuaException
    {
    	Log.d("Lua called me!", "WOOO");
      L.newTable();
      L.pushValue(-1);
      L.setGlobal("eg");

      L.pushString("example");

      L.pushJavaFunction(new JavaFunction(L) {
        /**
         * Example for loadLib.
         * Prints the time and the first parameter, if any.
         */
        public int execute() throws LuaException
        {
          if (L.getTop() > 1)
          {
            System.out.println(getParam(2));
          }
          
          return 0;
        }
      });

      L.setTable(-3);

      return 1;
    }
    
}
