package net.stroke.client.modules.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.EnumHand;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class CrystalAura extends BaseModule {
	public CrystalAura() {
		super("CrystalAura", "Automatically destroys nearby crystals", 0x00, ModuleCategory.Combat, false);
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Crystal Range", this, 4, 1, 6, true));
	}
	
	public void onUpdate() {
		double range = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Crystal Range").getValDouble();
		for(Entity entity : mc.world.loadedEntityList) {
			if(entity instanceof EntityEnderCrystal && mc.player.getDistanceToEntity(entity) <= range) {
				mc.playerController.attackEntity(mc.player, entity);
				mc.player.swingArm(EnumHand.MAIN_HAND);	
			}
		}
	}
}
