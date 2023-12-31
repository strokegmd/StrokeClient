package net.stroke.client.modules.movement;

import net.minecraft.network.play.client.CPacketPlayer;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.util.TimerUtils;

public class Flight extends BaseModule {
	public static Flight instance;
	
	public TimerUtils timer;
	
	public Flight() {
		super("Flight", "Allows you to fly", 0x00, ModuleCategory.Movement, false);
		instance = this;
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Fly Speed", this, 1, 0, 20, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Glide Speed", this, 0.05, 0.001, 0.3, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Damage Cooldown", this, 1, 0.1, 10, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Damage", this, false));
	}
	
	public void applyDamage() {
	    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 3.001D, mc.player.posZ, false));
	    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
	    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, true));
	}
	
	public void onEnable() {
		timer = new TimerUtils();
		
		boolean damage = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Damage").getValBoolean();
		if(damage) {
			this.applyDamage();
		}
	}
	
	public void onUpdate() {
		double flySpeed = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Fly Speed").getValDouble();
		double glideSpeed = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Glide Speed").getValDouble();
		double damageCooldown = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Damage Cooldown").getValDouble();
		boolean damage = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Damage").getValBoolean();
		
		mc.player.capabilities.isFlying = true;
		mc.player.capabilities.setFlySpeed((float) flySpeed / 10.0f);
		
		mc.player.motionX = 0.0f;
		mc.player.motionY = -glideSpeed;
		mc.player.motionZ = 0.0f;
		
		if(damage && timer.hasTimeElapsed((long) damageCooldown * 1000)) {
			this.applyDamage();
		}
 		
		if(mc.gameSettings.keyBindJump.isKeyDown()) {
			mc.player.motionY += flySpeed / 10.0f;
		}
		
		if(mc.gameSettings.keyBindSneak.isKeyDown()) {
			mc.player.motionY -= flySpeed / 10.0f;
		}
	}
	
	public void onDisable() {
		mc.player.capabilities.isFlying = false;
		mc.player.capabilities.setFlySpeed(0.05f);
	}
}
