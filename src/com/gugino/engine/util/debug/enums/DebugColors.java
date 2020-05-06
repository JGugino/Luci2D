/*Created By: Gugino
 *Date Created: May 6, 2020
 */
package com.gugino.engine.util.debug.enums;

public enum DebugColors {
	RESET("\033[0m"),
	RED("\033[0;31m"),GREEN("\033[0;32m"),YELLOW("\033[0;33m"),BLUE("\033[0;34m"),
	PURPLE("\033[0;35m"),CYAN("\033[0;36m"),WHITE("\033[0;37m"),BLACK("\033[0;30m"),
	BRIGHT_RED("\033[1;91m"),BRIGHT_GREEN("\033[1;92m"),BRIGHT_YELLOW("\033[1;93m"),BRIGHT_BLUE("\033[1;94m"),
	BRIGHT_PURPLE("\033[1;95m"),BRIGHT_CYAN("\033[1;96m"),BRIGHT_WHITE("\033[1;97m"),BRIGHT_BLACK("\033[1;90m"),
	BLACK_BACKGROUND("\033[40m"),RED_BACKGROUND("\033[41m"),GREEN_BACKGROUND("\033[42m"),YELLOW_BACKGROUND("\033[43m"),
    BLUE_BACKGROUND("\033[44m"),MAGENTA_BACKGROUND("\033[45m"),CYAN_BACKGROUND("\033[46m"),WHITE_BACKGROUND("\033[47m");
	
	public String color;
	
	DebugColors(String _color){
		color = _color;
	}
}
