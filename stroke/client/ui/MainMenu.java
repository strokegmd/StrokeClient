package net.stroke.client.ui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.util.ResourceLocation;
import net.stroke.client.StrokeClient;
import net.stroke.client.util.ColorUtils;

public class MainMenu extends GuiScreen {	
	public String[] buttons = {"Singleplayer", "Multiplayer", "Alts", "Options", "Ragequit!1!11!!", ""};
	
	public boolean singleplayer = false;
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if(!singleplayer) {
			buttonList.clear();
			
			mc.getTextureManager().bindTexture(new ResourceLocation("stroke/background.jpg"));
			drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
			drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);
			
			GL11.glPushMatrix();
			GL11.glScalef(3.0f, 3.0f, 3.0f);
			mc.fontRendererObj.drawStringWithShadow("Stroke Client", 2, 2, ColorUtils.primaryColor);
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			GL11.glScalef(1.0f, 1.0f, 1.0f);
			mc.fontRendererObj.drawStringWithShadow("version 1.0.00", 9, 33, ColorUtils.primaryColor);
			GL11.glPopMatrix();
			
			mc.fontRendererObj.drawStringWithShadow("Minecraft 1.12.2", 2, StrokeClient.getDisplayHeight() - 20, ColorUtils.primaryColor);
			mc.fontRendererObj.drawStringWithShadow("With OptiFine, ViaVersion", 2, StrokeClient.getDisplayHeight() - 10, ColorUtils.primaryColor);
			
			int buttonNumber = 0;
			for(String button : buttons) {
				int stringWidth = mc.fontRendererObj.getStringWidth(button);
				int posY = 15 * buttonNumber + StrokeClient.getDisplayHeight() / 3;
				buttonList.add(new GuiButton(buttonNumber, 6, posY, stringWidth, mc.fontRendererObj.FONT_HEIGHT, ""));
				buttonNumber++;
			}
			
			int trueButtonNumber = 0;
			for(String button : buttons) {
				int stringWidth = mc.fontRendererObj.getStringWidth(button);
				int posY = 15 * trueButtonNumber + StrokeClient.getDisplayHeight() / 3;
				GuiButton trueButton = buttonList.get(trueButtonNumber);
				
				// hovered
				if(mouseX >= trueButton.xPosition && mouseY >= trueButton.yPosition && mouseX < trueButton.xPosition + trueButton.width && mouseY < trueButton.yPosition + trueButton.height ) {
					mc.fontRendererObj.drawStringWithShadow(button, 6, posY, ColorUtils.secondaryColor);
				} else {
					mc.fontRendererObj.drawStringWithShadow(button, 6, posY, ColorUtils.primaryColor);
				}
				
				trueButtonNumber++;
			}
		}
	}

	public void actionPerformed(GuiButton button) {
		if(button.id == 0) { // singleplayer
			mc.displayGuiScreen(new GuiWorldSelection(this));
		}
		
		if(button.id == 1) { // multiplayer
			mc.displayGuiScreen(new GuiMultiplayer(this));
		}
		
		if(button.id == 2) { // alts
			mc.displayGuiScreen(new GuiAlts());
		}
		
		if(button.id == 3) { // options
			mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
		}
		
		if(button.id == 4) { // ragequit
			mc.shutdown();
		}
	}
}
