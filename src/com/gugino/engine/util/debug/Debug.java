/*Created By: Gugino
 *Date Created: May 6, 2020
 */
package com.gugino.engine.util.debug;

import com.gugino.engine.util.debug.enums.DebugColors;

public class Debug {
	
	private final static String DEBUG_PREFIX = "[Luci2D:Debug] - ";
	private final static String ERROR_PREFIX = "[Luci2D:Error] - ";
	private final static String WARNING_PREFIX = "[Luci2D:Warning] - ";
	
	private final static DebugColors DEFAULT_DEBUG_COLOR = DebugColors.BRIGHT_BLUE;

	public static void printLine(String _contents) {
		System.out.println(DEFAULT_DEBUG_COLOR.color + DEBUG_PREFIX + _contents + DebugColors.RESET.color);
	}
	
	public static void printLine(String _contents, DebugColors _color) {
		System.out.println(_color.color + DEBUG_PREFIX + _contents + DebugColors.RESET.color);
	}

	public static void printLine(String _contents, DebugColors _backgroundColor, DebugColors _textColor) {
		System.out.println(_backgroundColor.color + _textColor.color + DEBUG_PREFIX + _contents + DebugColors.RESET.color);
	}

	public static void printWarning(String _contents) {
		System.out.println(DebugColors.BRIGHT_YELLOW.color + WARNING_PREFIX + _contents + DebugColors.RESET.color);
	}

	public static void printError(String _contents) {
		System.out.println(DebugColors.BRIGHT_RED.color + ERROR_PREFIX + _contents + DebugColors.RESET.color);
	}
}
