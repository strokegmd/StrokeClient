package net.stroke.client.modules.render;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class BlockHitAnim extends BaseModule {
	public static BlockHitAnim instance;
	
	public BlockHitAnim() {
		super("BlockHitAnim", "Enables legacy blockhit animation", 0x00, ModuleCategory.Render, false);
		instance = this;
		// EDITS: net.minecraft.client.renderer.ItemRenderer
	}
}
