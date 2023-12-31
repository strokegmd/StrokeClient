package net.stroke.client.modules;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;

public class BaseModule {
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public String name;
	public String tooltip;
	
	public ModuleCategory category;
	public int keybind;
	
	public boolean autoenable;
	public boolean enabled;
	
	public Map<String, Integer> settings = new HashMap();
	
	public int posX;
	public int posY;
	
	public BaseModule(String name, String tooltip, int keybind, ModuleCategory category, boolean autoenable) {
		this.name = name;
		this.tooltip = tooltip;
		this.category = category;
		this.keybind = keybind;
		this.autoenable = autoenable;
		this.enabled = autoenable;
		
		System.out.println("[Stroke] Loaded " + this.name + "!");
	}
	
	public void toggle() {
		this.enabled = !this.enabled;
		if(this.enabled) {
			this.onEnable();
		} else {
			this.onDisable();
		}
	}
	
	public void setKey(int key)
	{
		this.keybind = key;
	}
	
	public void onEnable() {}
	public void onUpdate() {}
	public void onRender() {}
	public void onRender3D() {}
	public void onDisable() {}
}