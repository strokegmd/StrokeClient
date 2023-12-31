package net.stroke.client.util;

public class TimerUtils {
	public long startTime = System.currentTimeMillis();
	
	public boolean hasTimeElapsed(long ms) {
		if(System.currentTimeMillis() >= startTime + ms) {
			this.reset();
			return true;
		}
		
		return false;
	}
	
	public void reset() {
		this.startTime = System.currentTimeMillis();
	}
}
