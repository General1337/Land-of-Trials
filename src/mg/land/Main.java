package mg.land;

import graphics.GraphicsLayer;
import graphics.Vector2;

import java.io.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import mg.land.event.*;
import org.keplerproject.luajava.*;

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
        game = GameCore.getInstance();
        setContentView(game.gl);
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
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);

    	
    	gameThread= new Thread(game);
    	gameThread.start();

    }
    
    public Vector2 Resolution()
    {
    	DisplayMetrics dm = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(dm);
    	return new Vector2(dm.widthPixels, dm.heightPixels);
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
