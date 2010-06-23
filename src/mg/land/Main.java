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
        System.out.println("Hello from main?");
        try 
        { 
        	LuaState L = LuaStateFactory.newLuaState();
        	L.openLibs();
        	
            /*
             * Convert the input stream into a string to be sent to lua.
             */ 
            InputStream stream = getAssets().open("test.lua");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
           	StringBuilder sb = new StringBuilder();
           	String line = null;
           	while((line = reader.readLine()) != null)
           	{
           		sb.append(line + "\n");
           	}
            
           	reader.close();
           	stream.close();
            Log.d("Test file contents", sb.toString());
            L.LdoString(sb.toString()); 
            System.out.println("Test output..." + L.error());
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
