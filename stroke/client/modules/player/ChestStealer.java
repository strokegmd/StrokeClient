package net.stroke.client.modules.player;

import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class ChestStealer extends BaseModule {
	public int ticks;
	public int empty;
	
	public ChestStealer() {
		super("ChestStealer", "Automatically steals all items from chest", 0x00, ModuleCategory.Player, false);
		StrokeClient.instance.settingsManager.rSetting(new Setting("Delay", this, 35, 1, 100, true));
	}
	
	public void onEnable() {
		ticks = 0;
		empty = 0;
	}
	
	public boolean isContainerEmpty(Container container) {
		boolean temp = true;
	    int i = 0;
	    
	    for(int slotAmount = container.inventorySlots.size() == 90 ? 54 : 35; i < slotAmount; i++) {
	    	if (container.getSlot(i).getHasStack()) {
	    		temp = false;
	    	}
	    }
	    
	    return temp;
	}

	public void onUpdate() {
		long delay = (long) StrokeClient.instance.settingsManager.getSettingByName(this.name, "Delay").getValDouble();
		
		if(mc.currentScreen instanceof GuiChest) {
			Container open = mc.player.openContainer;
			if(!this.isContainerEmpty(open)) {
				for(int slotId = 0; slotId < open.inventorySlots.size() - 36; slotId++) {
					Slot slot = open.getSlot(slotId);
					if(slot.getHasStack() && slot.getStack() != null) {
						if(this.ticks >= delay / 10) {
							mc.playerController.windowClick(open.windowId, slotId, 1, ClickType.QUICK_MOVE, mc.player);
							this.ticks = 0;
						}
					}
				}
	
				this.ticks++;
			} else {
				mc.player.closeScreen();
			}
		}
	}
}
