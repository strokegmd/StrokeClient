package net.stroke.client.util;

import java.awt.Color;

public class ColorUtils {
	public static int primaryColor = 0xffffff;
	public static int secondaryColor = 0x9b90ff;
	public static int settingColor = 0x999999;
	
	public static int primaryObjectColor = new Color(primaryColor).hashCode();
	public static int secondaryObjectColor = new Color(secondaryColor).hashCode();
	
	// stolen again lol kekw xd lmao lmfao))))))
	// best c0d3r ever
	public static int getRainbow(float seconds, float saturation, float brightness, int index) {
		float hue = ((System.currentTimeMillis() + index) % (int)(seconds * 1000)) / (float)(seconds * 1000);
		int color = Color.HSBtoRGB(hue, saturation, brightness);
		return color;
	}
}
