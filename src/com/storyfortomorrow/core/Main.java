/**
 * 
 */
package com.storyfortomorrow.core;

import static com.storyfortomorrow.core.lib.References.JSON;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import lombok.Getter;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONException;
import org.json.JSONObject;

import com.storyfortomorrow.core.cmdframework.CommandFramework;
import com.storyfortomorrow.core.lib.References;
import com.storyfortomorrow.core.module.ModuleCore;
import com.storyfortomorrow.core.module.ModuleException;
import com.storyfortomorrow.core.module.Registry;
import com.storyfortomorrow.core.util.JsonCache;

/**
 * @author 598Johnn897
 * 		
 */
public class Main extends JavaPlugin
{
	private static Main instance;
	
	public static Main get()
	{
		Validate.notNull(instance);
		return instance;
	}
	
	public final SFTLog		clogger	= new SFTLog(this);
	@Getter
	public CommandFramework	cmdFramework;
							
	@Getter
	public ModuleCore		moduleCore;
							
	private int				start, end;
							
	@Override
	public void onLoad()
	{
		instance = this;
		start = (int) System.currentTimeMillis();
		
		if (!this.getDataFolder().exists())
		{
			File dir = this.getDataFolder();
			dir.mkdir();
		}
		
		try
		{
			JSON = new JsonCache(
					new File(Main.get().getDataFolder().getAbsolutePath() + References.JSON_FILE),
					new URL(References.JSON_URL), 15).getJson();
		}
		catch (IOException | JSONException e)
		{
			e.printStackTrace();
			try
			{
				JSON = new JSONObject().put("error", e.toString());
			}
			catch (JSONException e1)
			{
				e1.printStackTrace();
			}
		}
		
		try
		{
			References.MODULE_DIRECTORY = References.WORKING_DIRECTORY + JSON.getString("module_directory");
			
			clogger.log(Level.INFO, JSON.getJSONObject("messages").getString("loading"), new Object[]
			{ this.getDescription().getName(), this.getDescription().getVersion()
			});
		}
		catch (JSONException e1)
		{
			e1.printStackTrace();
		}
	}
	
	@Override
	public void onEnable()
	{
		try
		{
			cmdFramework = new CommandFramework(get());
			cmdFramework.registerCommands(get());
			cmdFramework.registerHelp();
			
			Registry.registerEvents(get());
			
			moduleCore = new ModuleCore();
			moduleCore.init();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			end = (int) (System.currentTimeMillis() - start);
			try
			{
				clogger.log(Level.INFO, JSON.getJSONObject("messages").getString("enabled"), new Object[]
				{ this.getDescription().getName(), this.getDescription().getVersion(), end
				});
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	public void onDisable()
	{
		try
		{
			moduleCore.disable();
			
			clogger.log(Level.INFO, JSON.getJSONObject("messages").getString("disabled"), new Object[]
			{ this.getDescription().getName(), this.getDescription().getVersion(),
					TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - start)
			});
		}
		catch (JSONException | ModuleException e)
		{
			e.printStackTrace();
		}
	}
}
