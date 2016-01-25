/**
 * 
 */
package com.storyfortomorrow.core.lib;

import java.io.File;

import org.json.JSONObject;

/**
 * @author 598Johnn897
 *		
 */
public class References
{
	public static String		JSON_URL	= "https://raw.githubusercontent.com/StoryForTomorrow/sftmc/master/sft.json";
	public static String		JSON_FILE	= "/sft.json";
	public static JSONObject	JSON;
								
	public static String		WORKING_DIRECTORY	= new File(".").getAbsolutePath();
													
	public static String		MODULE_DIRECTORY;
}
