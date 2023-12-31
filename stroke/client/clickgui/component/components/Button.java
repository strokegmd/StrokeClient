package net.stroke.client.clickgui.component.components;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.clickgui.component.Component;
import net.stroke.client.clickgui.component.Frame;
import net.stroke.client.clickgui.component.components.sub.Checkbox;
import net.stroke.client.clickgui.component.components.sub.Keybind;
import net.stroke.client.clickgui.component.components.sub.ModeButton;
import net.stroke.client.clickgui.component.components.sub.Slider;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.util.ColorUtils;

public class Button extends Component {

	public BaseModule mod;
	public Frame parent;
	public int offset;
	private boolean isHovered;
	private ArrayList<Component> subcomponents;
	public boolean open;
	public int height;
	public FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
	public Button(BaseModule mod, Frame parent, int offset) {
		this.mod = mod;
		this.parent = parent;
		this.offset = offset;
		this.height = 12;
		this.subcomponents = new ArrayList<Component>();
		this.open = false;
		int opY = offset + 12;
		if(StrokeClient.instance.settingsManager.getSettingsByMod(mod) != null) {
			for(Setting s : StrokeClient.instance.settingsManager.getSettingsByMod(mod)){
				if(s.isCombo()){
					this.subcomponents.add(new ModeButton(s, this, mod, opY));
					opY += 12;
				}
				if(s.isSlider()){
					this.subcomponents.add(new Slider(s, this, opY));
					opY += 12;
				}
				if(s.isCheck()){
					this.subcomponents.add(new Checkbox(s, this, opY));
					opY += 12;
				}
			}
		}
		
		this.subcomponents.add(new Keybind(this, opY));
	}

	@Override
	public void setOff(int newOff) {
		offset = newOff;
		int opY = offset + 12;
		for(Component comp : this.subcomponents) {
			comp.setOff(opY);
			opY += 12;
		}
	}

	@Override
	public void renderComponent() {
		Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, this.isHovered ? 0x99222222 : 0x99111111);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.mod.name, (parent.getX() + 5), (parent.getY() + offset + 2), this.mod.enabled ? ColorUtils.secondaryObjectColor : 0xFFFFFF); //0x999999
		if(this.subcomponents.size() >= 2) {
			Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.open ? "-" : "+", (parent.getX()+parent.getWidth()-10), (parent.getY() + offset + 2), ColorUtils.settingColor);
		}
		if(this.open) {
			if(!this.subcomponents.isEmpty()) {
				for(Component comp : this.subcomponents) {
					comp.renderComponent();
				}
			}
		}
	}

	@Override
	public int getHeight() {
		if(this.open) {
			return (12 * (this.subcomponents.size() + 1));
		}
		return 12;
	}

	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.isHovered = isMouseOnButton(mouseX, mouseY);
		if(!this.subcomponents.isEmpty()) {
			for(Component comp : this.subcomponents) {
				comp.updateComponent(mouseX, mouseY);
			}
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButton(mouseX, mouseY) && button == 0) {
			this.mod.toggle();
		}
		if(isMouseOnButton(mouseX, mouseY) && button == 1) {
			this.open = !this.open;
			this.parent.refresh();
		}
		for(Component comp : this.subcomponents) {
			comp.mouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		for(Component comp : this.subcomponents) {
			comp.mouseReleased(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	public void keyTyped(char typedChar, int key) {
		for(Component comp : this.subcomponents) {
			comp.keyTyped(typedChar, key);
		}
	}

	public boolean isMouseOnButton(int x, int y) {
		boolean tooltips = StrokeClient.instance.settingsManager.getSettingByName("ClickGui", "Tooltips").getValBoolean();
		if(x > parent.getX() && x < parent.getX() + parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset) {
			// TODO: create tooltip as component
			if(this.parent.isOpen() && tooltips) {
				int h = StrokeClient.getDisplayHeight();
				int fh = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
				Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(this.mod.tooltip, 2, h - fh - 2, ColorUtils.primaryColor);
			}
			
			return true;
		}
		return false;
	}
}
