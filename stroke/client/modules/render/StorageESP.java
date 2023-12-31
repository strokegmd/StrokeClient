package net.stroke.client.modules.render;

import java.awt.Color;

import net.minecraft.block.BlockChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.util.RenderUtils;

public class StorageESP extends BaseModule {
	public StorageESP() {
		super("StorageESP", "Highlights storages", 0x00, ModuleCategory.Render, false);
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Chests", this, true));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Shulker Boxes", this, true));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Dispensers", this, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Hoppers", this, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Droppers", this, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Furnaces", this, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Ender Chests", this, false));
	}
	
	public void onRender3D() {
		boolean chests = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Chests").getValBoolean();
		boolean shulkers = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Shulker Boxes").getValBoolean();
		boolean dispensers = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Dispensers").getValBoolean();
		boolean hoppers = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Hoppers").getValBoolean();
		boolean droppers = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Droppers").getValBoolean();
		boolean furnaces = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Furnaces").getValBoolean();
		boolean enderChests = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Ender Chests").getValBoolean();
		
		for(TileEntity entity : mc.world.loadedTileEntityList) {
			if (entity instanceof TileEntityChest && chests) {
				TileEntityChest chest = (TileEntityChest) entity;
				this.drawChestESP(chest, chest.getPos().getX(), chest.getPos().getY(), chest.getPos().getZ());
			}
			
			if (entity instanceof TileEntityDispenser && dispensers) {
				RenderUtils.blockESP(entity.getPos(), Color.white, 1.0, 1.0);
			}
			
			if (entity instanceof TileEntityDropper && droppers) {
				RenderUtils.blockESP(entity.getPos(), Color.white, 1.0, 1.0);
			}
			
			if (entity instanceof TileEntityEnderChest && enderChests) {
				RenderUtils.blockESP(entity.getPos(), Color.magenta, 1.0, 1.0);
			}
			
			if (entity instanceof TileEntityFurnace && furnaces) {
				RenderUtils.blockESP(entity.getPos(), Color.white, 1.0, 1.0);
			}
			
			if (entity instanceof TileEntityHopper && hoppers) {
				RenderUtils.blockESP(entity.getPos(), Color.white, 1.0, 1.0);
			}
			
			if (entity instanceof TileEntityShulkerBox && shulkers) {
				RenderUtils.blockESP(entity.getPos(), Color.blue, 1.0, 1.0);
			}
		}
	}
		
		public void drawChestESP(TileEntityChest chest, double x, double y, double z) {
				boolean isAdjacent = chest.adjacentChestChecked;
				if(chest.adjacentChestXPos != null)
				{
					if(chest.getChestType() == BlockChest.Type.TRAP) { //if is trapped chest
						RenderUtils.blockESP(chest.getPos(), Color.red, 1.0, 2.0);
					}else{
						RenderUtils.blockESP(chest.getPos(), Color.orange, 1.0, 2.0);
					}
				}

				if(chest.adjacentChestZPos != null)
				{
					if(chest.getChestType() == BlockChest.Type.TRAP) { //if is trapped chest
						RenderUtils.blockESP(chest.getPos(), Color.red, 2.0, 1.0);
					}else{
						RenderUtils.blockESP(chest.getPos(), Color.orange, 2.0, 1.0);
					}
				}
				if(chest.adjacentChestZNeg == null && chest.adjacentChestXNeg == null && chest.adjacentChestXPos == null && chest.adjacentChestZPos == null)
				{
					if(chest.getChestType() == BlockChest.Type.TRAP) { //if is trapped chest
						RenderUtils.blockESP(chest.getPos(), Color.red, 1.0, 1.0);
					}else{
						RenderUtils.blockESP(chest.getPos(), 
								Color.orange, 
								1.0, 
								1.0);
					}
				}
			}
}
