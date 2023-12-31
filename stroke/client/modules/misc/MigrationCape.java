package net.stroke.client.modules.misc;

import net.minecraft.util.ResourceLocation;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class MigrationCape extends BaseModule {
	public static MigrationCape instance;
	
	public static ResourceLocation cape = new ResourceLocation("stroke/cape.png");
	
	public MigrationCape() {
		super("MigrationCape", "Enables Migration Cape for you", 0x00, ModuleCategory.Misc, false);
		instance = this;
		// EDITS: net.minecraft.client.network.NetworkPlayerInfo
	}
}
