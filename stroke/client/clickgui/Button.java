package net.stroke.client.clickgui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.util.ColorUtils;

public class Button {
    public Minecraft mc = Minecraft.getMinecraft();

    public int x, y, width, height;
    public boolean binding;
    public BaseModule module;

    public Button(int x, int y, int width, int height, BaseModule module) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.module = module;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(x + 10, y, x + width - 10, y + height, ColorUtils.secondaryObjectColor);
        mc.fontRendererObj.drawStringWithShadow(
        		!binding ? module.name : "< PRESS KEY >", x + width / 2 - mc.fontRendererObj.getStringWidth(!binding ? module.name : "< PRESS KEY >") / 2, y + height / 2 - 9 / 2, 
        				module.enabled && !binding ? ColorUtils.secondaryObjectColor : -1);
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (binding) {
            module.keybind = keyCode;
            binding = false;

            if (keyCode == Keyboard.KEY_ESCAPE) {
                module.keybind = 0;
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (HoverUtils.hovered(mouseX, mouseY, x, y, x + width, y + height)) {
            if (mouseButton == 0) {
                module.toggle();
            } else if (mouseButton == 2) {
                binding = !binding;
            }
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
    }
}
