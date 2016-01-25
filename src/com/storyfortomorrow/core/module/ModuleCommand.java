/**
 * 
 */
package com.storyfortomorrow.core.module;

import static com.storyfortomorrow.core.Main.get;

import java.util.logging.Level;

import com.storyfortomorrow.core.cmdframework.Command;
import com.storyfortomorrow.core.cmdframework.CommandArgs;
import com.storyfortomorrow.core.cmdframework.CommandListener;
import com.storyfortomorrow.core.util.C;

/**
 * @author 598Johnn897
 *		
 */
public class ModuleCommand implements CommandListener
{
	@Command(name = "module", permission = "cryptic.module", aliases =
	{ "md"
	})
	public void module(CommandArgs info)
	{
		C.send(info.getSender(),
				"Running <green>%s <reset>v<green>%s <reset>with <green>%d <reset>module%s loaded.",
				get().getDescription().getName(), get().getDescription().getVersion(), ModuleCore.getModules().size(),
				ModuleCore.getModules().size() == 1 ? "" : "s");
		C.send(info.getSender(), "For more information about the modules do \"<green>/module help<reset>\"!");
	}
	
	@Command(name = "module.help", permission = "cryptic.module.help")
	public void modulehelp(CommandArgs info)
	{
		String[] msg = new String[]
		{ "Module Core Commands:", "/module help <green>- <reset>Displays this message.",
				"/module list <green>- <reset>Lists the current running modules.",
				"/module reload <green>- <reset>Reloads the modules.",
				"/module version <green>- <reset>Shows the version for the module core.",
				"/module info [id] <green>- <reset>Shows the information about a certain module."
		};
		
		for (String line : msg)
			C.send(info.getSender(), line);
	}
	
	@Command(name = "module.list", permission = "cryptic.module.list")
	public void modulelist(CommandArgs info) throws ModuleException
	{
		StringBuilder string = new StringBuilder("Modules (");
		string.append("<green>");
		string.append(Integer.toString(ModuleCore.getModules().size()));
		string.append("<reset>): <green>");
		for (Module module : ModuleCore.getModules())
		{
			string.append(module.getInfo().name());
			if (ModuleCore.getModules().size() > 1) string.append(", ");
		}
		
		C.send(info.getSender(), string.toString());
	}
	
	@Command(name = "module.info", permission = "cryptic.module.info")
	public void moduleinfo(CommandArgs info)
	{
		if (info.getArgs().length == 0)
		{
			C.send(info.getSender(), "Module Core v%s running on %s with %s modules loaded.",
					ModuleCore.getVERSION(), get().getServer().getVersion(), ModuleCore.getModules().size());
		}
		else
		{
			C.send(info.getSender(), "<gold>This feature is currently being worked on!");
		}
	}
	
	@Command(name = "module.reload", permission = "cryptic.module.reload")
	public void modulereload(CommandArgs info) throws ModuleException
	{
		new ModuleCore().reload();
		C.sendop(Level.INFO, info.getSender(), "<green>Module Core was reloaded!");
	}
}
