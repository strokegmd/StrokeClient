package net.stroke.client.modules.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.util.FriendsManager;
import net.stroke.client.util.TimerUtils;

public class KillAura extends BaseModule {
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static KillAura instance;
	
	public float yaw, pitch;
	
	public EntityPlayer attackingEntity;
	
	public KillAura() {
		super("KillAura", "Automatically attacks nearby players", 0x00, ModuleCategory.Combat, false);
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Aura Range", this, 5, 2, 6, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Client Rotations", this, false));
		
		instance = this;
		// EDITS: net.minecraft.client.entity.EntityPlayerSP
	}

	public void onUpdate() {
			double range = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Aura Range").getValDouble();
			boolean clientRotations = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Client Rotations").getValBoolean();
			
			for(EntityPlayer entity : mc.world.playerEntities) {
				if(mc.player.getDistanceToEntity(entity) <= range
					&& !entity.isDead
					&& entity.getUniqueID() != mc.player.getUniqueID()
					&& !entity.isCreative()
					&& !entity.isSpectator()
					&& entity.getName().length() < 17
					&& entity.getUniqueID().toString().split("-")[4] != "000000000000"
					&& !FriendsManager.isFriend(entity.getName())) {
						if(!clientRotations) {
							instance.yaw = getRotations(entity)[0];
							instance.pitch = getRotations(entity)[1];
						} else {
							mc.player.rotationYaw = getRotations(entity)[0];
							mc.player.rotationPitch = getRotations(entity)[1];
						}
						
						instance.attackingEntity = entity;
						
						if(Criticals.instance.enabled) {
							Criticals.instance.doCrit();
						}
						
						if(mc.player.getCooledAttackStrength(0.0f) == 1.0f) {
							mc.playerController.attackEntity(mc.player, entity);
							mc.player.swingArm(EnumHand.MAIN_HAND);
						}
			} else {
				if(instance.attackingEntity != null) {
					if(instance.attackingEntity.isDead || mc.player.getDistanceToEntity(instance.attackingEntity) >= range + 6) {
						instance.attackingEntity = null;
					}
				} else {
					instance.attackingEntity = null;
				}
			}
		}
	}
	
	public void onDisable() {
		instance.attackingEntity = null;
	}
	
	public static float[] getRotations(EntityPlayer entity) {
		double deltaX = entity.posX + (entity.posX - entity.lastTickPosX) - mc.player.posX,
			   deltaY = entity.posY - 3.5 + entity.getEyeHeight() - mc.player.posY + mc.player.getEyeHeight(),
			   deltaZ = entity.posZ + (entity.posZ - entity.lastTickPosZ) - mc.player.posZ;
		
		double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
		
		float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
			  pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
		
		if(deltaX < 0 && deltaZ < 0) {
			yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
		} else if(deltaX > 0 && deltaZ < 0) {
			yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
		}
		
		return new float[] {yaw, pitch};
    }
}
