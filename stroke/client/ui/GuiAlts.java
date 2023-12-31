package net.stroke.client.ui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.stroke.client.util.SessionManager;

public class GuiAlts extends GuiScreen {
	public GuiTextField username;

	public void initGui() {
        username = new GuiTextField(69420, fontRendererObj, width / 2 - 100, 60, 200, 20);
        username.setText(mc.getSession().getUsername());
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(new ResourceLocation("stroke/background.jpg"));
		drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
		drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);
		
		username.drawTextBox();
		fontRendererObj.drawStringWithShadow("Current username: " + mc.getSession().getUsername(), width / 2 - 100, 84, 0xffffff);
	}
	
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        username.mouseClicked(mouseX, mouseY, mouseButton);
    }
	
    public void updateScreen() {
       username.updateCursorCounter();
    }
	
	public void keyTyped(char typedChar, int keyCode) {
		if(keyCode == Keyboard.KEY_RETURN) {
			SessionManager.setUsername(username.getText());
			return;
		}
		
		if(keyCode == Keyboard.KEY_ESCAPE) {
			mc.displayGuiScreen(new MainMenu());
			return;
		}
		
		username.textboxKeyTyped(typedChar, keyCode);
	}
}
