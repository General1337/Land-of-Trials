package mg.land;

import java.io.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import mg.land.event.*;
import org.keplerproject.luajava.*;

public class Main extends Activity 
{
	
	public static GameCore game;
	public Thread gameThread;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d("Testing Lua", "Hello.");
        try 
        {
            LuaState state = LuaStateFactory.newLuaState();
            /*
             * Convert the input stream into a string to be sent to lua.
             */
            InputStream stream = getAssets().open("test.lua");
            
            
            Log.d("Test file name", );
            state.LdoString();
        } catch (Exception e)
        {
        	e.printStackTrace();
        }

        
        game = new GameCore();
    }
    
    @Override
    public void onStart()
    {    	
    	super.onStart();
    	gameThread = new Thread(game);
    	gameThread.start();

    }
    
    public void onResume()
    {
    	
    	super.onResume();
    }
}
