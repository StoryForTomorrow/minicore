/**
 * 
 */
package com.storyfortomorrow.core.module;

import com.storyfortomorrow.core.Main;

/**
 * @author 598Johnn897
 *		
 */
public abstract class Module
{
	protected ModuleLogger	logger	= new ModuleLogger(this);
	protected Main	plugin	= Main.get();
									
	public Module() throws ModuleException
	{
	
	}
	
	public ModuleInfo getInfo() throws ModuleException
	{
		if (this.getClass().getAnnotation(ModuleInfo.class) == null)
		{
			throw new ModuleException(
					"ModuleInfo annotation is null in class: " + this.getClass().getSimpleName() + "!");
		}
		else
		{
			return this.getClass().getAnnotation(ModuleInfo.class);
		}
	}
	
	public abstract void load();
	
	public abstract void enable();
	
	public abstract void disable();
	
}
