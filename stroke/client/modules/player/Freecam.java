package net.stroke.client.modules.player;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class Freecam extends BaseModule {
	public static Freecam instance;
	
	public EntityOtherPlayerMP freePlayer;
	
	public double firstX;
	public double firstY;
	public double firstZ;
	
	public float firstYaw;
	public float firstPitch;
	public float firstYawHead;
	
	public Freecam() {
		super("Freecam", "Fly out of your body", 0x00, ModuleCategory.Player, false);
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Fly Speed", this, 1, 0, 10, true));
		instance = this;
		// EDITS: net.minecraft.client.entity.EntityPlayerSP
	}
	
	
	public void onEnable() {
		firstX = mc.player.posX;
		firstY = mc.player.posY;
		firstZ = mc.player.posZ;
		
		firstYaw = mc.player.rotationYaw;
		firstPitch = mc.player.rotationPitch;
		firstYawHead = mc.player.rotationYawHead;
		
		freePlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
		freePlayer.copyLocationAndAnglesFrom(mc.player);
		
		mc.world.addEntityToWorld(-2, freePlayer);
	}
	
	public void onUpdate() {
		double flySpeed = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Fly Speed").getValDouble();
		
		mc.player.capabilities.isFlying = true;
		mc.player.capabilities.setFlySpeed((float)flySpeed / 10.0f);
		mc.player.noClip = true;
		
		mc.player.motionX = 0.0f;
		mc.player.motionY = 0.0f;
		mc.player.motionZ = 0.0f;
		
		if(mc.gameSettings.keyBindJump.isKeyDown()) {
			mc.player.motionY += flySpeed / 100.0f;
		}
		
		if(mc.gameSettings.keyBindSneak.isKeyDown()) {
			mc.player.motionY -= flySpeed / 100.0f;
		}
	}
	
	public void onDisable() {
		mc.world.removeEntityFromWorld(-2);
		mc.player.capabilities.isFlying = false;
		mc.player.capabilities.setFlySpeed(0.05f);
		
		mc.player.setPosition(firstX, firstY, firstZ);
		mc.player.rotationYaw = firstYaw;
		mc.player.rotationPitch = firstPitch;
		mc.player.rotationYawHead = firstYawHead;
	}
}
