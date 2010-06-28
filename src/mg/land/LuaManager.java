package mg.land;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.keplerproject.luajava.*;
import android.util.Log;


public class LuaManager 
{
	protected LuaState lua;
	 
	public LuaManager()
	{
		Log.v("Setup", "Creating lua manager...");
		lua = LuaStateFactory.newLuaState();
		lua.openLibs();
	}  
	
	/**
	 * Loads a script from the assets folder, given a string name.
	 * @param asset
	 */
	public void loadScript(String asset)
	{
		try{
	        InputStream stream = Main.getInstance().getAssets().open(asset);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	       	StringBuilder sb = new StringBuilder();
	       	String line = null;
	       	while((line = reader.readLine()) != null)
	       	{
	       		sb.append(line + "\n");
	       	}
	        
	       	reader.close();
	       	stream.close();
	       	
	       	if(lua.LdoString(sb.toString()) == 1)
	       	{
	       		// return value of 1 means a runtime error
	       		Log.e("Lua Runtime Error", "Error in: " + asset);
	       		lua.error();
	       	}
		} catch(Exception e)
		{
			Log.e("Error loading asset at LuaManager.loadScript()", asset);
			e.printStackTrace();
		}
	}
}
