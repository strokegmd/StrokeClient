package net.stroke.client.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class ParticleEngine
{
    public CopyOnWriteArrayList<Particle> particles;
    public float lastMouseX;
    public float lastMouseY;
    
    public ParticleEngine() {
        this.particles = Lists.newCopyOnWriteArrayList();
    }
    
    public static void drawCircle(final double x, final double y, final float radius, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(9);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(x + Math.sin(i * 3.141526 / 180.0) * radius, y + Math.cos(i * 3.141526 / 180.0) * radius);
        }
        GL11.glEnd();
    }
    
    public static void disableRender2D() {
        GL11.glDisable(3042);
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }
    
    public static void enableRender2D() {
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.0f);
    }
    
    public static void setColor(final int colorHex) {
        final float alpha = (colorHex >> 24 & 0xFF) / 255.0f;
        final float red = (colorHex >> 16 & 0xFF) / 255.0f;
        final float green = (colorHex >> 8 & 0xFF) / 255.0f;
        final float blue = (colorHex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, (alpha == 0.0f) ? 1.0f : alpha);
    }
    
    public static void drawLine(final double startX, final double startY, final double endX, final double endY, final float thickness, final int color) {
        enableRender2D();
        setColor(color);
        GL11.glLineWidth(thickness);
        GL11.glBegin(1);
        GL11.glVertex2d(startX, startY);
        GL11.glVertex2d(endX, endY);
        GL11.glEnd();
        disableRender2D();
    }
    
    public void render(final float mouseX, final float mouseY) {
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 0.2f);
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        final float xOffset = 0.0f;
        final float yOffset = 0.0f;
        while (this.particles.size() < sr.getScaledWidth() / 8.0f) {
            this.particles.add(new Particle(sr, new Random().nextFloat() + 1.0f, new Random().nextFloat() * 5.0f + 5.0f));
        }
        final ArrayList toRemove = Lists.newArrayList();
        final int maxOpacity = 52;
        final int color = -570425345;
        final int mouseRadius = 100;
        for (final Particle particle : this.particles) {
            final double particleX = particle.x + Math.sin(particle.ticks / 10.0f) * 50.0 + -xOffset / 5.0f;
            final double particleY = particle.ticks * particle.speed * particle.ticks / 10.0f + -yOffset / 5.0f;
            if (particleY < sr.getScaledHeight()) {
                if (particle.opacity < maxOpacity) {
                    final Particle particle2 = particle;
                    particle2.opacity += 2.0f;
                }
                if (particle.opacity > maxOpacity) {
                    particle.opacity = (float)maxOpacity;
                }
                final Color c = new Color(255, 255, 255, (int)particle.opacity);
                final float particle_thickness = 1.0f;
                final int line_color = new Color(1.0f, (1.0f - (float)(particleY / sr.getScaledHeight())) / 2.0f, 1.0f, 1.0f).getRGB();
                GlStateManager.enableBlend();
                this.drawBorderedCircle(particleX, particleY, particle.radius * particle.opacity / maxOpacity, color, color);
            }
            particle.ticks += (float)0.05;
            if (particleY <= sr.getScaledHeight() && particleY >= 0.0 && particleX <= sr.getScaledWidth() && particleX >= 0.0) {
                continue;
            }
            toRemove.add(particle);
        }
        this.particles.removeAll(toRemove);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        this.lastMouseX = (float)GLUtils.getMouseX();
        this.lastMouseY = (float)GLUtils.getMouseY();
    }
    
    public void drawBorderedCircle(final double x, final double y, final float radius, final int outsideC, final int insideC) {
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glScalef(0.1f, 0.1f, 0.1f);
        drawCircle(x * 10.0, y * 10.0, radius * 10.0f, insideC);
        GL11.glScalef(10.0f, 10.0f, 10.0f);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
    }
}
