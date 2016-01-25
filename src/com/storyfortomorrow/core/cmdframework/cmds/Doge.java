/**
 * 
 */
package com.storyfortomorrow.core.cmdframework.cmds;

import com.storyfortomorrow.core.cmdframework.Command;
import com.storyfortomorrow.core.cmdframework.CommandArgs;
import com.storyfortomorrow.core.util.C;

/**
 * @author John
 * 		
 */
public class Doge
{
	@Command(name = "doge")
	public void doge(CommandArgs info)
	{
		C.send(info.getSender(), "<gold>DOGE DogE DOGe dOGE DOGE ODG EOD GEOD GODOGE");
	}
}
