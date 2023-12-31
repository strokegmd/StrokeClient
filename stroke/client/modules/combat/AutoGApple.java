package net.stroke.client.modules.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class AutoGApple extends BaseModule {
	public boolean eating = false;
	
	public AutoGApple() {
		super("AutoGApple", "Automatically eats golden apples", 0x00, ModuleCategory.Combat, false);
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Minimum Health", this, 10, 1, 20, true));
	}
	
	public void onUpdate() {
		NonNullList<ItemStack> mainInv = mc.player.inventory.mainInventory;
		KeyBinding useItem = mc.gameSettings.keyBindUseItem;
		
		double minHealth = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Minimum Health").getValDouble();
		
		for(int slot = 0; slot < mainInv.size(); slot++) {
			if(mc.player.getHealth() < minHealth) {
				if(mainInv.get(slot).getItem() == Items.GOLDEN_APPLE) {
					mc.playerController.windowClick(0, (slot < 9) ? (slot + 36) : slot, 0, ClickType.PICKUP, mc.player);
				    mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
				    mc.playerController.windowClick(0, (slot < 9) ? (slot + 36) : slot, 0, ClickType.PICKUP, mc.player);
				             
				    useItem.setKeyBindState(useItem.getKeyCode(), true);
				    eating = true;
				} else if(mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
					useItem.setKeyBindState(useItem.getKeyCode(), true);
			    	eating = true;
				}
			}
		
		if(mc.player.getHealth() > minHealth && eating) {
			useItem.setKeyBindState(useItem.getKeyCode(), false);
			eating = false;
		}
		}
	}
}