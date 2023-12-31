package net.stroke.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.util.text.TextComponentString;
import net.stroke.client.clickgui.ClickGuiScreen;
import net.stroke.client.commands.CommandManager;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleManager;
import net.stroke.client.util.SettingsManager;
import viamcp.ViaMCP;

public class StrokeClient {
	public static Minecraft mc;
	
	public static String dev_username = "megao4ko";
	public static String title = "[stroke] client  ・ v1.0.00 | Minecraft 1.12.2";
	public static String name = "[stroke] client ・ ";
	public static String version = "v1.0.00";
	
	public static StrokeClient instance;
	public static ClickGuiScreen clickGui;
	
	public static SettingsManager settingsManager;
	
	public static boolean destructed = false;
	
	public StrokeClient()
	{
		instance = this;
		settingsManager = new SettingsManager();
		clickGui = new ClickGuiScreen();
	}
	
	public static void launchClient() {
		mc = Minecraft.getMinecraft();
		settingsManager = new SettingsManager();
		
		ViaMCP.getInstance().start();
		
		System.out.println("[Stroke] Launching Stroke Client " + version + "!");
		
		ModuleManager.loadModules();
		CommandManager.loadCommands();
		
		for(BaseModule module : ModuleManager.modules) {
			if(module.autoenable) {
				module.toggle(); module.toggle(); // bugfix lmao
			}
		}
		
		clickGui = new ClickGuiScreen();
		
		System.out.println("[Stroke] All done!");
	}
	
	public static void onKeyPress(int key) {
		for(BaseModule module : ModuleManager.modules) {
			if(module.keybind == key && !(mc.currentScreen instanceof GuiChat) && !(mc.currentScreen instanceof GuiContainer) && !(mc.currentScreen instanceof GuiEditSign)) {
				module.toggle();
			}
		}
	}
	
	public static int getScaledWidth() {
		mc = Minecraft.getMinecraft();
		ScaledResolution sr = new ScaledResolution(mc);
		return sr.getScaledWidth();
	}
	
	public static int getScaledHeight() {
		mc = Minecraft.getMinecraft();
		ScaledResolution sr = new ScaledResolution(mc);
		return sr.getScaledHeight();
	}
	
	public static int getDisplayWidth() {
		mc = Minecraft.getMinecraft();
		return mc.displayWidth / 2;
	}
	
	public static int getDisplayHeight() {
		mc = Minecraft.getMinecraft();
		return mc.displayHeight / 2;
	}
	
	public static String getUsername() {
		mc = Minecraft.getMinecraft();
		return mc.getSession().getUsername();
	}
	
	public static void sendChatMessage(String message) {
		mc = Minecraft.getMinecraft();
		TextComponentString component = new TextComponentString("[\2479Stroke§r] " + message);
		mc.ingameGUI.getChatGUI().printChatMessage(component);
	}
}