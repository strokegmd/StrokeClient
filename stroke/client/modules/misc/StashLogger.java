package net.stroke.client.modules.misc;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.math.BlockPos;
import net.stroke.client.StrokeClient;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class StashLogger extends BaseModule {
	public ArrayList<TileEntity> alreadyFound = new ArrayList<TileEntity>();
	
	public int chestCounter = 0;
	
	public StashLogger() {
		super("StashLogger", "Sends a private message to chat, if founds shulkers or chests near you", 0x00, ModuleCategory.Misc, false);
	}
	
	public void onUpdate() {
		for(TileEntity entity : mc.world.loadedTileEntityList) {
			if(entity instanceof TileEntityShulkerBox && !alreadyFound.contains(entity)) {
				StrokeClient.sendChatMessage("FOUND SHULKER! XYZ: " + blockPosToString(entity.getPos()));
				alreadyFound.add(entity);
			}
			
			if(entity instanceof TileEntityChest && !alreadyFound.contains(entity) && !isInSpawner(entity)) {
				this.chestCounter++;
			}
			
			if(this.chestCounter > 5) {
				StrokeClient.sendChatMessage("FOUND CHESTS! XYZ: " + blockPosToString(entity.getPos()));
				alreadyFound.add(entity);
				this.chestCounter = 0;
			}
		}
	}
	
	public boolean isInSpawner(TileEntity entity) {
		for(TileEntity tile : mc.world.loadedTileEntityList) {
			if(tile instanceof TileEntityMobSpawner) {
				if(entity.getPos().getY() == tile.getPos().getY()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public String blockPosToString(BlockPos pos) {
		return Integer.toString(pos.getX()) + ", " + Integer.toString(pos.getY()) + ", " + Integer.toString(pos.getZ());
	}
}
