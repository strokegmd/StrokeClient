package net.stroke.client.modules.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class AutoTotem extends BaseModule {
	public int timer;
	
	public AutoTotem() {
		// stolen lol
		super("AutoTotem", "Automatically replaces totems", 0x00, ModuleCategory.Combat, false);
		StrokeClient.instance.settingsManager.rSetting(new Setting("Minimum Health", this, 20, 1, 20, true));
	}
	
	public void onEnable() {
		timer = 0; 
	}

	public void onUpdate() {
		double minHealth = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Minimum Health").getValDouble();
		if(mc.player.getHealth() > minHealth) {
			return;
		}
		
			if(timer > 0) {
				timer--;
				return;
			}
				
            NonNullList<ItemStack> inv;
            ItemStack offhand = mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);

            int inventoryIndex;

            inv = mc.player.inventory.mainInventory;
            
            for(inventoryIndex = 0; inventoryIndex < inv.size(); inventoryIndex++) {
               	if (inv.get(inventoryIndex) != ItemStack.field_190927_a) { // ItemStack.EMPTY
            		if ((offhand == null) || (offhand.getItem() != Items.field_190929_cY)) { // ItemStack.TOTEM
            			if (inv.get(inventoryIndex).getItem() == Items.field_190929_cY) { // ItemStack.TOTEM
            				replaceTotem(inventoryIndex);
            	            break;
            			}
            		}
                }
				timer = 3;
            }
		}
	
    public void replaceTotem(int inventoryIndex) {
        if (mc.player.openContainer instanceof ContainerPlayer) {
            mc.playerController.windowClick(0, inventoryIndex < 9 ? inventoryIndex + 36 : inventoryIndex, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, inventoryIndex < 9 ? inventoryIndex + 36 : inventoryIndex, 0, ClickType.PICKUP, mc.player);
        }
    }

}
