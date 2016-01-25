/**
 * 
 */
package com.storyfortomorrow.core.cmdframework.cmds;

import com.storyfortomorrow.core.cmdframework.Command;
import com.storyfortomorrow.core.cmdframework.CommandArgs;
import com.storyfortomorrow.core.cmdframework.CommandListener;
import com.storyfortomorrow.core.util.C;

/**
 * @author 598Johnn897
 * 		
 */
public class Lenny implements CommandListener
{
	@Command(name = "lenny", description = "enny lennylenny lenny", usage = "")
	public void lenny(final CommandArgs info)
	{
		C.send(info.getSender(), "<gold>What are you doing tonight?");
	}
}
