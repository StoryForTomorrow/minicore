/**
 * 
 */
package com.storyfortomorrow.core.module;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import com.storyfortomorrow.core.Main;
import com.storyfortomorrow.core.util.ClassEnumerator;

/**
 * @author 598Johnn897
 *		
 */
public class Registry
{
	
	public static void registerModule(Module module) throws ModuleException
	{
		registerEvents(module);
		Main.get().cmdFramework.registerCommands(module);
	}
	
	public static void registerEvents(Object clazz)
	{
		Class<?>[] classes = ClassEnumerator.getInstance().getClassesFromThisJar(clazz);
		if (classes == null || classes.length == 0) return;
		for (Class<?> c : classes)
		{
			try
			{
				if (Listener.class.isAssignableFrom(c) && !c.isInterface() && !c.isEnum() && !c.isAnnotation())
				{
					Main.get().getServer().getPluginManager().registerEvents((Listener) c.newInstance(),
							Main.get());
				}
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void unregisterEvents(Object clazz)
	{
		Class<?>[] classes = ClassEnumerator.getInstance().getClassesFromThisJar(clazz);
		if (classes == null || classes.length == 0) return;
		for (Class<?> c : classes)
		{
			try
			{
				if (Listener.class.isAssignableFrom(c) && !c.isInterface() && !c.isEnum() && !c.isAnnotation())
				{
					HandlerList.unregisterAll((Listener) c.newInstance());
				}
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}
}
