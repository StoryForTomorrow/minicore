/**
 * 
 */
package com.storyfortomorrow.core.module;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.storyfortomorrow.core.Main;

/**
 * @author Johnn
 *		
 */
public class ModuleLogger extends Logger
{
	private String name;
	
	/**
	 * 
	 * @param context
	 * @throws ModuleException
	 */
	public ModuleLogger(Module context) throws ModuleException
	{
		super(context.getClass().getCanonicalName(), null);
		
		String prefix = context.getInfo().name();
		name = prefix != null ? new StringBuilder().append("[").append(prefix).append("] ").toString()
				: "[" + context.getInfo().id() + "] ";
				
		setParent(Main.get().getServer().getLogger());
		setLevel(Level.ALL);
	}
	
	@Override
	public void log(LogRecord logRecord)
	{
		logRecord.setMessage(name + logRecord.getMessage());
		super.log(logRecord);
	}
}
