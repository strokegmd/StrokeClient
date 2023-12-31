package net.stroke.client.modules.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class AutoArmor extends BaseModule {
	public AutoArmor() {
		// stolen lol
		super("AutoArmor", "Automatically equips best armour from your inventory", 0x00, ModuleCategory.Combat, false);
	}
	
	private int timer;
	private Item NULL_ITEM;

	public void onEnable() {
		timer = 0; 
		NULL_ITEM = Item.getItemFromBlock(Blocks.AIR);
	}

	public void onUpdate() {
		if(timer > 0) {
			timer--;
			return;
		}
				
		if(mc.currentScreen instanceof GuiContainer && !(mc.currentScreen instanceof InventoryEffectRenderer))
			return;
				
		int[] bestArmorSlots = new int[4];
		int[] bestArmorValues = new int[4];
				
		for(int armorType = 0; armorType < 4; armorType++) {
			ItemStack oldArmor = mc.player.inventory.armorItemInSlot(armorType);
			if(oldArmor != null && oldArmor.getItem() instanceof ItemArmor)
				bestArmorValues[armorType] = ((ItemArmor)oldArmor.getItem()).damageReduceAmount;
					
				bestArmorSlots[armorType] = -1;
		}
				
		for(int slot = 0; slot < 36; slot++) {
			ItemStack stack = mc.player.inventory.getStackInSlot(slot);
			if(stack == null || !(stack.getItem() instanceof ItemArmor))
				continue;
					
			ItemArmor armor = (ItemArmor)stack.getItem();
			int armorType = this.getArmorType(armor);
			int armorValue = armor.damageReduceAmount;
					
			if(armorValue > bestArmorValues[armorType]) {
				bestArmorSlots[armorType] = slot;
				bestArmorValues[armorType] = armorValue;
			}
		}
				
		for(int armorType = 0; armorType < 4; armorType++) {
			int slot = bestArmorSlots[armorType];
			if(slot == -1)
				continue;
						
			ItemStack oldArmor = mc.player.inventory.armorItemInSlot(armorType);
			if(oldArmor == null || !this.isEmptySlot(oldArmor) || mc.player.inventory.getFirstEmptyStack() != -1) {
				if(slot < 9)
					slot += 36;
				
				if(mc.player.ticksExisted % 2 == 0) {
					mc.playerController.windowClick(0, 8 - armorType, 0, ClickType.QUICK_MOVE, mc.player);
					mc.playerController.windowClick(0, slot, 0, ClickType.QUICK_MOVE, mc.player);
				}
						
				break;
			}
		}
				
		timer = 4;
	}

	public int getArmorType(ItemArmor armor) {
		return armor.armorType.ordinal() - 2;
	}
	
	public boolean isEmptySlot(ItemStack slot) {
		return slot.getItem() == NULL_ITEM;
	}
}