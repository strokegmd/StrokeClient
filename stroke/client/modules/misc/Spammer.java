package net.stroke.client.modules.misc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.util.TimerUtils;

public class Spammer extends BaseModule {
	public TimerUtils timer;
	
	public int count;
	
	public Spammer() {
		super("Spammer", "Spams messages in chat", 0x00, ModuleCategory.Misc, false);
		StrokeClient.instance.settingsManager.rSetting(new Setting("Delay", this, 1, 1, 10, false));
	}
	
	public void onEnable() {
		timer = new TimerUtils();
		count = 0;
	}
	
	public void onUpdate() {
		double delay = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Delay").getValDouble();
		
		List<String> content = null;
		try {
			content = Files.readAllLines(Paths.get("messages.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String message : content) {
			if(timer.hasTimeElapsed((long) (delay * 1000))) {
				mc.player.sendChatMessage(content.get(count));
				if(count < content.size() - 1) {
					count++;
				} else {
					count = 0;
				}
			}
		}
	}
}
