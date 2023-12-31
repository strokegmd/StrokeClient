package net.stroke.client.modules.render;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class NoScoreBoard extends BaseModule {
	public static NoScoreBoard instance;
	
	public NoScoreBoard() {
		super("NoScoreBoard", "Removes scoreboard", 0x00, ModuleCategory.Render, false);
		instance = this;
		// EDITS: net.minecraft.client.gui.GuiIngame
	}
}
