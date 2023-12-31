package net.stroke.client.modules.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.util.FriendsManager;
import net.stroke.client.util.RenderUtils;

public class NameTags extends BaseModule {
	public static NameTags instance;
	
	public NameTags() {
		super("NameTags", "Displays custom nametags", 0x00, ModuleCategory.Render, false);
		instance = this;
	}
	
	public void onRender3D() {
            for (EntityPlayer player : mc.world.playerEntities) {
                if (player == null) continue;
                if (player.deathTime > 0) continue;
                if (player == mc.player) continue;
				double x = ((player.lastTickPosX + (player.posX - player.lastTickPosX)
						- RenderManager.renderPosX));
				double y = ((player.lastTickPosY + (player.posY - player.lastTickPosY)
						- RenderManager.renderPosY));
				double z = ((player.lastTickPosZ + (player.posZ - player.lastTickPosZ)
						- RenderManager.renderPosZ));
                renderTag(player, x, y, z);
            }
		}
	
    private void renderTag(Entity entity, double x, double y, double z) {
        String name = entity.getName();
        if(FriendsManager.isFriend(name)) {
        	name = "\2479" + name;
        }
        
        if (entity instanceof EntityLivingBase) {
        	name = name + " \247a" + ((double)Math.round((((EntityLivingBase) entity).getHealth() * 100) / 100) / 2);
        }
        
        float var13 = 1.6F;
        float var14 = (float) (0.016666668F * (mc.player.getDistanceToEntity(entity)) / 2);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y + entity.height + 0.5F, (float) z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-RenderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(RenderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-var14, -var14, var14);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_LIGHTING);
        Tessellator var15 = Tessellator.getInstance();
        BufferBuilder vertexbuffer = var15.getBuffer();
        int var16 = (int) -mc.player.getDistanceToEntity(entity) / (int) var13;
        if (entity.isSneaking()) {
            var16 += 4;
        } else if (var16 < -14) {
            var16 = -14;
        }

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        int var17 = mc.fontRendererObj.getStringWidth(name) / 2;
        RenderUtils.drawBorderedRect(-var17 - 2, var16, var17 + 2, 11 + var16, 0.5F, 0xFF000000, 0x80000000);

        mc.fontRendererObj.drawStringWithShadow(name, -var17, var16, 0xFFFFFF);
        
        mc.entityRenderer.disableLightmap();
        GL11.glLineWidth(1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        
        GL11.glPopMatrix();
    }
    
    
    public static void drawItem(ItemStack itemstack, int i, int j) 
	{
    	final RenderItem itemRenderer = mc.getRenderItem();
    	//TODO: FOR RENDERING BLOCKS IN PLAYERESP GuiIngame.itemRenderer.renderIcon(j, j, null, j, j);
		itemRenderer.renderItemIntoGUI(itemstack, i, j);
		itemRenderer.renderItemOverlayIntoGUI(mc.fontRendererObj, itemstack, i, j, null);
	    GL11.glDisable(2884 /* GL_CULL_FACE */);
	    GL11.glEnable(3008 /* GL_ALPHA_TEST */);
	    GL11.glDisable(3042 /* GL_BLEND */);
	    GL11.glDisable(2896 /* GL_LIGHTING */);
	    GL11.glDisable(2884 /* GL_CULL_FACE */);
	    GL11.glClear(256);
	}
}