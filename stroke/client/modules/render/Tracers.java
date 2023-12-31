package net.stroke.client.modules.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.util.FriendsManager;

public class Tracers extends BaseModule {
	public Tracers() {
		super("Tracers", "Draws lines to entities in your render distance", 0x00, ModuleCategory.Render, false);
	}
	
	public void onRender3D() {
		try {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_LINE_SMOOTH);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glLineWidth(1.5F);
			
			for (Entity entities : mc.world.loadedEntityList) {
				if (entities != mc.player && entities != null) {
					if (entities instanceof EntityPlayer) {
						float distance = mc.renderViewEntity.getDistanceToEntity(entities);
						double posX = ((entities.lastTickPosX + (entities.posX - entities.lastTickPosX)
								- RenderManager.renderPosX));
						double posY = ((entities.lastTickPosY + (entities.posY - entities.lastTickPosY)
								- RenderManager.renderPosY));
						double posZ = ((entities.lastTickPosZ + (entities.posZ - entities.lastTickPosZ)
								- RenderManager.renderPosZ));
	
						if(FriendsManager.isFriend(entities.getName())) {
	    					GL11.glColor3f(0.0F, 1.0F, 0.0F);
	    				} else {
							if (distance <= 6F) {
								GL11.glColor3f(1.0F, 0.0F, 0.0F);
							} else if (distance <= 96F) {
								GL11.glColor3f(1.0F, (distance / 100F), 0.0F);
							} else if (distance > 96F) {
								GL11.glColor3f(0.1F, 0.6F, 255.0F);
							}
	    				}
						
						Vec3d eyes = new Vec3d(0, 0, 1).rotatePitch(-(float) Math.toRadians(mc.player.rotationPitch)).rotateYaw(-(float) Math.toRadians(mc.player.rotationYaw));
						
						GL11.glBegin(GL11.GL_LINE_LOOP);
						GL11.glVertex3d(eyes.xCoord, mc.player.getEyeHeight() + eyes.yCoord, eyes.zCoord);
						GL11.glVertex3d(posX, posY, posZ);
						GL11.glEnd();
					}
				}
			}
			
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_LINE_SMOOTH);
			GL11.glPopMatrix();
		} catch (Exception exception) {}
	}
}
