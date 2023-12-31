package net.stroke.client.modules.render;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class AntiOverlay extends BaseModule {
	public static AntiOverlay instance;
	
	public AntiOverlay() {
		super("AntiOverlay", "Prevents rendering of fire and other annoying things", 0x00, ModuleCategory.Render, false);
		instance = this;
		// EDITS: net.minecraft.client.renderer.entity.ItemRenderer
		// EDITS: net.minecraft.client.gui.GuiIngame
	}
}
