package net.stroke.client.modules.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class ClickGui extends BaseModule {
    public static ClickGui instance;
    
    public ClickGui() {
        super("ClickGui", "Manage module options", Keyboard.KEY_RSHIFT, ModuleCategory.Render, false);
        StrokeClient.instance.settingsManager.rSetting(new Setting("Tooltips", this, true));
        StrokeClient.instance.settingsManager.rSetting(new Setting("Snow", this, true));
        instance = this;
    }
    
    public void onUpdate() {
    	this.mc.displayGuiScreen(StrokeClient.clickGui);
    }
    
    public void onDisable() {
    	this.mc.displayGuiScreen((GuiScreen)null);
        this.mc.setIngameFocus();
    }
}