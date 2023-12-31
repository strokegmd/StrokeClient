package net.stroke.client.modules.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.modules.combat.KillAura;
import net.stroke.client.util.ColorUtils;

public class TargetHUD extends BaseModule {
	public TargetHUD() {
		super("TargetHUD", "Shows info about enemy", 0x00, ModuleCategory.Render, false);
	}
	
	public void onRender() {
		if(mc.pointedEntity instanceof EntityPlayer || KillAura.instance.attackingEntity != null) {
			EntityPlayer target = null;
			if(KillAura.instance.attackingEntity != null) {
				target = KillAura.instance.attackingEntity;
			} else {
				target = (EntityPlayer)mc.pointedEntity;
			}
			
			if(target.getName().length() < 16) {
				Gui.drawRect(4, 15, 230, 66, Color.black.hashCode() * 100);
				
				drawPlayerHead(10, 15, target.getName());
				mc.fontRendererObj.drawStringWithShadow(target.getName(), 27, 10, ColorUtils.primaryColor);
				GL11.glPopMatrix();
				
				mc.fontRendererObj.drawStringWithShadow("Health: " + Math.round(target.getHealth()) + " HP", 54, 39, ColorUtils.primaryColor);
				
				Gui.drawRect(4, 64, 230, 66, Color.gray.hashCode());
				
				int width = (int)Math.round(target.getHealth()) * 11;
				if(target.getHealth() >= 20.0f) {
					width += 10;
				}
				
				Gui.drawRect(4, 64, width, 66, Color.red.hashCode());
			}
		}
	}
	
	public void drawPlayerHead(int x, int y, String username) {
		NetworkPlayerInfo info = mc.player.connection.getPlayerInfo(username);
		if(info != null) {
			GL11.glPushMatrix();
			GL11.glScalef(2.0f, 2.0f, 2.0f);
			
			mc.getTextureManager().bindTexture(info.getLocationSkin());
			GL11.glColor4f(1F, 1F, 1F, 1F);
			Gui.drawScaledCustomSizeModalRect(x - 5, y - 5, 8.0f, 8.0f, 8, 8, 20, 20, 64.0f, 64.0f);
		}
	}
}