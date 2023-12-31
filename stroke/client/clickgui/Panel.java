package net.stroke.client.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.modules.ModuleManager;

public class Panel {
    public Minecraft mc = Minecraft.getMinecraft();

    public int x, y, width, height, dragY, dragX;
    public boolean extended, dragging;
    public ModuleCategory category;

    public List<Button> buttons = new ArrayList<>();

    public Panel(int x, int y, int width, int height, ModuleCategory category) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.category = category;

        int y1 = y + height;

        for (BaseModule module : ModuleManager.modules) {
            if (module.category == category) {
                buttons.add(new Button(x, y1, width, height, module));
                y1 += height;
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }

        Gui.drawRect(x, y, x + width, y + height, new Color(0xf3b48b).hashCode());
        mc.fontRendererObj.drawStringWithShadow(category.name(), x + width / 2 - mc.fontRendererObj.getStringWidth(category.name()) / 2, y + height / 2 - 9 / 2, -1);

        if (extended) {
            int y1 = y + height;
            for (Button button : buttons) {
                button.x = x;
                button.y = y1;

                y1 += height;

                button.drawScreen(mouseX, mouseY, partialTicks);
            }
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (extended) {
            for (Button button : buttons) {
                button.keyTyped(typedChar, keyCode);
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (HoverUtils.hovered(mouseX, mouseY, x, y, x + width, y + height)) {
            if (mouseButton == 0) {
                dragX = mouseX - x;
                dragY = mouseY - y;
                dragging = true;
            } else if (mouseButton == 1) {
                extended = !extended;
            }
        }

        if (extended) {
            for (Button button : buttons) {
                button.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;

        if (extended) {
            for (Button button : buttons) {
                button.mouseReleased(mouseX, mouseY, state);
            }
        }
    }
}
