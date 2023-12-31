package net.stroke.client.modules.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.util.FriendsManager;
import net.stroke.client.util.RenderUtils;

public class PlayerESP extends BaseModule {
	public PlayerESP() {
		super("PlayerESP", "Reveals player location", 0x00, ModuleCategory.Render, false);
		
		ArrayList<String> modes = new ArrayList<String>();
		modes.add("Box");
		modes.add("Outline");
		modes.add("Glow");
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("mode", this, modes, "Mode"));
	}
	
	public void onRender3D() {
		RenderManager renderManager = mc.getRenderManager();
		String mode = StrokeClient.instance.settingsManager.getSettingByName(this.name, "mode").getValString();
		
		for(EntityPlayer player : mc.world.playerEntities) {
			if(player != mc.player) {
				if(mode == "Box") {
					if(FriendsManager.isFriend(player.getName())) {
						RenderUtils.drawEntityESP(player, Color.green);
					} else {
						RenderUtils.drawEntityESP(player, Color.cyan);
					}
				} else if(mode == "Glow") {
					player.setGlowing(true);
				} else if(mode == "Outline") {
					GL11.glPushMatrix();
					GL11.glBlendFunc(770, 771);
					GL11.glEnable(GL_BLEND);
					GL11.glLineWidth(1.0F);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glDisable(GL_DEPTH_TEST);
					GL11.glDepthMask(false);
					
					RenderUtils.drawSelectionBoundingBox(new AxisAlignedBB(player.boundingBox.minX - 0.05 - player.posX + (player.posX - renderManager.renderPosX),
					player.boundingBox.minY - player.posY + (player.posY - renderManager.renderPosY),
					player.boundingBox.minZ - 0.05 - player.posZ + (player.posZ - renderManager.renderPosZ),
					player.boundingBox.maxX + 0.05 - player.posX + (player.posX - renderManager.renderPosX),
					player.boundingBox.maxY + 0.1 - player.posY + (player.posY - renderManager.renderPosY),
					player.boundingBox.maxZ + 0.05 - player.posZ + (player.posZ - renderManager.renderPosZ)));
					
					GL11.glLineWidth(2.0F);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glEnable(GL_DEPTH_TEST);
					GL11.glDepthMask(true);
					GL11.glDisable(GL_BLEND);
					GL11.glPopMatrix();
				}
            }
		}
	}
	
	public void onDisable() {
		String mode = StrokeClient.instance.settingsManager.getSettingByName(this.name, "mode").getValString();
		if(mode == "Glow") {
			for(EntityPlayer player : mc.world.playerEntities) {
				player.setGlowing(false);
			}
		}
	}
}
