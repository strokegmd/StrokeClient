package net.stroke.client.modules.fun;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class HalalMode extends BaseModule {
	public HalalMode() {
		super("HalalMode", "Now you can't eat porkchop", 0x00, ModuleCategory.Fun, false);
	}
	
	public void onUpdate() {
		if(mc.player.getActiveItemStack().getItem() == Items.PORKCHOP || mc.player.getActiveItemStack().getItem() == Items.COOKED_PORKCHOP) {
			mc.shutdown();
		}
	}
}
