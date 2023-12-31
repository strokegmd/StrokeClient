package net.stroke.client.util;

import java.util.Random;

import net.minecraft.client.gui.ScaledResolution;

public class Particle
{
    public float x;
    public float y;
    public float radius;
    public float speed;
    public float ticks;
    public float opacity;
    
    public Particle(final ScaledResolution sr, final float radius, final float speed) {
        this.x = new Random().nextFloat() * sr.getScaledWidth();
        this.y = new Random().nextFloat() * sr.getScaledHeight();
        this.ticks = new Random().nextFloat() * sr.getScaledHeight() / 2.0f;
        this.radius = radius;
        this.speed = speed;
    }
}
