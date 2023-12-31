package net.stroke.client.modules.render;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.modules.ModuleManager;
import net.stroke.client.util.ColorUtils;

public class Hud extends BaseModule {
	public StrokeClient client;
	
	public Hud() {
		super("Hud", "Shows enabled modules and important information", 0x00, ModuleCategory.Render, true);
		// totally not stolen from kami
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Coordinates", this, true));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Module List", this, true));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Username", this, true));
	}
	
	public void onRender() {
		boolean coordinates = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Coordinates").getValBoolean();
		boolean moduleList = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Module List").getValBoolean();
		boolean username = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Username").getValBoolean();
		
		int xBorder = StrokeClient.getScaledWidth() - 2;
		int yBorder = StrokeClient.getScaledHeight() - 10;
		
		int versionX = mc.fontRendererObj.getStringWidth(client.name);
		int usernameX = mc.fontRendererObj.getStringWidth("Hello, ") + 2;
		int markX = mc.fontRendererObj.getStringWidth("Hello, " + StrokeClient.getUsername()) + 2;
		
		double playerX = Math.round(mc.player.posX * 10.0) / 10.0;
		double playerY = Math.round(mc.player.posY * 10.0) / 10.0;
		double playerZ = Math.round(mc.player.posZ * 10.0) / 10.0;
		
		double playerNetherX = playerX / 8.0f;
		double playerNetherY = playerY;
		double playerNetherZ = playerZ / 8.0f;
		
		playerNetherX = Math.round(playerNetherX * 10.0) / 10.0;
		playerNetherZ = Math.round(playerNetherZ * 10.0) / 10.0;
		
		String coords = Double.toString(playerX) + ", " + Double.toString(playerY) + ", " + Double.toString(playerZ);
		String netherCoords = Double.toString(playerNetherX) + ", " + Double.toString(playerNetherY) + ", " + Double.toString(playerNetherZ);
		
		int xyzX = xBorder - 22 - mc.fontRendererObj.getStringWidth(coords);
		int coordsX = xBorder - 22 - mc.fontRendererObj.getStringWidth(coords) + mc.fontRendererObj.getStringWidth("XYZ ");
		
		int xyzY = yBorder - 10;
		int coordsY = xyzY;
		
		int netherX = xBorder - 37 - mc.fontRendererObj.getStringWidth(netherCoords);
		int netherCoordsX = xBorder - 37 - mc.fontRendererObj.getStringWidth(netherCoords) + mc.fontRendererObj.getStringWidth("Nether ");
		
		int netherY = yBorder;
		int netherCoordsY = netherY;
		
		int rainbowColor = ColorUtils.getRainbow(2.0f, 0.8f, 1.0f, 100);
		
		Gui.drawRect(3, 2, 124, 1, rainbowColor);
		Gui.drawRect(3, 2, 124, 14, Color.black.hashCode() * 100);
		
		mc.fontRendererObj.drawStringWithShadow(client.name, 4, 4, ColorUtils.primaryColor);
		mc.fontRendererObj.drawStringWithShadow(client.version, versionX, 4, ColorUtils.secondaryColor);
		
		if(username) {
			mc.fontRendererObj.drawStringWithShadow("Hello, ", 2, yBorder, ColorUtils.primaryColor);
			mc.fontRendererObj.drawStringWithShadow(StrokeClient.getUsername(), usernameX, yBorder, ColorUtils.secondaryColor);
			mc.fontRendererObj.drawStringWithShadow("!", markX, yBorder, ColorUtils. primaryColor);
		}
		
		if(coordinates) {
			mc.fontRendererObj.drawStringWithShadow("XYZ", xyzX, xyzY, ColorUtils.secondaryColor);
			mc.fontRendererObj.drawStringWithShadow(coords, coordsX, coordsY, ColorUtils.primaryColor);
			
			mc.fontRendererObj.drawStringWithShadow("Nether ", netherX, netherY, ColorUtils.secondaryColor);
			mc.fontRendererObj.drawStringWithShadow(netherCoords, netherCoordsX, netherCoordsY, ColorUtils.primaryColor);
		}
		
		int moduleNumber = 0;
		for (BaseModule module : ModuleManager.modules) {
			if (!module.enabled) {
				continue;
			}
			
			int posX = xBorder - mc.fontRendererObj.getStringWidth(module.name) + module.posX;
			int posY = 10 * moduleNumber + 2 + module.posY;
			
			int rainbowModuleColor = ColorUtils.getRainbow(2.0f, 0.5f, 1.0f, moduleNumber * 100);
			
			if(moduleList) {
				Gui.drawRect(posX - 3, posY + 9, StrokeClient.getScaledWidth() + 1, posY - 1, rainbowModuleColor);
				Gui.drawRect(posX - 2, posY + 9, StrokeClient.getScaledWidth(), posY - 1, Color.black.hashCode() * 80);
				mc.fontRendererObj.drawStringWithShadow(module.name, posX, posY, rainbowModuleColor);
			}
			
			moduleNumber++;
		}
	}
}
