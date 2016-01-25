package com.storyfortomorrow.core.cmdframework.cmds;

import org.bukkit.entity.Player;

import com.storyfortomorrow.core.cmdframework.Command;
import com.storyfortomorrow.core.cmdframework.CommandArgs;
import com.storyfortomorrow.core.cmdframework.CommandListener;
import com.storyfortomorrow.core.util.C;

public class Me implements CommandListener
{
	@Command(name = "me")
	public void me(CommandArgs info)
	{
		if (info.isPlayer())
		{
			Player player = info.getPlayer();
			if (info.getArgs().length == 0) C.send(player, "<red>You did not provide me a message! (/me <message>)");
			else C.broadcast("* %s<reset> %s", player.getDisplayName(), info.getFinalArg(0));
		}
		else
		{
			if (info.getArgs().length == 0)
				info.getSender().sendMessage("You did not provide me a message! (/me <message>)");
			else C.broadcast("* Console %s", info.getFinalArg(0));
		}
	}
}
